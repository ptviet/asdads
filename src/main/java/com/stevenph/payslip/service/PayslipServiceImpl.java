package com.stevenph.payslip.service;

import com.stevenph.payslip.model.Employee;
import com.stevenph.payslip.model.Payslip;
import com.stevenph.payslip.model.Tax;
import com.stevenph.payslip.utility.CSVFileReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class PayslipServiceImpl implements PayslipService {

  public PayslipServiceImpl() {
  }

  /**
   * Initialize tax ranges
   * @return a list of tax ranges
   */
  private static ArrayList<Tax> initTax() {
    ArrayList<Tax> taxes = new ArrayList<>();

    Tax rate1 = new Tax(0, 18200, 0, 0);
    Tax rate2 = new Tax(18201, 37000, 0.19, 0);
    Tax rate3 = new Tax(37001, 87000, 0.325, 3572);
    Tax rate4 = new Tax(87001, 180000, 0.37, 19822);
    Tax rate5 = new Tax(180001, Integer.MAX_VALUE, 0.45, 54232);

    taxes.add(rate1);
    taxes.add(rate2);
    taxes.add(rate3);
    taxes.add(rate4);
    taxes.add(rate5);

    return taxes;
  }

  /**
   * Generate a payslip object
   * @param employee, Employee
   * @return a payslip object
   */
  private Payslip createPayslip(Employee employee) {

    employee.setIncomeTax(calculateTax(employee.getAnnualSalary()));
    employee.setSuperAmount(calculateSuper(employee.calculateGrossIncome(), employee.getSuperRate()));

    String name = employee.getFirstName() + " " + employee.getLastName();
    String payPeriod = employee.getPaymentPeriod();
    int grossIncome = employee.calculateGrossIncome();
    int incomeTax = employee.getIncomeTax();
    int netIncome = employee.calculateNetIncome();
    int superAmount = employee.getSuperAmount();

    return new Payslip(name, payPeriod, grossIncome, incomeTax, netIncome, superAmount);
  }

  /**
   * Generate a payslip object
   * @param employee, Employee
   * @return a payslip object from createPayslip method
   */
  @Override
  public Payslip generateOne(Employee employee) {
    return createPayslip(employee);
  }

  /**
   * Generate a list of payslips
   * @param inputFile, MultipartFile
   * @return a list of payslips
   */
  @Override
  public ArrayList<Payslip> generateFromFile(MultipartFile inputFile) {

    try {
    CSVFileReader csvFileReader = new CSVFileReader();
    ArrayList<Employee> employees = csvFileReader.readFromFile(inputFile);
      ArrayList<Payslip> payslips = new ArrayList<>();

    for(Employee employee : employees) {
      payslips.add(createPayslip(employee));
    }
      return payslips;

    } catch(IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Calculate income tax
   * @param annualSalary, the provided annual salary
   * @return income tax, round up or round down, or 0 if invalid annualSalary
   */
  @Override
  public int calculateTax(int annualSalary) {
    if (annualSalary > 0) {
      for (Tax tax : initTax()) {
        if (annualSalary > tax.getRangeStart() && annualSalary <= tax.getRangeEnd()) {
          return (int) Math.round((tax.getAdditionAmount() + (annualSalary - tax.getRangeStart()) * tax.getRate()) / 12);
        }
      }
    }

    return 0;
  }

  /**
   * Calculate super amount
   * @param grossIncome, the gross income
   * @param superRate, the super rate
   * @return the super amount, round up or round down
   */
  @Override
  public int calculateSuper(int grossIncome, double superRate) {
    double convertedRate = superRate / 100;
    return (int) Math.round(grossIncome * convertedRate);
  }
}

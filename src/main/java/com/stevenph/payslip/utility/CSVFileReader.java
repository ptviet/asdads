package com.stevenph.payslip.utility;

import com.stevenph.payslip.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;

public class CSVFileReader {

  /**
   * Read data from MultipartFile
   * Read through every line
   * Split data separated by "," into a String[]
   * Delete "%" from superRate if there is any
   * Create employee object
   * @param file, MultipartFile
   * @return the list of employees
   */
  public ArrayList<Employee> readFromFile(MultipartFile file) throws IOException {

    BufferedReader bufferedReader = null;
    String line;
    ArrayList<Employee> employees = new ArrayList<>();

    try {
      bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

      while ((line = bufferedReader.readLine()) != null) {
        String[] details = line.split(",");

        Employee employee = new Employee();

        employee.setFirstName(details[0]);
        employee.setLastName(details[1]);
        employee.setAnnualSalary(Integer.parseInt(details[2]));

        String superRate = details[3];
        if(superRate.endsWith("%")) superRate = superRate.substring(0, superRate.length() - 1);

        employee.setSuperRate(Double.parseDouble(superRate));
        employee.setPaymentPeriod(details[4]);

        if (employee.validateSalaryAndSuper()) {
          employees.add(employee);
        }

      }

    } catch(IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return employees;
  }

}

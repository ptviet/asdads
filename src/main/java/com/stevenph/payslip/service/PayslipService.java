package com.stevenph.payslip.service;

import com.stevenph.payslip.model.Employee;
import com.stevenph.payslip.model.Payslip;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface PayslipService {
  Payslip generateOne(Employee employee);
  ArrayList<Payslip> generateFromFile(MultipartFile file);
  int calculateTax(int annualSalary);
  int calculateSuper(int grossIncome, double superRate);
}

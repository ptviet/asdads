package com.stevenph.payslip.controller;

import com.stevenph.payslip.model.Employee;
import com.stevenph.payslip.service.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PayslipController {

  @Autowired
  PayslipService payslipService;

  @GetMapping("/")
  public String index() {
    return  "Hello there!";
  }

  /**
   * Return a payslip based on employee details provided
   * @param employee, Employee
   * @return the payslip
   */
  @PostMapping("/generateOne")
  public ResponseEntity generateOne(@RequestBody Employee employee) {

    return new ResponseEntity<>(payslipService.generateOne(employee), HttpStatus.OK);
  }

  /**
   * Return a list of payslips based on employee details provided in uploaded file
   * @param file, MultipartFile
   * @return a list of payslips
   */
  @PostMapping("/generateFromFile")
  public ResponseEntity generateFromFile(@RequestParam("file") MultipartFile file) {

    return new ResponseEntity<>(payslipService.generateFromFile(file), HttpStatus.OK);
  }

}

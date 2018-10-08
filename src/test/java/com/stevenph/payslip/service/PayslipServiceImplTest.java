package com.stevenph.payslip.service;

import com.stevenph.payslip.model.Employee;
import com.stevenph.payslip.utility.CSVFileReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayslipServiceImplTest {

  @Autowired
  PayslipService payslipService;

  @Autowired
  WebApplicationContext wContext;

  private MockMvc mockMvc;
  private CSVFileReader csvFileReader;
  private String data = "";

  @Before
  public void setUp(){

    csvFileReader = new CSVFileReader();
    mockMvc = MockMvcBuilders.webAppContextSetup(wContext)
        .alwaysDo(MockMvcResultHandlers.print())
        .build();

    data = "David,Rudd,60050,9%,01 March – 31 March\n" +
        "Ryan,Chen,120000,10%,01 March – 31 March";
  }

  @Test
  public void calculateSuper() {
    assertEquals(450, payslipService.calculateSuper(5004, 9));
  }

  @Test
  public void calculateTax() {
    assertEquals(922, payslipService.calculateTax(60050));
  }

  @Test
  public void generateFromFile() throws Exception {

    MockMultipartFile file = new MockMultipartFile("testinput", "testinput.csv", "text/plain", data.getBytes());

    ArrayList<Employee> employees = csvFileReader.readFromFile(file);
    Employee emp = employees.get(0);
    emp.setIncomeTax(payslipService.calculateTax(emp.getAnnualSalary()));
    emp.setSuperAmount(payslipService.calculateSuper(emp.calculateGrossIncome(), emp.getSuperRate()));

    assertEquals(922, emp.getIncomeTax());
    assertEquals(450, emp.getSuperAmount());

  }

  @Test
  public void generateOne() throws Exception {
  }
}
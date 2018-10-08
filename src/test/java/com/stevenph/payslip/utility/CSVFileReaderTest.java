package com.stevenph.payslip.utility;

import com.stevenph.payslip.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CSVFileReaderTest {

  private CSVFileReader csvFileReader;
  private String data = "";

  @Before
  public void setUp() {

    csvFileReader = new CSVFileReader();
    data = "David,Rudd,60050,9%,01 March – 31 March\n" +
        "Ryan,Chen,120000,10%,01 March – 31 March";
  }

  @Test
  public void readFromFile() throws Exception {

    MockMultipartFile file = new MockMultipartFile("testinput", "testinput.csv", "text/plain", data.getBytes());

    ArrayList<Employee> employees = csvFileReader.readFromFile(file);
    assertEquals(2, employees.size());

    Employee emp1 = employees.get(0);
    assertEquals("David", emp1.getFirstName());
    assertEquals("Rudd", emp1.getLastName());
    assertEquals("01 March – 31 March", emp1.getPaymentPeriod());
    assertEquals(60050, emp1.getAnnualSalary());

    assertEquals(9, emp1.getSuperRate(), 0);

    Employee employeeTwo = employees.get(1);
    assertEquals("Ryan", employeeTwo.getFirstName());
    assertEquals("Chen", employeeTwo.getLastName());
    assertEquals("01 March – 31 March", employeeTwo.getPaymentPeriod());
    assertEquals(120000, employeeTwo.getAnnualSalary());
    assertEquals(10, employeeTwo.getSuperRate(), 0);

  }

}
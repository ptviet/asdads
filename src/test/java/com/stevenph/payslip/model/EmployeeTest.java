package com.stevenph.payslip.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class EmployeeTest {

  private Employee employee;

  @Before
  public void setUp() throws Exception {
    employee = new Employee("David", "Rudd", 60050, 9, "01 March - 31 March");
  }

  @Test
  public void testEmployeeExist() throws Exception {
    assertNotNull(employee);
  }

  @Test
  public void calculateGrossIncome() throws Exception {
    assertEquals(5004, employee.calculateGrossIncome());
  }

  @Test
  public void validateSalary() throws Exception {
    employee.setAnnualSalary(-1000);
    assertThat(employee.validateSalaryAndSuper()).isFalse();

    employee.setAnnualSalary(1000);
    assertThat(employee.validateSalaryAndSuper()).isTrue();
  }

  @Test
  public void validateSuper() throws Exception {
    employee.setSuperRate(51.0);
    assertThat(employee.validateSalaryAndSuper()).isFalse();

    employee.setSuperRate(49.0);
    assertThat(employee.validateSalaryAndSuper()).isTrue();
  }

}
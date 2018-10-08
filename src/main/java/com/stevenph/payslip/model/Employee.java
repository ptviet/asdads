package com.stevenph.payslip.model;

public class Employee {

  private String firstName;
  private String lastName;
  private int annualSalary;
  private double superRate;
  private String paymentPeriod;
  private int incomeTax;
  private int superAmount;

  public Employee() {
  }

  public Employee(String firstName, String lastName, int annualSalary, double superRate, String paymentPeriod) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.annualSalary = annualSalary;
    this.superRate = superRate;
    this.paymentPeriod = paymentPeriod;
  }

  /**
   * Calculate employee's gross income
   * @return gross income, round up or round down
   */
  public int calculateGrossIncome() {

    return Math.round( (float) annualSalary / 12);
  }

  /**
   * Calculate employee's net income
   * @return net income, round up or round down
   */
  public int calculateNetIncome() {

    return (calculateGrossIncome() - incomeTax);
  }

  /**
   * Validate employee's super and salary input
   * annual salary must be a positive integer
   * super rate must be 0% - 50% inclusive
   * @return true if valid, false if invalid
   */
  public boolean validateSalaryAndSuper() {

    if(annualSalary < 0) {
      return false;
    } else if (superRate < 0.0 || superRate > 50.0) {
      return false;
    } else {
      return true;
    }

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAnnualSalary() {
    return annualSalary;
  }

  public void setAnnualSalary(int annualSalary) {
    this.annualSalary = annualSalary;
  }

  public double getSuperRate() {
    return superRate;
  }

  public void setSuperRate(double superRate) {
    this.superRate = superRate;
  }

  public String getPaymentPeriod() {
    return paymentPeriod;
  }

  public void setPaymentPeriod(String paymentPeriod) {
    this.paymentPeriod = paymentPeriod;
  }

  public int getIncomeTax() {
    return incomeTax;
  }

  public void setIncomeTax(int incomeTax) {
    this.incomeTax = incomeTax;
  }

  public int getSuperAmount() {
    return superAmount;
  }

  public void setSuperAmount(int superAmount) {
    this.superAmount = superAmount;
  }

}

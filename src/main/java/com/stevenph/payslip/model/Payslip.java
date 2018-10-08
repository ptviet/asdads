package com.stevenph.payslip.model;

public class Payslip {

  private String name;
  private String payPeriod;
  private int grossIncome;
  private int incomeTax;
  private int netIncome;
  private int superAmount;

  public Payslip() {
  }

  public Payslip(String name, String payPeriod, int grossIncome, int incomeTax, int netIncome, int superAmount) {
    this.name = name;
    this.payPeriod = payPeriod;
    this.grossIncome = grossIncome;
    this.incomeTax = incomeTax;
    this.netIncome = netIncome;
    this.superAmount = superAmount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPayPeriod() {
    return payPeriod;
  }

  public void setPayPeriod(String payPeriod) {
    this.payPeriod = payPeriod;
  }

  public int getGrossIncome() {
    return grossIncome;
  }

  public void setGrossIncome(int grossIncome) {
    this.grossIncome = grossIncome;
  }

  public int getIncomeTax() {
    return incomeTax;
  }

  public void setIncomeTax(int incomeTax) {
    this.incomeTax = incomeTax;
  }

  public int getNetIncome() {
    return netIncome;
  }

  public void setNetIncome(int netIncome) {
    this.netIncome = netIncome;
  }

  public int getSuperAmount() {
    return superAmount;
  }

  public void setSuperAmount(int superAmount) {
    this.superAmount = superAmount;
  }
}


package com.stevenph.payslip.model;

public class Tax {

  private int rangeStart;
  private int rangeEnd;
  private double rate;
  private double additionAmount;

  public Tax(int rangeStart, int rangeEnd, double rate, double additionAmount) {
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
    this.rate = rate;
    this.additionAmount = additionAmount;
  }

  public int getRangeStart() {
    return rangeStart;
  }

  public void setRangeStart(int rangeStart) {
    this.rangeStart = rangeStart;
  }

  public int getRangeEnd() {
    return rangeEnd;
  }

  public void setRangeEnd(int rangeEnd) {
    this.rangeEnd = rangeEnd;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public double getAdditionAmount() {
    return additionAmount;
  }

  public void setAdditionAmount(double additionAmount) {
    this.additionAmount = additionAmount;
  }
}

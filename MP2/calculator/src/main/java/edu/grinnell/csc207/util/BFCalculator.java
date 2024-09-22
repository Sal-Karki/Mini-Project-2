package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * BFCalculator Class. by: Sal Karki.
 */
public class BFCalculator {
  /**
  * Stores last fraction.
  */
  private BigFraction lastValue;

  /**
  * Constructor.
  */
  public BFCalculator() {
    this.lastValue = new BigFraction(BigInteger.ZERO, BigInteger.ONE);

  } // constructor

    /**
    * Returns last value.
    * @return lastValue
    */
  public BigFraction get() {
    return this.lastValue;
  } // get

    /**
    * Adds last value to input.
    * @param addValue
    */
  public void add(BigFraction addValue) {
    this.lastValue = this.lastValue.add(addValue);
  } // add

  /**
  * Subtracts input from last valye.
  * @param subtractValue
  */
  public void subtract(BigFraction subtractValue) {
    BigFraction negativeValue = new BigFraction(subtractValue.numerator().negate(),
        subtractValue.denominator());

    this.lastValue = this.lastValue.add(negativeValue);
  } // subtract

  /**
  * Multiplies last value and input.
  * @param multiplyValue
  */
  public void multiply(BigFraction multiplyValue) {
    BigInteger numerator = this.lastValue.numerator().multiply(multiplyValue.numerator());
    BigInteger denominator = this.lastValue.denominator().multiply(multiplyValue.denominator());
    this.lastValue = new BigFraction(numerator, denominator);
  } // multiply

  /**
  * Divides last value by input.
  * @param divideValue
  */
  public void divide(BigFraction divideValue) {
    BigInteger numerator = this.lastValue.numerator().multiply(divideValue.denominator());
    BigInteger denominator = this.lastValue.denominator().multiply(divideValue.numerator());
    this.lastValue = new BigFraction(numerator, denominator);
  } // divide

  /**
  * Sets last value to 0.
  */
  public void clear() {
    this.lastValue = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
  } // clear

} // BFCalculator

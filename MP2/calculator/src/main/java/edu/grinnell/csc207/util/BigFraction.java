package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * BigFraction class.
 * Created from lab code by Professor Sam.
 * by: Sal Karki.
 */
public class BigFraction {
  /**
   * numerator.
   */
  private BigInteger num;

  /**
   * denominator.
   */
  private BigInteger denom;
  /**
   * Build a new fraction with numerator num and denominator denom.
   * @param numerator
   * @param denominator
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    if (denominator.equals(BigInteger.ZERO)) {
      System.err.println("Denominator cannot be zero.");
    } // if
    simplifyAndSet(numerator, denominator);
  } // BigFraction

  /**
   * Constructor.
   * @param numerator
   * @param denominator
   */
  public BigFraction(int numerator, int denominator) {
    this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
  } // constructor

  /**
   * Build a new fraction by parsing a string of the form "numerator/denominator".
   * @param str
   */
  public BigFraction(String str) {
    String[] parts = str.split("/");
    if (parts.length == 2) {
      BigInteger numerator = new BigInteger(parts[0].trim());
      BigInteger denominator = new BigInteger(parts[1].trim());
      if (denominator.equals(BigInteger.ZERO)) {
        System.err.println("Denominator cannot be zero.");
      } // if
      simplifyAndSet(numerator, denominator);
    } else if (parts.length == 1) {
      BigInteger numerator = new BigInteger(parts[0].trim());
      simplifyAndSet(numerator, BigInteger.ONE);
    } else {
      System.err.println("Invalid fraction format.");
    } // if
  } // BigFraction


  /**
   * Simplify and set the numerator and denominator by dividing by the GCD.
   * @param numerator
   * @param denominator
   */
  private void simplifyAndSet(BigInteger numerator, BigInteger denominator) {
    BigInteger gcd = numerator.gcd(denominator);
    numerator = numerator.divide(gcd);
    denominator = denominator.divide(gcd);
    if (denominator.compareTo(BigInteger.ZERO) < 0) {
      numerator = numerator.negate();
      denominator = denominator.negate();
    } // if

    this.num = numerator;
    this.denom = denominator;
  } // simplifyAndSet

  /**
   * Add another fraction to this fraction.
   * @param addend
   * @return BigFraction
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultDenominator = this.denom.multiply(addend.denom);
    BigInteger resultNumerator =
        this.num.multiply(addend.denom).add(addend.num.multiply(this.denom));

    return new BigFraction(resultNumerator, resultDenominator);
  } // add

  /**
   * Subtract another fraction from this fraction.
   * @param subtrahend
   * @return BigFraction
   */
  public BigFraction subtract(BigFraction subtrahend) {
    BigInteger resultDenominator = this.denom.multiply(subtrahend.denom);
    BigInteger resultNumerator =
        this.num.multiply(subtrahend.denom).subtract(subtrahend.num.multiply(this.denom));

    return new BigFraction(resultNumerator, resultDenominator);
  } // subtract

  /**
   * Multiply this fraction by another fraction.
   * @param multiplier
   * @return BigFraction
   */
  public BigFraction multiply(BigFraction multiplier) {
    BigInteger resultNumerator = this.num.multiply(multiplier.num);
    BigInteger resultDenominator = this.denom.multiply(multiplier.denom);

    return new BigFraction(resultNumerator, resultDenominator);
  } // multiply

  /**
   * Divide this fraction by another fraction.
   * @param divisor
   * @return BigFraction
   */
  public BigFraction divide(BigFraction divisor) {
    if (divisor.num.equals(BigInteger.ZERO)) {
      System.err.println("Cannot divide by zero.");
    } // if
    BigInteger resultNumerator = this.num.multiply(divisor.denom);
    BigInteger resultDenominator = this.denom.multiply(divisor.num);

    return new BigFraction(resultNumerator, resultDenominator);
  } // divide

  /**
   * Express this fraction as a double.
   * @return double
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue

  /**
   * Get the numerator of this fraction.
   * @return BigInteger
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator

  /**
   * Get the denominator of this fraction.
   * @return BigInteger
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator

  /**
   * Convert this fraction to a string for ease of printing.
   * @return String
   */
  @Override
  public String toString() {
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } else if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    } else {
      return this.num + "/" + this.denom;
    } // if
  } // toString
} // BigFraction

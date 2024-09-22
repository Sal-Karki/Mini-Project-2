package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Quick Calculator Class by Sal Karki.
 */
public class QuickCalculator {

  /**
   * Main Class.
   * @param args
   */
  public static void main(String[] args) {
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();

    for (String arg : args) {
      if (arg.startsWith("STORE ")) {
        char register = arg.charAt(6);
        registers.store(register, calculator.get());
        System.err.println("STORE " + register + " -> STORED");
      } // if
    } // for
  } // main

  /**
   * Evaluates the expression and calls inputted operator.
   * @param tokens
   * @param calculator
   * @param registers
   * @return BigFraction
   */
  private static BigFraction evaluateExpression(String[] tokens,
      BFCalculator calculator, BFRegisterSet registers) {
    BigFraction currentVal = parseFractionOrRegister(tokens[0], registers);

    for (int i = 1; i < tokens.length; i += 2) {
      String operator = tokens[i];
      BigFraction nextVal = parseFractionOrRegister(tokens[i + 1], registers);

      switch (operator) {
        case "+":
          calculator.add(nextVal);
          break;
        case "-":
          calculator.subtract(nextVal);
          break;
        case "*":
          calculator.multiply(nextVal);
          break;
        case "/":
          calculator.divide(nextVal);
          break;
        default:
          System.err.println("Error: Invalid operator");
      } // switch
    } // for

    return calculator.get();
  } // evaulateExpression

  /**
   * Separates each expressions.
   * @param token
   * @param registers
   * @return BigFraction
   */
  private static BigFraction parseFractionOrRegister(String token, BFRegisterSet registers) {
    if (token.length() == 1 && Character.isLetter(token.charAt(0))) {
      return registers.get(token.charAt(0));
    } else {
      String[] parts = token.split("/");
      if (parts.length == 2) {
        return new BigFraction(new BigInteger(parts[0]), new BigInteger(parts[1]));
      } else {
        return new BigFraction(new BigInteger(parts[0]), BigInteger.ONE);
      } // if
    } // if
  } // parseFractionOrRegister
} // QuickCalculator

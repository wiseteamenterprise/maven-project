package com.example;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {

  }

  /**
   * @param someonte the name of a person
   * @return greetung string
   */
  public final String greet(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}

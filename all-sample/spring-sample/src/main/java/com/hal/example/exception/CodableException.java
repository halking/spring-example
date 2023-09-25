package com.hal.example.exception;

public interface CodableException {

  /**
   * Gets the code.
   *
   * @return the code
   */
  int getCode();

  /**
   * Gets the message.
   *
   * @return the message
   */
  String getMessage();
}

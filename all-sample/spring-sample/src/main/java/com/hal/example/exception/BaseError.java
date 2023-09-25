package com.hal.example.exception;

/**
 * The Interface BaseErrorIF.
 */
public interface BaseError {

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

  /**
   * With params.
   *
   * @param params the params
   * @return the string
   */
  String withParams(Object... params);
}

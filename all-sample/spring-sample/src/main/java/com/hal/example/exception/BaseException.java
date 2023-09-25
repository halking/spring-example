package com.hal.example.exception;

/**
 * The Class BaseException.
 */
public class BaseException extends RuntimeException implements CodableException {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = -2416491642235544882L;

  /**
   * The code.
   */
  private int code;

  /**
   * Instantiates a new demo base exception.
   *
   * @param message the message
   */
  public BaseException(String message) {
    super(message);
  }

  /**
   * Instantiates a new demo base exception.
   *
   * @param code the code
   * @param message the message
   */
  public BaseException(int code, String message) {
    super(message);
    this.code = code;
  }

  /**
   * Instantiates a new demo base exception.
   *
   * @param message the message
   * @param e the e
   */
  public BaseException(String message, Throwable e) {
    super(message, e);
    if (e instanceof CodableException) {
      this.code = ((CodableException) e).getCode();
    }
  }

  /**
   * Instantiates a new base exception.
   *
   * @param error the error
   * @param params the params
   */
  public BaseException(BaseError error, Object... params) {
    this(error.getCode(), error.withParams(params));
  }

  /**
   * Gets the code.
   *
   * @return the code
   */
  @Override
  public int getCode() {
    return code;
  }

  /**
   * Sets the code.
   *
   * @param code the new code
   */
  public void setCode(int code) {
    this.code = code;
  }

}

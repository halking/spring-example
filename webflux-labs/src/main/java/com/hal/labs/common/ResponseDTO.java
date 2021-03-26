package com.hal.labs.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO<T> {

  /**
   * The Constant SUCCESS_MSG.
   */
  public static final String SUCCESS_MSG = "ok";

  /**
   * The Constant FAIL_MSG.
   */
  public static final String FAIL_MSG = "fail";

  /**
   * The Constant CODE_OK.
   */
  public static final int CODE_OK = 0;

  /**
   * The code.
   */
  private int code;

  /**
   * The error message.
   */
  private String msg;

  /**
   * The data.
   */
  @JsonInclude(Include.NON_NULL)
  private T data;

  private ResponseDTO() {

  }

  /**
   * Instantiates a new response dto with success code & message
   */
  public static ResponseDTO success() {
    ResponseDTO response = new ResponseDTO();
    response.code = CODE_OK;
    response.msg = SUCCESS_MSG;
    return response;
  }

  /**
   * Instantiates a new response dto with success code & message
   */
  public static <R> ResponseDTO<R> success(R data) {
    ResponseDTO<R> response = new ResponseDTO<>();
    response.setCode(CODE_OK);
    response.setMsg(SUCCESS_MSG);
    response.setData(data);
    return response;
  }

  /**
   * Instantiates a new response dto with failure code & message
   */
  public static ResponseDTO fail(int code, String errmsg) {
    ResponseDTO<String> response = new ResponseDTO<>();
    response.setCode(code);
    response.setMsg(errmsg);
    return response;
  }

  /**
   * Instantiates a new response dto with failure code & message
   */
  public static ResponseDTO fail(Exception e) {
    ResponseDTO<String> response = new ResponseDTO<>();
    response.setMsg(e.getMessage());
    return response;
  }


}

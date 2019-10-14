package com.hal.sample.common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Steven HUANG
 * @Date: 2019/10/11
 */
public class LoggerMain {

  private static Logger logger = Logger.getLogger("com.hal.sample.common.LoggerMain");

  public static void main(String argv[]) {
    // Log a FINE tracing message
    logger.fine("doing stuff");
    try{
      System.out.println("test logger");
    } catch (Exception ex) {
      // Log the exception
      logger.log(Level.WARNING, "trouble sneezing", ex);
    }
    logger.fine("done");
  }

}

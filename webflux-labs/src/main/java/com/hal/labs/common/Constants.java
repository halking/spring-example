package com.hal.labs.common;

import com.google.common.collect.Sets;
import java.util.Set;

public interface Constants {

  /**
   * The default page number
   */
  Integer DEFAULT_PAGE_NO = Integer.valueOf(1);
  /**
   * The default page size
   */
  Integer DEFAULT_PAGE_SIZE = Integer.valueOf(10);

  String SYSTEM = "System";

  Set<String> IMAGE_TYPES = Sets.newHashSet("jpg", "jpeg", "png");


  String yyyyMMddHHmmss = "yyyyMMddHHmmss";
  String yyyyMMdd = "yyyyMMdd";
  String yyyyMM = "yyyyMM";
  String yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";
  String yyyy_MM_dd = "yyyy-MM-dd";
  String HHmmss = "HH:mm:ss";
  String mmss = "mmss";
  String SUCCESS = "SUCCESS";
  String CN_DATE_PATTERN = "yyyy年MM月dd日";

  String REDIS_SUCCESS = "SUCCESS";

}

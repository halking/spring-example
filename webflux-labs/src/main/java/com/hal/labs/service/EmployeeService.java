package com.hal.labs.service;

import com.hal.labs.dao.EmployeesDao;
import com.hal.labs.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Steven HUANG
 * @Date: 2021/3/26
 */
@Service
public class EmployeeService {

  @Autowired
  private EmployeesDao employeesDao;

  public Employees getById(Long id) {
    return employeesDao.findById(id).orElse(null);
  }
}

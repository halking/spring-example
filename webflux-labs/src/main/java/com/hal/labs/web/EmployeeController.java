package com.hal.labs.web;

import com.hal.labs.common.ResponseDTO;
import com.hal.labs.entity.Employees;
import com.hal.labs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: Steven HUANG
 * @Date: 2021/3/26
 */
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController extends BaseController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/{employeeId}")
  public Mono<ResponseDTO<Employees>> getDetail(@PathVariable("employeeId") Long employeeId) {
    return this.successMono(employeeService.getById(employeeId));
  }
}

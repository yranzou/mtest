package com.mtest.webapp.controller;

import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("displayEmployees.htm")
    public ModelAndView execute(Model model) {
        List<Employee> employees = this.employeeService.getAll();
        model.addAttribute("employees", employees);
        return new ModelAndView("employees");
    }
}

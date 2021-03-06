package com.mtest.webapp.controllers;

import com.mtest.model.Department;
import com.mtest.server.common.DepartmentService;
import com.mtest.server.common.EmployeeService;
import com.mtest.server.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("doAdd")
    public String doAdd(@RequestParam("name") String name,
                        @RequestParam("chiefId") int chiefId ) {
        Department department = new Department();
        department.setName(name);
        department.setChiefId(chiefId);
        departmentService.create(department);
        return "redirect:/employee/all";
    }

    @RequestMapping("add")
    public ModelAndView add(Model model) throws ServerException {
        model.addAttribute("chiefs",  employeeService.getAllDepartmentsChiefs());
        return new ModelAndView("addDepartment");
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public ModelAndView display(@PathVariable("id") int id, Model model) throws ServerException {
        model.addAttribute("department", departmentService.get(id));
//        model.addAttribute("departmentChief",  employeeService.getDepartmentChief(id));
        return new ModelAndView("departmentPage");
    }

//    @RequestMapping("do")
//    public ModelAndView displayAllEmployees(Model model) {
//        List<Employee> employees = this.employeeService.getAll();
//        model.addAttribute("employees", employees);
//        List<Department> departments = this.departmentService.search("NAME","");
//        model.addAttribute("departments", departments);
//        return new ModelAndView("employees");
//    }



    @RequestMapping("update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("name") String name,
                         @RequestParam("chiefId") int chiefId,
                         Model model) throws ServerException {

        Department department = departmentService.get(id);
        department.setName(name);
        department.setChiefId(chiefId);
        departmentService.update(department);
        return "redirect:" + id;
    }


    @RequestMapping(value="edit/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable("id") int id, Model model) throws ServerException {
        model.addAttribute("thisDepartmentChief", employeeService.getDepartmentChief(id));
        model.addAttribute("department",  departmentService.get(id));
        model.addAttribute("chiefs",  employeeService.getAllDepartmentsChiefs());
        return new ModelAndView("editDepartment");
    }

    @RequestMapping("all")
    public ModelAndView displayAll(Model model) throws ServerException {
        List<Department> departments = this.departmentService.getAll();
        model.addAttribute("departments", departments);
        return new ModelAndView("departments");
    }
}

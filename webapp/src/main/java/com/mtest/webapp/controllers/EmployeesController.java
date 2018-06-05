package com.mtest.webapp.controllers;

import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.model.Phone;
import com.mtest.model.PhoneType;
import com.mtest.server.common.DepartmentService;
import com.mtest.server.common.EmployeeService;
import com.mtest.server.common.PhoneService;
import com.mtest.server.exception.ServerException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("employee")
public class EmployeesController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PhoneService phoneService;

    @PostMapping("doAdd")
    public String doAddEmployee(@RequestParam("photo") MultipartFile photo,
                                @RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("phone") String phone,
                                @RequestParam("datepicker") String birthday,

                                RedirectAttributes redirectAttributes) throws ServerException {
        Employee employee = new Employee();

        logger.debug("Going to add Employee " + name + " " + surname);
        if (!photo.isEmpty()) {
            try {
                employee.setPhoto(photo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        employee.setName(name);
        employee.setSurname(surname);
        employee.setPhone(phone);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        java.sql.Date sqlDate = null;
        Date date = null;
        try {
            date = format.parse(birthday);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(date);

        employee.setBirthday(sqlDate);

        employeeService.create(employee);
        logger.info("Employee has been created with unknown id: ");
        return "redirect:all";
    }


    @RequestMapping("add")
    public ModelAndView add() {
        return new ModelAndView("addEmployee");
    }

    @PostMapping("update")
    public String update(@RequestParam("photo") MultipartFile photo,
                                   @RequestParam("id") int id,
                                   @RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
//                                   @RequestParam("phone") String phone,
                                   @RequestParam("chiefId") int chiefId,
                                   @RequestParam("phoneNumber") String[] phoneNumber,
                                   @RequestParam("phoneType") String[] phoneType,

                                   RedirectAttributes redirectAttributes) throws ServerException {

        Employee employee = employeeService.get(id);
        if (!photo.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:/employee/all";

            try {
                employee.setPhoto(photo.getBytes());
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        employee.setName(name);
        employee.setSurname(surname);
//        employee.setPhone(phone);
        employee.setChiefId(chiefId);
        phoneService.delete(id);
        if (phoneNumber.length!=1)
        {
            Set<Phone> phones = new TreeSet<>();
            for (int i = 1; i < phoneNumber.length; i++) {
                Phone phone = new Phone();
                phone.setNumber(phoneNumber[i]);
                phone.setType(PhoneType.valueOf(phoneType[i]));
                phones.add(phone);
            }

            phoneService.create(phones, id);
        } else
        {

        }

        employeeService.update(employee);
        return "redirect:" + id;
    }




    @RequestMapping(value="edit/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable("id") int id,
                             Model model) throws ServerException {
        Employee employee = employeeService.get(id);
        Set<Phone> phones = phoneService.get(id);
        model.addAttribute("employee", employee);
        model.addAttribute("phones", phones);
//        model.addAttribute(employee.getSurname());
//        model.addAttribute(employee.getPhone());
//        model.addAttribute(id);
        return new ModelAndView("editEmployee");
    }

    @RequestMapping("search")
    public ModelAndView search(@RequestParam("searchIn") String searchIn,
                               @RequestParam("searchValue") String searchValue,
                               Model model
                               ) throws ServerException {
        model.addAttribute("employees",  employeeService.search(searchIn, searchValue));
        model.addAttribute("departments",  departmentService.search(searchIn, searchValue));
        return new ModelAndView("employees");
    }

    @RequestMapping("all")
    public ModelAndView displayAll(Model model) throws ServerException {
        logger.info("!!!!!!!!!!!!!!!! ALL EMPLOYEES SHOWN");
        logger.debug("ALL EMP DEBUG!!!!!!!!!!!!");
        List<Employee> employees = this.employeeService.getAll();
        model.addAttribute("employees", employees);
        List<Department> departments = this.departmentService.search("NAME","");
        model.addAttribute("departments", departments);
        return new ModelAndView("employees");
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public ModelAndView display(@PathVariable("id") int id, Model model) throws ServerException {
        Employee employee = employeeService.get(id);
        String image = "data:image/png;base64," + Base64.encode(employee.getPhoto());
        Set<Phone> phones = phoneService.get(id);
        employee.setPhones(phones);
        model.addAttribute("phones", phones);
        model.addAttribute("employee", employee);
        model.addAttribute("image", image);
        return new ModelAndView("employeePage");
    }
}

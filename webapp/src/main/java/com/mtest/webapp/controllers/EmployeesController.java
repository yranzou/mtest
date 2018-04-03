package com.mtest.webapp.controllers;

import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.server.DepartmentService;
import com.mtest.server.EmployeeService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("employee")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("doAdd")
    public String doAddEmployee(@RequestParam("photo") MultipartFile photo,
                                @RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("phone") String phone,
                                RedirectAttributes redirectAttributes) {
        Employee employee = new Employee();

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

        employeeService.create(employee);
        return "redirect:all";
    }


    @RequestMapping("add")
    public ModelAndView add() {
        return new ModelAndView("addEmployee");
    }

    @PostMapping("update")
    public String updateEmployee(@RequestParam("photo") MultipartFile photo,
                                   @RequestParam("id") int id,
                                   @RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("phone") String phone,
                                   RedirectAttributes redirectAttributes) {

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
        employee.setPhone(phone);
        employeeService.update(employee);
        return "redirect:" + id;
    }




    @RequestMapping(value="edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id,
                             Model model) {
        Employee employee = employeeService.get(id);
        model.addAttribute(employee);
//        model.addAttribute(employee.getSurname());
//        model.addAttribute(employee.getPhone());
//        model.addAttribute(id);
        return new ModelAndView("editEmployee");
    }

    @RequestMapping("search")
    public ModelAndView search(@RequestParam("searchIn") String searchIn,
                               @RequestParam("searchValue") String searchValue,
                               Model model
                               ) {
        model.addAttribute("employees",  employeeService.search(searchIn, searchValue));
        model.addAttribute("departments",  departmentService.search(searchIn, searchValue));
        return new ModelAndView("employees");
    }

    @RequestMapping("all")
    public ModelAndView displayAll(Model model) {
        List<Employee> employees = this.employeeService.getAll();
        model.addAttribute("employees", employees);
        List<Department> departments = this.departmentService.search("NAME","");
        model.addAttribute("departments", departments);
        return new ModelAndView("employees");
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public ModelAndView display(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.get(id);
        String image = "data:image/png;base64," + Base64.encode(employee.getPhoto());
        model.addAttribute("employee", employee);
        model.addAttribute("image", image);
        return new ModelAndView("employeePage");
    }
}

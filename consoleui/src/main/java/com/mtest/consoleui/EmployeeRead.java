package com.mtest.consoleui;

import com.mtest.dao.EmployeeDao;
import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class EmployeeRead {
    private static String phone = "";
    private static String name = "";
    private static String surname = "";
    private static EmployeeService employeeService;

    private static void menuUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - Get co-workers\n" +
                "2 - Get subordinates\n" +
                "3 - Get all\n" +
                "0 - ../\n\n");
    }



    static void menu(String subCat, Scanner sc) {

        boolean isRun = true;

        while (isRun) {
            menuUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
//                        employeeSetName(subCat, sc);
                        break;
                    case "2":
//                        employeeSetSurname(subCat, sc);
                        break;
                    case "3":
                        employeeGetAll(subCat, sc);
                        break;
                    case "0":
                        isRun = false;
                        break;
//                        System.out.println("wtf");

                }
            }
        }
    }
    private static void employeePersistUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "0 - ../\n" +
                "Enter - save\n");
    }

    private static void employeeGetAllUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - get all employees\n" +
                "0 - Parent dir\n");
    }

    private static void printEmployees() {
//        List<Employee> employees = new EmployeeService().getAll();
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.getAll();
        for (Employee employee : employees) {
            System.out.println("Id = " + employee.getId());
            System.out.println("Name = " + employee.getName());
        }

    }

    private static void employeeGetAll(String cat, Scanner sc) {
        String subCat = cat + "GetAll/";
        boolean isRun = true;
        while (isRun) {
            employeeGetAllUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        printEmployees();
                        break;
                    case "0":
                        phone = _next;
                        isRun = false;
                        break;
                }
            }
        }
    }





}

package com.mtest.consoleui;

import com.mtest.server.DepartmentService;
import com.mtest.server.EmployeeService;

import java.util.Scanner;

/**
 * Created by yuri on 27.01.18.
 */
public class UI {
    private String phone;
    private String name;
    private String surname;
    private Scanner sc;
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    UI()
    {
        sc = new Scanner(System.in);
        employeeService = new EmployeeService();
        departmentService = new DepartmentService();
    }

    private void usage(String str) {
        System.out.println(
                str + "\n" +
                "\n" +
                "1 - Create\n" +
                "2 - Update\n" +
                "3 - Delete\n" +
                "4 - Reports\n" +
                "0 - exit");
    }

    private void createUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - Employee\n" +
                "2 - Department\n" +
                "0 - Parent dir");
    }

    private void create(String cat) {
        String subCat = cat + "Create/";
        boolean isRun = true;
        createUsage(subCat);
        while (isRun) {
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        employee(subCat);
                        break;
                    case "2":
                        isRun = false;
                        department(subCat);
                        break;
                    case "0":
                        isRun = false;

                }
            }
        }
        start();
    }

    private void department(String subCat) {
    }

    public void start()
    {
        String cat = "\n/CRUD/";
        boolean isRun = true;
        usage(cat);
        while (isRun)  {

            if (sc.hasNext())
            {
                String _next = sc.next();
                switch (_next)
                {
                    case "1":
                        create(cat);
                        break;
                    case "2":
                        System.out.println("Update" +
                                "1 - Employee\n" +
                                "2 - Department");
                        break;
                    case "3":
                        System.out.println("Destroy" +
                                "1 - Employee\n" +
                                "2 - Department");
                        break;
                    case "4":
                        System.out.println("Report\n" +
                                "1 - List department employees\n" +
                                "2 - List subordinates");
                        break;
                    case "0":
//                        isRun = false;
                        System.exit(0);
                        break;
                    default:
                        usage(cat);

                }
            }
        }
    }



    private void employeeUsage(String subCat) {
        System.out.println(subCat + "\n" +



                "0 - ../\n" +
                "1 - Set name\n" +
                "2 - Set surname\n" +
                "3 - Set phone\n" +
                "4 - Commit:\n" +
                "    Name = " + name +
                "    Surname = " + surname +
                "    Phone = " + phone);
    }

    private void employee(String cat) {
        String phone = "";
        String name = "";
        String surname = "";
        String subCat = cat + "Employee/";

        boolean isRun = true;
        employeeUsage(subCat);
        while (isRun) {
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        employeeSetName(subCat);
                        break;
                    case "2":
                        employeeSetSurname(subCat);
                        break;
                    case "3":
                        employeeSetPhone(subCat);
                        break;
                    case "4":
                        employeePersist(subCat);
                        break;
                    case "0":
                        isRun = false;
//                        System.out.println("wtf");

                }
            }
        }
        start();
    }

    private void employeePersistUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "0 - ../\n" +
                "Enter - save\n");
    }

    private void employeePersist(String cat) {

        employeeService.create(name, surname, phone);
//        String subCat = cat + "save/";
//        boolean isRun = true;
//        employeePersistUsage(subCat);
//        while (isRun) {
//            if (sc.hasNext()) {
//                String _next = sc.next();
//                switch (_next) {
//                    case "0":
//                        isRun = false;
//                        break;
//                    default:
//                        employeeService.create(_next);
//
//                }
//            }
//        }
//        start();
    }

    private void employeeSetPhoneUsage(String subCat) {
            System.out.println(subCat + "\n" +
                    "Enter 10 char phone\n" +
                    "0 - Parent dir\n");
        }

    private void employeeSetPhone(String cat) {
        String subCat = cat + "SetPhone/";
        boolean isRun = true;
        employeeSetPhoneUsage(subCat);
        while (isRun) {
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        phone = _next;

                }
            }
        }
    }

    private void employeeSetSurnameUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - Enter surname\n" +
                "0 - Parent dir\n");
    }

    private void employeeSetSurname(String cat) {
        String subCat = cat + "SetSurname/";
        boolean isRun = true;
        employeeSetSurnameUsage(subCat);
        while (isRun) {
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        surname = _next;

                }
            }
        }
    }

    private void employeeSetNameUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "Enter name without spaces\n" +
                "0 - Parent dir\n");
    }

    private void employeeSetName(String cat) {
        String subCat = cat + "SetName/";
        boolean isRun = true;
        employeeSetNameUsage(subCat);
        while (isRun) {
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        name = _next;
                        isRun = false;
                        break;

                }
            }
        }
    }
}

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

    private void startUsage(String str) {
        System.out.println(
                str + "\n" +
                "\n" +
                "1 - Create\n" +
                "2 - Update\n" +
                "3 - Delete\n" +
                "4 - Reports\n" +
                "0 - exit");
    }

    public void start()
    {
        String cat = "\n/CRUD/";
        boolean isRun = true;

        while (isRun)  {
            startUsage(cat);
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
                        isRun = false;
                        System.exit(0);
                        break;
//                    default:
//                        usage(cat);
                }
            }
        }
    }

    private void createUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - Employee\n" +
                "2 - Department\n" +
                "0 - Parent dir");
    }

    private void create(String cat) {
        String subCat = cat + "Create/";
        boolean isxRun = true;

        while (isxRun) {
            createUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        employee(subCat);
                        break;
                    case "2":
                        department(subCat);
                        break;
                    case "0":
                        isxRun = false;
                        break;
                }
            }
        }
        //start();
    }
    ///////////////////////////////////////
    private void employeeUsage(String subCat) {
        System.out.println(subCat + "\n" +




                "1 - Set name\n" +
                "2 - Set surname\n" +
                "3 - Set phone\n" +
                "4 - Commit:\n" +
                "0 - ../\n\n" +
                "    Name = " + name +
                "    Surname = " + surname +
                "    Phone = " + phone);
    }

    private void employee(String cat) {
        phone = "";
        name = "";
        surname = "";
        String subCat = cat + "Employee/";

        boolean isRun = true;

        while (isRun) {
            employeeUsage(subCat);
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
                        phone = "";
                        name = "";
                        surname = "";
                        isRun = false;
                        break;
//                        System.out.println("wtf");

                }
            }
        }
//        start();
    }

    private void employeePersistUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "0 - ../\n" +
                "Enter - save\n");
    }

    private void employeePersist(String cat) {

        employeeService.create(name, surname, phone);
        name = "";
        surname = "";
        phone = "";
        System.out.println("created new entry");
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
        while (isRun) {
            employeeSetPhoneUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        phone = _next;
                        isRun = false;
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
                String next = sc.next();
                switch (next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        surname = next;
                        isRun = false;

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
                String next = sc.next();
                switch (next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        name = next;
                        isRun = false;
                }
            }
        }
    }


    private void departmentUsage(String subCat) {
        System.out.println(subCat + "\n" +




                "1 - Set department name\n" +
                "2 - Commit:\n" +
                "0 - ../\n\n" +
                "    Department name = " + name);
    }

    private void department(String cat) {
        name = "";
        String subCat = cat + "Department/";

        boolean isRun = true;

        while (isRun) {
            departmentUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        departmentSetName(subCat);
                        break;
                    case "2":
                        departmentPersist(subCat);
                        break;
                    case "0":
                        name = "";
                        isRun = false;
                        break;
                }
            }
        }
    }

    private void departmentSetNameUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "Enter name without spaces\n" +
                "0 - Parent dir\n");
    }

    private void departmentSetName(String cat) {
        String subCat = cat + "SetName/";
        boolean isRun = true;
        departmentSetNameUsage(subCat);
        while (isRun) {
            if (sc.hasNext()) {
                String next = sc.next();
                switch (next) {
                    case "0":
                        isRun = false;
                        break;
                    default:
                        name = next;
                        isRun = false;
                }
            }
        }
    }

    private void departmentPersist(String cat) {

        departmentService.create(name);
        name = "";
        System.out.println("created new entry");
    }
    ///////////////////////////////////////


}

package com.mtest.consoleui;

import com.mtest.server.EmployeeService;

import java.util.Scanner;

public class EmployeeCreate {
    private static String phone = "";
    private static String name = "";
    private static String surname = "";

    private static void menuUsage(String subCat) {
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



    static void menu(String subCat, Scanner sc) {

        boolean isRun = true;

        while (isRun) {
            menuUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        employeeSetName(subCat, sc);
                        break;
                    case "2":
                        employeeSetSurname(subCat, sc);
                        break;
                    case "3":
                        employeeSetPhone(subCat, sc);
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
    }
    private static void employeePersistUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "0 - ../\n" +
                "Enter - save\n");
    }

    private static void employeePersist(String cat) {

        new EmployeeService().create(name, surname, phone);
        name = "";
        surname = "";
        phone = "";
        System.out.println("created new entry");
    }

    private static void employeeSetPhoneUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "Enter 10 char phone\n" +
                "0 - Parent dir\n");
    }

    private static void employeeSetPhone(String cat, Scanner sc) {
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

    private static void employeeSetSurnameUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - Enter surname\n" +
                "0 - Parent dir\n");
    }

    private static void employeeSetSurname(String cat, Scanner sc) {
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

    private static void employeeSetNameUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "Enter name without spaces\n" +
                "0 - Parent dir\n");
    }

    static void employeeSetName(String cat, Scanner sc) {
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
}

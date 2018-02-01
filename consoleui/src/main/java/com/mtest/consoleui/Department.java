package com.mtest.consoleui;

import com.mtest.server.DepartmentService;

import java.util.Scanner;

public class Department {

    private static String name = "";

    private static void menuUsage(String subCat) {
        System.out.println(subCat + "\n" +

                "1 - Set department name\n" +
                "2 - Commit:\n" +
                "0 - ../\n\n" +
                "    Department name = " + name);
    }

    static void menu(String cat, Scanner sc, CRUD.Action action) {
        name = "";
        String subCat = cat + "Department/";

        boolean isRun = true;

        while (isRun) {
            menuUsage(subCat);
            if (sc.hasNext()) {
                String _next = sc.next();
                switch (_next) {
                    case "1":
                        departmentSetName(subCat, sc);
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

    private static void departmentSetNameUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "Enter name without spaces\n" +
                "0 - Parent dir\n");
    }

    private static void departmentSetName(String cat, Scanner sc) {
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

    private static void departmentPersist(String cat) {

        new DepartmentService().create(name);
        name = "";
        System.out.println("created new entry");
    }
}

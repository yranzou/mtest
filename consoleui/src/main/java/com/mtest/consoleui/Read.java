package com.mtest.consoleui;

import java.util.Scanner;

public class Read {
    private static void menuUsage(String subCat) {
        System.out.println(subCat + "\n" +
                "1 - Employee\n" +
                "2 - Department\n" +
                "0 - Parent dir");
    }

    static void menu(String cat, Scanner sc) {
        String subCat = cat + "Read/";
        boolean isRun = true;

        while (isRun) {
            menuUsage(subCat);
            if (sc.hasNext()) {
                String next = sc.next();
                switch (next) {
                    case "1":
                        Employee.menu(subCat, sc, CRUD.Action.READ);
                        break;
                    case "2":
                        Department.menu(subCat, sc, CRUD.Action.READ);
                        break;
                    case "0":
                        isRun = false;
                        break;
                }
            }
        }
    }
}

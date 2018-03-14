package com.mtest.consoleui;

import com.mtest.dao.EmployeeDao;
import com.mtest.model.Employee;

import java.util.List;
import java.util.Scanner;

public class TestDao {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
        boolean isRun = true;
        Scanner sc = new Scanner(System.in);
        while (isRun) {
            System.out.println("1 - getAll");
            System.out.println("0 - exit");
            if (sc.hasNext()) {
                String next = sc.next();
                switch (next) {
                    case "1":
                        for (Employee employee:employeeDao.getAll())
                        {
                            System.out.println(employee.getName());
                        }
                        break;
//                    case "2":
//                        Department.menu(subCat, sc, CRUD.Action.READ);
//                        break;
                    case "0":
                        isRun = false;
                        break;
                }
            }
        }
    }
}

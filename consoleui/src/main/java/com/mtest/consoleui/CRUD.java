package com.mtest.consoleui;

import com.mtest.server.DepartmentService;
import com.mtest.server.EmployeeService;

import java.util.Scanner;

/**
 * Created by yuri on 27.01.18.
 */
public class CRUD {
    private String phone;
    private String name;
    private String surname;
    enum Action {
        CREATE,
        READ,
        UPDADE,
        DESTROY
    }
//    private  sc;
//    private  employeeService;
//    private  departmentService;
    CRUD()
    {

    }

    private static void crudUsage(String str) {
        System.out.println(
                str + "\n" +
                        "\n" +
                        "1 - Create\n" +
                        "2 - Update\n" +
                        "3 - Delete\n" +
                        "4 - Reports\n" +
                        "0 - exit");
    }

    public static void start()
    {
        Scanner sc = new Scanner(System.in);
//        EmployeeService employeeService = new EmployeeService();
//        DepartmentService departmentService = new DepartmentService();
        String cat = "\n/CRUD/";
        boolean isRun = true;

        while (isRun)  {
            crudUsage(cat);
            if (sc.hasNext())
            {
                String next = sc.next();
                switch (next)
                {
                    case "1":
                        Create.menu(cat, sc);
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
                        Read.menu(cat, sc);
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


    ///////////////////////////////////////








    ///////////////////////////////////////


}

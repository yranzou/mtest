package com.mtest.webapp;

import com.mtest.model.Employee;
import com.mtest.server.common.EmployeeService;
import com.mtest.server.exception.ServerException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sun.misc.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * Created by yuri on 30.11.17.
 */
public class EditEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
        this.applicationContext.getBean(EmployeeService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("begin edit Employee");
        Employee employee = null;
//        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        String name = null; // = req.getParameter("name");
        String surName = null;// = req.getParameter("surname");
        String phone = null;// = req.getParameter("phone");
        byte[] photo = null;
        // =========
        InputStream inputStream = null; // input stream of the upload file

        int id;// = Integer.parseInt(req.getParameter("id"));

//        Part filePart = req.getPart("photo");
//        Collection<Part> reqParts = req.getParts();

//        Iterator iter = reqParts.iterator();

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);


// Parse the request

        List items = null;
        try {
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        Iterator iter = items != null ? items.iterator() : null;

        while (Objects.requireNonNull(iter).hasNext()) {
            FileItem item = (FileItem) iter.next();
            if (item.isFormField()) {
                switch (item.getFieldName()) {
                    case "name":
                        name = item.getString();
                        break;
                    case "surname":
                        surName = item.getString();
                        break;
                    case "phone":
                        phone = item.getString();
                        break;
                    case "id":
                        id = Integer.parseInt(item.getString());
                        try {
                            employee = employeeService.get(id);
                        } catch (ServerException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                inputStream = item.getInputStream();

                try {
                    photo = IOUtils.readFully(inputStream, -1, true);
                } catch (IOException e) {
                    System.out.printf("CATCH DEL");
                    e.printStackTrace();
                }

            }
        }

//        if (filePart != null) {
//            // prints out some information for debugging
//            System.out.println(filePart.getName());
//            System.out.println(filePart.getSize());
//            System.out.println(filePart.getContentType());
//
//            // obtains input stream of the upload file
//            inputStream = filePart.getInputStream();
//
//
//
////            employee.setPhoto(photo);
//        }

        // ===========



        if (photo != null && photo.length > 0) {
            employee.setPhoto(photo);
        } else
        {
            System.out.printf("PHOTO NOT SET - USING EXISTING VALUE");
        }
        employee.setName(name);
        employee.setSurname(surName);
        employee.setPhone(phone);

        employeeService.update(employee);
//        resp.sendRedirect("/displayEmployees");
        resp.sendRedirect(resp.encodeRedirectURL("displayEmployees"));
    }
}

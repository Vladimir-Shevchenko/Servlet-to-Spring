package com.AdminServlets;

import com.database.AdminPeriodicalsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
public class AdminPeriodicalsManagementServlet /* extends HttpServlet*/ {

    @Autowired
    AdminPeriodicalsDao adminPeriodicalsDao;

    @GetMapping("AdminPeriodicalsManagementServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if( request.getParameter("deleteId")!=null) {
            adminPeriodicalsDao.deletePeriodical(request.getParameter("deleteId"));
            response.sendRedirect("AdminPeriodicalsServlet");
        }else if(request.getParameter("editId")!=null){}
    }



    @PostMapping("AdminPeriodicalsManagementServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //ADD
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String description = request.getParameter("description");

        if(name!=null||price!=null||type!=null||description!=null) {
            if (name.equals("") || price.equals("") || type.equals("") || description.equals("")) {
                response.getWriter().println("Please fill all fields!");
            } else {
                AdminPeriodicalsDao.addPeriodical(name, price, type, description);
                response.sendRedirect("AdminPeriodicalsServlet");
            }
        }
        //EDIT
        String id = request.getParameter("Id");
        String editedName = request.getParameter("editedName");
        String editedPrice = request.getParameter("editedPrice");
        String editedType = request.getParameter("editedType");
        String editedDescription = request.getParameter("editedDescription");

        if(editedName==null||editedName.equals("")||editedPrice.equals("")||editedType.equals("")||editedDescription.equals("")) {
            response.getWriter().println("Please fill all fields!");
        }else{ adminPeriodicalsDao.editPeriodical(id, editedName, editedPrice, editedType, editedDescription);
            response.sendRedirect("AdminPeriodicalsServlet");}
    }
}

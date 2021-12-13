package com.PeriodicalsServlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


@Controller
public class ConfirmServlet {

   @GetMapping("ConfirmServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.getParameter("subscribe");
    HttpSession session = request.getSession();

        session.setAttribute("idConfirm", request.getParameter("id"));
        session.setAttribute("nameConfirm", request.getParameter("name"));
        session.setAttribute("priceConfirm", request.getParameter("price"));
        session.setAttribute("typeConfirm", request.getParameter("type"));
        session.setAttribute("descriptionConfirm", request.getParameter("description"));

        response.sendRedirect("confirm.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

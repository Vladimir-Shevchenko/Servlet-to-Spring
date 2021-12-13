package com.RegisterLoginServlets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class I18Servlet {


   @GetMapping("I18Servlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

request.setCharacterEncoding("UTF-8");


        String lang = request.getParameter("lan");
        request.getSession().setAttribute("lang",lang );

        if(request.getParameter("go")!=null) {
            response.sendRedirect("login.jsp");
        }else if (request.getParameter("goPer")!=null){
            response.sendRedirect("PeriodicalServlet");
        }else if(request.getParameter("goProf")!=null){
            response.sendRedirect("ProfilServlet");
        }else if(request.getParameter("goReplenish")!=null){
            response.sendRedirect("ReplenishServlet");
        }else if(request.getParameter("goUserLoginSuccess")!=null){
            response.sendRedirect("loginSuccess.jsp");
        }else if(request.getParameter("HelloAdmin")!=null){
            response.sendRedirect("helloAdmin.jsp");
        }else if(request.getParameter("AdminUsers")!=null){
            response.sendRedirect("adminUsers.jsp");
        }

    }




  @PostMapping(value = "I18Servlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String lang = request.getParameter("lan");
        request.getSession().setAttribute("lang",lang );
        response.sendRedirect("login.jsp");
    }
}

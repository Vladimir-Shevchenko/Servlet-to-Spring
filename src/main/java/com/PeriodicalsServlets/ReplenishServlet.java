package com.PeriodicalsServlets;

import com.database.ReplenishDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Controller

public class ReplenishServlet  {

    @Autowired
    ReplenishDao replenishDao;


    @GetMapping("ReplenishServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("replenish.jsp");
    }


   @PostMapping("ReplenishServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String amount = (String)request.getParameter("amount");
       String username = (String)request.getSession().getAttribute("username");
       replenishDao.replenish(amount, username);

        response.sendRedirect("successPayment.jsp");
    }
}

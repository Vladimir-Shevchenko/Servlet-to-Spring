package com.PeriodicalsServlets;

import com.database.AddOrderDao;
import com.database.MoneyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Controller

public class ThankyouServlet {

    @Autowired
    MoneyDao moneyDao;


   @GetMapping("ThankyouServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       HttpSession session = request.getSession();

        String periodical_id =  (String) session.getAttribute("idConfirm");
        String username =  (String) session.getAttribute("username");

        String price_ =  (String)session.getAttribute("priceConfirm");
        price_ = price_.substring(0,price_.length()-1);
        int price=Integer.parseInt(price_);
        int myMoney = moneyDao.getMoney(username);

       if(myMoney>=price) {
           moneyDao.updateMoney((myMoney-price)+"",username);
           AddOrderDao.insertOrder(periodical_id, username);

           response.sendRedirect("thankyou.jsp");

       }else {response.getWriter().println("Its not enough money! Please replenish your bill!");}

    }






}

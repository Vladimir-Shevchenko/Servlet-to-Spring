package com.PeriodicalsServlets;

import com.Bean.Periodical;
import com.database.MoneyDao;
import com.database.ProfilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfilServlet {

    static List<Periodical> myOrderslist=new ArrayList<>();

    @Autowired
    ProfilDao profilDao;
    @Autowired
    MoneyDao moneyDao;


    @GetMapping("ProfilServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        myOrderslist = profilDao.getMyPeriodicals(username);
        int money = moneyDao.getMoney(username);


        session.setAttribute("myOrderslist", myOrderslist);
        session.setAttribute("money", money);
        response.sendRedirect("profil.jsp");
    }


}

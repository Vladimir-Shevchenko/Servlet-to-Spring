package com.AdminServlets;

import com.Bean.Member;
import com.database.AllUsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminUsersServlet {

    @Autowired
    AllUsersDao allUsersDao;
    @GetMapping("AdminUsersServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //BLOCK USER
        if(request.getParameter("blockUser")!=null){
            allUsersDao.blockUser(request.getParameter("blockUser"));
        }

        //UNBLOCK USER
        if(request.getParameter("unblockUser")!=null){
            allUsersDao.unblockUser(request.getParameter("unblockUser"));
        }

        //FILL PAGE WITH LIST
        List<Member> mList = allUsersDao.getAllUsers();
        request.getSession().setAttribute("mList",mList);
        System.out.println("mlist:  "+mList);
        response.sendRedirect("adminUsers.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

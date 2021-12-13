package com.RegisterLoginServlets;
import com.Bean.LoginBean;
import com.database.LoginDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;

@RestController
@CrossOrigin
@RequestMapping("/")
public class LoginServlet  {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Autowired
    LoginBean loginBean;
    @Autowired
    LoginDao loginDao;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    if(username!=null) { request.setAttribute("un", username);}

        String lang = request.getParameter("lan");
        request.getSession().setAttribute("lang",lang );
        response.sendRedirect("login.jsp");
    }



     @RequestMapping(value = "login", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username!=null){
        request.setAttribute("un", username);

        if(username.equals("admin")&&password.equals("admin")){
            HttpSession session =   request.getSession();
            session.setAttribute("username", username);
            request.setAttribute("un", username);
            session.setMaxInactiveInterval(145);

            response.sendRedirect("helloAdmin.jsp"); return;}


        String sha = sha256(password);
        password = sha;

        loginBean.setUsername(username);
        loginBean.setPassword(password);

        if(loginDao.validate(loginBean)){
         HttpSession session =   request.getSession();
         session.setAttribute("username", username);
            request.setAttribute("un", username);
            session.setMaxInactiveInterval(145);


            response.sendRedirect("loginSuccess.jsp");
        }else {response.sendRedirect("tryagain.jsp");}
    }
    }




    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex); } return hexString.toString();
        } catch(Exception ex){
            log.error("Ошибка", ex);
            throw new RuntimeException(ex);
        }
    }



}

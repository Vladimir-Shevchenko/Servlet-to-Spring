package com.RegisterLoginServlets;

import com.Bean.Member;
import com.database.RegisterDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class RegisterServlet extends HttpServlet {

   private final RegisterDao registerDao;



    @PostMapping("RegisterServlet")
    @RequestMapping(value = "RegisterServlet", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");


        String sha = sha256(password);
        password = sha;

        Member member = new Member(uname,password,email,phone);

        String result =  registerDao.insert(member);
        response.getWriter().print(result);
        response.sendRedirect("registerSuccess.jsp");
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
        } catch(Exception ex){ throw new RuntimeException(ex); }
    }


}

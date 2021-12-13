package com.PeriodicalsServlets;

import com.Bean.Periodical;
import com.database.PeriodicalsDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class PeriodicalServlet  {

    @Autowired
    PeriodicalsDao pd;



    private static final Logger log = Logger.getLogger(PeriodicalServlet.class);
    static List<Periodical> list=new ArrayList<>();




    @GetMapping("PeriodicalServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Periodical> periodicalsList= /*PeriodicalsDao*/pd.getPeriodicals();
        list = periodicalsList;


        if(request.getParameter("letter")!=null){
        String lett = request.getParameter("letter");
        list = periodicalsList.stream().filter(n->n.getName().startsWith(lett)).collect(Collectors.toList());
}


        HttpSession session = request.getSession();
        session.setAttribute("list", list);

        request.getRequestDispatcher("periodicals.jsp").forward(request,response);
    }


        @PostMapping("PeriodicalServlet")

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Это информационное сообщение!");

        List<Periodical> sortByNameList = new ArrayList<>();

        String search = request.getParameter("search_String");
        String option = request.getParameter("search_option");

        if(search!=null&&option!=null&&option.equals("Theme")) {
         sortByNameList = list.stream()
                    .filter(n -> n.getName().contains(search))
                    .sorted(Comparator.comparing(Periodical::getType))
                    .collect(Collectors.toList());
        }


      else  if(search!=null&&option!=null&&option.equals("Title")) {
            sortByNameList = list.stream()
                    .filter(n -> n.getName().contains(search))
                    .sorted(Comparator.comparing(Periodical::getName))
                    .collect(Collectors.toList());
        }


        else  if(search!=null&&option!=null&&option.equals("Price")) {
            sortByNameList = list.stream()
                    .filter(n -> n.getName().contains(search))
                    .sorted(Comparator.comparing(Periodical::getPrice))
                    .collect(Collectors.toList());
        }


      else  if(search!=null) {
            sortByNameList = list.stream()
                    .filter(n -> n.getName().contains(search))
                    .sorted(Comparator.comparing(Periodical::getType))
                    .collect(Collectors.toList());
        }


        HttpSession session = request.getSession();
        session.setAttribute("list", sortByNameList);

        request.getRequestDispatcher("periodicals.jsp").forward(request, response);
    }
}

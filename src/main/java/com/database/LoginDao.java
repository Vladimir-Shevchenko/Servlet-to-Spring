package com.database;

import com.Bean.LoginBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.sql.*;


@Service
public class LoginDao {

    private static final Logger log = Logger.getLogger(LoginDao.class);


    public boolean validate(LoginBean loginBean) {

        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean status = false;
        String sql = "select * from member where uname = ? and password = ?";
        PreparedStatement ps;

        try {
            ps =   connection.prepareStatement(sql);
            ps.setString(1, loginBean.getUsername());
            ps.setString(2, loginBean.getPassword());

            ResultSet rs = ps.executeQuery();


            while (rs.next()){
            String isBlocked =   rs.getString("isBlocked");
                status = true;
                if(isBlocked==null){isBlocked="Blocked";}
               if(isBlocked==null||isBlocked.equals("Blocked")) {
                   status = false;
               }
            }
        } catch (SQLException e) {
            log.error("Ошибка", e);
        }finally {

            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Ошибка", e);          }
        }
        return status;
    }
}

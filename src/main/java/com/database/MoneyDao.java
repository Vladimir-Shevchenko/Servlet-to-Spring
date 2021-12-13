package com.database;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class MoneyDao {

    private static final Logger log = Logger.getLogger(MoneyDao.class);


    public static int getMoney(String username) {

        int money = 0;
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select money from money where username = '"+username+"'";
        PreparedStatement ps;

        try {
            ps =   connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                 money =  rs.getInt("money");

            }//while
            return money;

        } catch (SQLException e) {
            log.error("Это сообщение ошибки", e);
        }finally {

            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Это сообщение ошибки", e);           }
        }

        return -1;

    }





    public  static String updateMoney(String amount, String username){

        Connection conn = ConnectionPool.getInstance().getConnection();
        String sql = "update money set money = ? where username = ?";

        try {

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 ,  Integer.parseInt(amount));
            ps.setString(2,username);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                log.error("Это сообщение ошибки", ex);

            }
            log.error("Это сообщение ошибки", e);
        }finally {

            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                log.error("Это сообщение ошибки", e);
            }
        }
        return "Condrats, you paid successfully!";
    }




}

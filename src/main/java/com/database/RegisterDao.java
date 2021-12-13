package com.database;

import com.Bean.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Service
public class RegisterDao {


    public   String insert(Member member){

        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "insert into member (uname, password, email,phone, isBlocked) values(?,?,?,?,?)";

        try {

            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,member.getUname());
            ps.setString(2,member.getPassword());
            ps.setString(3,member.getEmail());
            ps.setString(4,member.getPhone());
            ps.setString(5, "");
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.error("Это сообщение ошибки", ex);

            }
            log.error("Это сообщение ошибки", e);
        }finally {

                ConnectionPool.getInstance().closeAndSetAutoCommit(con);

        }
        return "Condrats, you have registered successfully!";
    }
}

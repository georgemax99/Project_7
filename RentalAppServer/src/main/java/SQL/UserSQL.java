package SQL;

import Beans.User;
import Util.SQLUtil;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.*;

public class UserSQL {
    public Long create(User user) {
        Long id = -1L;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "insert into users ( " +
                    "name, email, password" +
                    ") values (?, ?, ?) ";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next())
                id = rs.getLong(1);
        } catch (SQLException e) {
            System.out.println("Database failed to update");
        } finally {
            SQLUtil.close(conn, stmt, rs);
        }
        return id;
    }

    public User readByEmailAndPassword(String email, String password) {
        User user = null;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from users where email = ? and password = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                BeanProcessor bp = new BeanProcessor();
                user = bp.toBean(rs, User.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database failed to query.");
        } finally {
            SQLUtil.close(conn, stmt, rs);
        }
        return user;
    }

    public User readByEmail(String email) {
        User user = null;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from users where email = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                BeanProcessor bp = new BeanProcessor();
                user = bp.toBean(rs, User.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database failed to query.");
        } finally {
            SQLUtil.close(conn, stmt, rs);
        }
        return user;
    }
}

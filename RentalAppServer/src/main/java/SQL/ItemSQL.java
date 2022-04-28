package SQL;

import Beans.Item;
import Beans.User;
import Util.SQLUtil;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.*;
import java.util.List;

public class ItemSQL {
    public Long create(Item item) {
        Long id = -1L;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "insert into items ( " +
                    "userId, title, description, price, imgUrl" +
                    ") values (?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, item.getUserId());
            stmt.setString(2, item.getTitle());
            stmt.setString(3, item.getDescription());
            stmt.setDouble(4, item.getPrice());
            stmt.setString(5, item.getImgUrl());
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

    public List<Item> readAll() {
        List<Item> itemList = null;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from items where isRented = 0 ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            BeanProcessor bp = new BeanProcessor();
            itemList = bp.toBeanList(rs, Item.class);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database failed to query.");
        } finally {
            SQLUtil.close(conn, stmt, rs);
        }
        return itemList;
    }

    public Item readById(Long id) {
        Item item = null;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from items where id = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                BeanProcessor bp = new BeanProcessor();
                item = bp.toBean(rs, Item.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database failed to query.");
        } finally {
            SQLUtil.close(conn, stmt, rs);
        }
        return item;
    }

    public void updateIsRented(Long id) {
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "update items set isRented = 1 where id = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to increment upvotes.");
        } finally {
            SQLUtil.close(conn, stmt);
        }
    }
}

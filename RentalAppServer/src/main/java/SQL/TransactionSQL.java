package SQL;

import Beans.Item;
import Beans.Transaction;
import Util.SQLUtil;

import java.sql.*;

public class TransactionSQL {
    public Long create(Transaction transaction) {
        Long id = -1L;
        Connection conn = SQLUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "insert into transactions ( " +
                    "itemId, buyerId, pickupTime, dropOffTime" +
                    ") values (?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, transaction.getItemId());
            stmt.setLong(2, transaction.getBuyerId());
            stmt.setLong(3, transaction.getPickupTime());
            stmt.setLong(4, transaction.getDropOffTime());
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
}

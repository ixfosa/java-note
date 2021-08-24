package environments;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ixfosa on 2021/3/25 15:47
 */
public class MyTransaction extends JdbcTransaction implements Transaction {
    public MyTransaction(DataSource ds, TransactionIsolationLevel desiredLevel,
                         boolean desiredAutoCommit) {
        super(ds, desiredLevel, desiredAutoCommit);
    }
    public MyTransaction(Connection connection) {
        super(connection);
    }
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }
    public void commit() throws SQLException {
        super.commit();
    }
    public void rollback() throws SQLException {
        super.rollback();
    }
    public void close() throws SQLException {
        super.close();
    }
    public Integer getTimeout() throws SQLException {
        return super.getTimeout();
    }
}

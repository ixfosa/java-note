package environments;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by ixfosa on 2021/3/25 15:46
 */
public class MyTransactionFactory implements TransactionFactory {
    @Override
    public void setProperties(Properties props) {
    }

    @Override
    public Transaction newTransaction(Connection connection) {
        return new MyTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
        return new MyTransaction(dataSource, transactionIsolationLevel, b);
    }
}

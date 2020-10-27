package ru.yandex;

        import java.beans.PropertyVetoException;
        import java.sql.Connection;
        import java.sql.SQLException;
        import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    private static ComboPooledDataSource cpdc = new ComboPooledDataSource();

        public  static void dataSource() throws PropertyVetoException {

        cpdc.setJdbcUrl("ec2-52-87-135-240.compute-1.amazonaws.com:5432");
        cpdc.setDriverClass("org.postgresql.Driver");
        cpdc.setUser("root");
        cpdc.setPassword("admin");
        cpdc.setInitialPoolSize(5);
        cpdc.setMinPoolSize(5);
        cpdc.setAcquireIncrement(5);
        cpdc.setMaxPoolSize(20);
        cpdc.setMaxStatements(100);

    }

    public static Connection getConnection() throws SQLException {
            return cpdc.getConnection();
    }

}

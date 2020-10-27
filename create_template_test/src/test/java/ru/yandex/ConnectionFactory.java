package ru.yandex;

        import java.sql.Connection;
        import java.sql.SQLException;
        import org.apache.commons.dbcp.*;


public class ConnectionFactory {

    private static BasicDataSource dataSource;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("ec2-52-87-135-240.compute-1.amazonaws.com:5432");
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUsername("root"); // проставил специально, не указывая
            dataSource.setPassword("admin"); // пароль и логин
        }
        return dataSource.getConnection();
    }
}
package net.globemc.gclaim;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static Database dbInstance = null;

    private DatabaseConfig config;
    private MysqlDataSource dataSource;

    public static Database getInstance() {
        return dbInstance;
    }

    public static void loadFromConfig(File configFile) throws SQLException {
        dbInstance = new Database(configFile);
        dbInstance.testConnection();
    }

    private Database(File configFile) throws SQLException {
        config = new DatabaseConfig(configFile);
        dataSource = new MysqlConnectionPoolDataSource();

        dataSource.setServerName(config.getHost());
        dataSource.setPortNumber(config.getPort());
        dataSource.setDatabaseName(config.getDb());
        dataSource.setUser(config.getUser());
        dataSource.setPassword(config.getPassword());

        System.out.println(config.getHost());
        System.out.println(config.getUser());
        System.out.println(config.getPassword());
        System.out.println(config.getPort());
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

    public DatabaseConfig getConfig() {
        return config;
    }

    private void testConnection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            if (!conn.isValid(1)) {
                throw new SQLException(("Could not establish a connection to the database."));
            }
        }
    }
}

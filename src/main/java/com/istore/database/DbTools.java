package com.istore.database;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbTools {
    private final String url = "jdbc:h2:file:./db/istore;AUTO_SERVER=true";
    private final String user = "sa";
    private final String passwd = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, passwd);
    }

    public void initDatabase() {
        Map<String, Object> config = new HashMap<>();
        config.put("liquibase.licenseKey", "YOUR_PRO_KEY");

        try {
            Scope.child(config, () -> {
                Connection connection = getConnection();
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

                Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update();
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
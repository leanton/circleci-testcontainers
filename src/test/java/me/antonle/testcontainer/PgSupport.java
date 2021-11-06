package me.antonle.testcontainer;

import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSupport {

    protected static final PostgreSQLContainer<?> PG;
    protected static final Connection PG_CLIENT;

    public static final String DB_URL;
    public static final String DB_NAME = "db";
    private static final String DB_USER = "test";

    static {
        final var circleCI = Boolean.parseBoolean(System.getenv("CIRCLECI"));
        if (circleCI) {
            DB_URL = "jdbc:postgresql://localhost:5432/backend";
            PG = null;
        } else {
            PG = new PostgreSQLContainer<>("postgres:9.6.12")
                .withDatabaseName(DB_NAME)
                .withUsername(DB_USER)
                .withPassword(DB_USER);
            PG.start();
            DB_URL = PG.getJdbcUrl();
        }

        try {
            PG_CLIENT = DriverManager.getConnection(DB_URL, DB_USER, DB_USER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

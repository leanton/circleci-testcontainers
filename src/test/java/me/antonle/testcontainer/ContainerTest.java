package me.antonle.testcontainer;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainerTest extends PgSupport {

    @Test
    void should_connect_to_database() {

        try (var statement = PG_CLIENT.createStatement()) {
            var execute = statement.execute("SELECT 1");
            assertTrue(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

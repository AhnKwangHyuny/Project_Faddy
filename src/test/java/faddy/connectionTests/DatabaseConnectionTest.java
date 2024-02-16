package faddy.connectionTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void whenInitialized_thenDataSourceIsNotNull() {
        assertThat(dataSource).isNotNull();
    }


    @Test
    public void whenInitialized_thenDatabaseNameIsKnown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String databaseName = connection.getCatalog();
            System.out.println("databaseName = " + databaseName);
            assertThat(databaseName).isEqualTo("faddy_db");
        }
    }

    @Test
    public void printDatabaseInfo() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("Username: " + connection.getMetaData().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
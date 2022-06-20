import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class ConnectionFactoryTest {

    public ConnectionFactory connectionFactory;

    @BeforeEach
    public void initialize() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Test
    void mustReceiveAConnection() {
        try {
            this.connectionFactory.recoverConnection();
            System.out.println("Connected!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
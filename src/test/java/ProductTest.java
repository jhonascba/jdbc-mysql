import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class ProductTest {

    public Product product;

    @BeforeEach
    public void initialize() throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        this.product = new Product(factory);
    }

    @Test
    void mustInsertAProductIntoDatabase() throws SQLException {
        try {
            this.product.insertNewProduct("Teclado", "Teclado Mecânico");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void mustReadTheDataFromDatabase() throws SQLException {
        try{
            System.out.println(this.product.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void mustUpdateInformationFromDatabase() throws SQLException {
        try {
            this.product.updateProduct(2, "GELADEIRA", "GELADEIRA AZUL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void mustDeleteAProductFromDatabase() throws SQLException {
        try{
            this.product.deleteProduct(29);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
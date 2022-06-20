import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private Connection connection;

    public Product(ConnectionFactory factory) throws SQLException {
        this.connection = factory.recoverConnection();
        this.connection.setAutoCommit(false);
    }

    private PreparedStatement createInsertStatement() throws SQLException {
        return this.connection.prepareStatement(
                "INSERT INTO produto (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
    }

    private PreparedStatement createSelectStatement() throws SQLException {
        return this.connection.prepareStatement(
                "SELECT id, nome, descricao FROM produto"
        );
    }

    private PreparedStatement createUpdateStatement() throws SQLException {
        return this.connection.prepareStatement(
                "UPDATE produto SET nome=?, descricao=? WHERE id=?;"
        );
    }

    private PreparedStatement createDeleteStatement() throws SQLException {
        return this.connection.prepareStatement(
                "DELETE FROM produto WHERE id = ?"
        );
    }

    private Integer getCreatedId(PreparedStatement stm) throws SQLException {
        ResultSet rst = stm.getGeneratedKeys();
        Integer id = null;
        while (rst.next()) {
            id = rst.getInt(1);
        }
        return id;
    }

    public void insertNewProduct(String nome, String descricao) throws SQLException {
        try (PreparedStatement stm = this.createInsertStatement()) {
            stm.setString(1, nome);
            stm.setString(2, descricao);
            stm.execute();
            this.connection.commit();
            System.out.println("Product sucessfully introduced with ID " + getCreatedId(stm));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK EXECUTED");
            connection.rollback();
        }
    }

    public List getData() {
        try (PreparedStatement stm = this.createSelectStatement()) {
            stm.execute();
            ResultSet rst = stm.getResultSet();
            List<DataBaseResult> dataBaseResults = new ArrayList<>();

            while (rst.next()) {
                Integer id = rst.getInt("id");
                String nome = rst.getString("nome");
                String descricao = rst.getString("descricao");

                dataBaseResults.add(new DataBaseResult(id, nome, descricao));
            }

            return dataBaseResults;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("It wasn't possible get the elements");
        }
        return null;
    }

    public void updateProduct(int id, String nome, String descricao) throws SQLException {
        try(PreparedStatement stm = this.createUpdateStatement()) {
            stm.setInt(3, id);
            stm.setString(1, nome);
            stm.setString(2, descricao);
            stm.execute();
            this.connection.commit();
            System.out.println("Amount of updated lines: " + stm.getUpdateCount());
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            System.out.println("ROLLBACK EXECUTED");
        }
    }

    public void deleteProduct(int id) throws SQLException {
        try (PreparedStatement stm = this.createDeleteStatement()) {
            stm.setInt(1, id);
            stm.execute();
            this.connection.commit();
            System.out.println("Amount of updated lines: " + stm.getUpdateCount());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK EXECUTED");
            connection.rollback();
        }
    }


}

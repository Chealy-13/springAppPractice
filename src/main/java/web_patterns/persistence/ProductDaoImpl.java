package web_patterns.persistence;

import web_patterns.business.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends MySQLDao implements ProductDao {

    public ProductDaoImpl() {
        super();
    }

    public ProductDaoImpl(Connection conn) {
        super(conn);
    }

    public ProductDaoImpl(String propertiesFilename) {
        super(propertiesFilename);
    }

    @Override
    public Product getByProductCode(String productCode) {
        Product product = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE productCode = ?")) {
            // Parameterize the query
            ps.setString(1, productCode);

            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = mapRow(rs);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when executing SQL or processing results.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
            e.printStackTrace();
        } finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        Connection conn = super.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM products")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = mapRow(rs);
                    products.add(product);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when executing SQL or processing results.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution.");
            e.printStackTrace();
        } finally {
            super.freeConnection(conn);
        }

        return products;
    }

    @Override
    public boolean deleteByProductCode(String productCode) {
        int rowsAffected = 0;

        Connection conn = super.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE productCode = ?")) {
            ps.setString(1, productCode);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare/execute SQL.");
            e.printStackTrace();
        } finally {
            super.freeConnection(conn);
        }

        return rowsAffected > 0;
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        // Map the ResultSet to a Product object
        return Product.builder()
                .productCode(rs.getString("productCode"))
                .productName(rs.getString("productName"))
                .productLine(rs.getString("productLine"))
                .productScale(rs.getString("productScale"))
                .productVendor(rs.getString("productVendor"))
                .productDescription(rs.getString("productDescription"))
                .quantityInStock(rs.getInt("quantityInStock"))
                .buyPrice(rs.getDouble("buyPrice"))
                .msrp(rs.getDouble("msrp"))
                .build();
    }

    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoImpl("database.properties");
        List<Product> products = productDao.getAllProducts();
        System.out.println(products);

        System.out.println("------------------------------");
        System.out.println("Product with code S72_3212: " + productDao.getByProductCode("S72_3212"));
    }

//    @Override
//    public Product getById(int id) {
//        return null;
//    }
//
//    @Override
//    public List<Product> getAllCustomers() {
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(int id) {
//        return false;
//    }
}

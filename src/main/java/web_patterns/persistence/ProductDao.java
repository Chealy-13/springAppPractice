package web_patterns.persistence;


import web_patterns.business.Customer;
import web_patterns.business.Product;

import java.util.List;

public interface ProductDao {
    Product getByProductCode(String productCode);

    List<Product> getAllProducts();

    boolean deleteByProductCode(String productCode);

//    public Product getById(int id);
//    public List<Product> getAllCustomers();
//    public boolean deleteById(int id);
}

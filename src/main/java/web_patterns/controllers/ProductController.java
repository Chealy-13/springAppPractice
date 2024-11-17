package web_patterns.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web_patterns.business.Customer;
import web_patterns.business.Product;
import web_patterns.persistence.CustomerDao;
import web_patterns.persistence.CustomerDaoImpl;
import web_patterns.persistence.ProductDao;
import web_patterns.persistence.ProductDaoImpl;

import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/viewProducts")
    public String processRequest(Model model) {
        ProductDao productDao = new ProductDaoImpl("database.properties");
        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products", products);

        return "products";
    }
}
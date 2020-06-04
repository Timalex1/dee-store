package com.deestore.v1.webapp.bootstrap;

import com.deestore.v1.webapp.domains.*;
import com.deestore.v1.webapp.domains.security.Role;
import com.deestore.v1.webapp.enums.OrderStatus;
import com.deestore.v1.webapp.repositories.CategoryRepository;
import com.deestore.v1.webapp.repositories.UserRepository;
import com.deestore.v1.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.Double
        ;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DBSeeder implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {

        loadProducts();
        loadUsersAndCustomers();
        loadCategories();
        loadProductsIntoCategories();
//        loadCarts();
        loadOrderHistory();
        loadRoles();
//        assignUsersToDefaultRole();
        assignUsersToAdminRole();
    }

    private void loadCategories() {

        Category fashion = new Category();
        fashion.setName("Fashion");
        categoryRepository.save(fashion);

        Category Electronics = new Category();
        Electronics.setName("Electronics");
        Electronics.setParent(fashion);
        categoryRepository.save(Electronics);

        Category Kitchen = new Category();
        Kitchen.setName("Kitchen Utensils");
        Kitchen.setParent(fashion);
        categoryRepository.save(Kitchen);

        Category child1 = new Category("child1");
        child1.setParent(Electronics);
        Category child2 = new Category("child2");
        child2.setParent(Kitchen);
        Category child3 = new Category("child3");
        child3.setParent(child1);
        categoryRepository.save(child1);
        categoryRepository.save(child2);
        categoryRepository.save(child3);
    }


    private void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("anewman");
        user1.setPassword("password");


        Customer customer1 = new Customer();
//        customer1.setCustomerId(1L);
        customer1.setFirstName("Alfred");
        customer1.setLastName("Newman");
        customer1.setBillingAddress(new BillingAddress()); // refactor to use embedded BillingAddress object
        customer1.getBillingAddress().setAddress("1 Alfred Lane");
        customer1.getBillingAddress().setCity("Alfred");
        customer1.getBillingAddress().setState("Maine");
        customer1.getBillingAddress().setZipCode("04291");
//        customer1.setUserRegDate(ld);
        customer1.setEmail("alfred@gmail.com");
        customer1.setCustomerPhone("207-101-1001");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("bschuster");
        user2.setPassword("password");

        Customer customer2 = new Customer();
//        customer2.setCustomerId(2L);
        customer2.setFirstName("Brad");
        customer2.setLastName("Schuster");
        customer2.setBillingAddress(new BillingAddress()); // refactor to use embedded BillingAddress object
        customer2.getBillingAddress().setAddress("2 Bradford Street");
        customer2.getBillingAddress().setCity("Waterville");
//        customer2.setUserRegDate(ld);
        customer2.getBillingAddress().setState("Maine");
        customer2.getBillingAddress().setZipCode("04902");
        customer2.setEmail("bschuster@yahoo.com");
        customer2.setCustomerPhone("207-202-2002");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("canthracks");
        user3.setPassword("password");

        Customer customer3 = new Customer();
//        customer3.setCustomerId(3L);
        customer3.setFirstName("Claire");
        customer3.setLastName("Anthracks");
//        customer3.setUserRegDate(ld);
        customer3.setBillingAddress(new BillingAddress()); // refactor to use embedded BillingAddress object
        customer3.getBillingAddress().setAddress("3 Claire Avenue");
        customer3.getBillingAddress().setCity("Portland");
        customer3.getBillingAddress().setState("Maine");
        customer3.getBillingAddress().setZipCode("04033");
        customer3.setEmail("claireax@ygmail.com");
        customer3.setCustomerPhone("207-302-3102");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);

        User user4 = new User();
        user4.setUsername("dlefleur");
        user4.setPassword("password");

        Customer customer4 = new Customer();
//        customer4.setCustomerId(4L);
        customer4.setFirstName("Dennis");
        customer4.setLastName("LeFleur");
//        customer4.setUserRegDate(ld);
        customer4.setBillingAddress(new BillingAddress()); // refactor to use embedded BillingAddress object
        customer4.getBillingAddress().setAddress("4 Dentist Road");
        customer4.getBillingAddress().setCity("Bangor");
        customer1.getBillingAddress().setState("Maine");
        customer1.getBillingAddress().setZipCode("04734");
        customer4.setEmail("claiseax@ygmail.com");
        customer4.setCustomerPhone("207-402-4000");
        user4.setCustomer(customer4);
        userService.saveOrUpdate(user4);

        User user5 = new User();
        user5.setUsername("efrommage");
        user5.setPassword("password");

        Customer customer5 = new Customer();
//        customer5.setCustomerId(5L);
        customer5.setFirstName("Enid");
        customer5.setLastName("Frommage");
//        customer5.setUserRegDate(ld);
        customer5.setBillingAddress(new BillingAddress()); // refactor to use embedded BillingAddress object
        customer5.getBillingAddress().setAddress("5 Cheese Way");
        customer5.getBillingAddress().setCity("South Paris");
        customer5.getBillingAddress().setState("Maine");
        customer5.getBillingAddress().setZipCode("04255");
        customer5.setEmail("cheezy@ygmail.com");
        customer5.setCustomerPhone("207-502-5500");
        user5.setCustomer(customer5);
        userService.saveOrUpdate(user5);
    }

    private void loadProducts() {

        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductName("Panasonic");
        product1.setProductDescription("Product 1");
        product1.setProductPrice(new Double(
                "11.99"));
        product1.setProductImage("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setProductName("Canon");
        product2.setProductDescription("Product 2");
        product2.setProductPrice(new Double(
                "12.99"));
        product2.setProductImage("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setProductName("Nikon");
        product3.setProductDescription("Product 3");
        product3.setProductPrice(new Double(
                "13.99"));
        product3.setProductImage("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setProductName("Panasonic2");
        product4.setProductDescription("Product 4");
        product4.setProductPrice(new Double(
                "14.99"));
        product4.setProductImage("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setProductName("Panasonic3");
        product5.setProductDescription("Product 5");
        product5.setProductPrice(new Double(
                "15.99"));
        product5.setProductImage("http://example.com/product5");
        productService.saveOrUpdate(product5);

    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartItem cartDetail = new CartItem();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });

    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });

        });


    }

    private void loadProductsIntoCategories(){
        Set<Category> categoryList = categoryRepository.findByParentIsNull();

        for (Category cat : categoryList ){
            recursiveTree(cat);
        }
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                });
            }
        });
    }

    public void recursiveTree(Category cat) {
        System.out.println(cat.getName());
        List<Product> products = (List<Product>) productService.listAll();

        if (cat.getChildren().size() > 0) {
            for (Category c : cat.getChildren()) {
                recursiveTree(c);
                if (c.getName().equalsIgnoreCase("child3")){
                    products.forEach( product -> {
//                        product.addProductToCategory(c);
//                        productService.saveOrUpdate(product);
                    });
                }
            }
        }

    }
}

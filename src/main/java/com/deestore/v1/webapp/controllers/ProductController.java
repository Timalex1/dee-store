package com.deestore.v1.webapp.controllers;


import com.deestore.v1.webapp.commands.ProductForm;
import com.deestore.v1.webapp.converters.ProductFormToProduct;
import com.deestore.v1.webapp.converters.ProductToProductForm;
import com.deestore.v1.webapp.domains.Category;
import com.deestore.v1.webapp.domains.Product;
import com.deestore.v1.webapp.repositories.CategoryRepository;
import com.deestore.v1.webapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by donaldsmallidge on 9/14/16.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductToProductForm productToProductForm;
    private ProductFormToProduct productFormToProduct;
    @Autowired
    private CategoryRepository categoryRepository;

    List<Category> cats = new ArrayList<>();


    
    @Autowired
    public void setProductService(ProductService productService) {

        this.productService = productService;
    }

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {

        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @RequestMapping("/product/list")
    public String listProducts(Model model, Principal pr){

        Map<String, Object> map = new HashMap<>();
        map.put("products", productService.listAll());

        model.addAllAttributes(map);

        return "admin/product/product_list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable Long id, Model model) {

        model.addAttribute("product", productService.getById(id));

        return "admin/product/product_details";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);

        return "admin/product/product_add";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", cats);

        return "admin/product/product_add";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/product/product_add";
        }

        ProductForm savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId() +"?success=true&productname=" + savedProduct.getName();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable Long id) {

        Product product = productService.getById(id);
        productService.delete(id);


        return "redirect:/product/list" + "?success=true&productname="+product.getProductName();
    }

    //Recursive method to loop through into the Sub Categories of Products
    public void recursiveTree(Category cat) {
        if (cat.getChildren().size() > 0) {
            for (Category c : cat.getChildren()) {
                if(c.getChildren().size() == 0){
                    cats.add(c);
                }
                recursiveTree(c);
            }
        }
    }
}

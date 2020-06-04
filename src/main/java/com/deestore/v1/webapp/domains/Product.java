package com.deestore.v1.webapp.domains;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Setter @Getter
public class Product implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "manufacturer")
    private String productManufacturer;

//    @NotEmpty(message = "Product Name is mandatory")
    @Column(name = "name")
    private String productName;

//    @NotNull(message="Please provide some price")
//    @Min(value = 100, message = "Minimum value should be greater than 100")
    @Column(name = "price")
    private Double productPrice;

    @Column(name = "unit")
    private int unitStock;

    private String productImage;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private String dateCreated = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(columnDefinition = "varchar(255)", name = "product_id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
//    @NotNull
    private Set<Category> fallIntoCategories = new HashSet<>();

    public void addProductToCategory(Category category) {
        if (!this.fallIntoCategories.contains(category)) {
            this.fallIntoCategories.add(category);
        }
        if (!category.getProducts().contains(this)) {
            category.getProducts().add(this);
        }
    }
}


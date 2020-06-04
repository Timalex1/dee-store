package com.deestore.v1.webapp.commands;

import com.deestore.v1.webapp.domains.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductForm {
    private Long id;

    @NotEmpty
    @Size(min=5,max=200)
    private String description;

    @NotEmpty
    @Size(min=1,max=200)
    private String name;

    @NotEmpty
    @Size(min=1,max=200)
    private String manufacturer;

    //    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    @NotNull
    @Min(0)
    @Max(1000000)
    private Double price;

    @NotEmpty
    @URL
    private String imageUrl;

    @Min(0)
    @Max(30)
    private int unit;

    private String category;
}

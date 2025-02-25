package alten.shop.back.model;

import alten.shop.back.enums.STOCKENUM;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private double quantity;
    private String internalReference;
    private double shellId;
    private STOCKENUM stockEnum;
    private long rating;
    private String createdAt;
    private String updatedAt;


}



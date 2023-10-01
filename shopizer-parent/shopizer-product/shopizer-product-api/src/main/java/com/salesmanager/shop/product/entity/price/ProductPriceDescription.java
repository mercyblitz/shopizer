package com.salesmanager.shop.product.entity.price;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.shop.commons.entity.common.description.Description;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_PRICE_DESCRIPTION",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "PRODUCT_PRICE_ID",
                        "LANGUAGE_ID"
                })
        }
)

@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_price_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductPriceDescription extends Description {

    public final static String DEFAULT_PRICE_DESCRIPTION = "DEFAULT";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ManyToOne(targetEntity = ProductPrice.class)
    @JoinColumn(name = "PRODUCT_PRICE_ID", nullable = false)
    private ProductPrice productPrice;


    @Column(name = "PRICE_APPENDER")
    private String priceAppender;

    public ProductPriceDescription() {
    }

    public String getPriceAppender() {
        return priceAppender;
    }

    public void setPriceAppender(String priceAppender) {
        this.priceAppender = priceAppender;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }


}

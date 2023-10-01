package com.salesmanager.shop.product.entity.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.shop.commons.constants.SchemaConstant;
import com.salesmanager.shop.commons.entity.common.description.Description;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_OPTION_VALUE_DESCRIPTION", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "PRODUCT_OPTION_VALUE_ID",
                "LANGUAGE_ID"
        })
}
)

@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT",
        pkColumnValue = "product_option_value_description_seq",
        allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE,
        initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductOptionValueDescription extends Description {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(targetEntity = ProductOptionValue.class)
    @JoinColumn(name = "PRODUCT_OPTION_VALUE_ID")
    private ProductOptionValue productOptionValue;

    public ProductOptionValueDescription() {
    }

    public ProductOptionValue getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValue productOptionValue) {
        this.productOptionValue = productOptionValue;
    }

}

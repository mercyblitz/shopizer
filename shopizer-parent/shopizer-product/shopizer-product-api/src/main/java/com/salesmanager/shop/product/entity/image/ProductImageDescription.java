package com.salesmanager.shop.product.entity.image;


import com.salesmanager.shop.commons.constants.SchemaConstant;
import com.salesmanager.shop.commons.entity.common.description.Description;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGE_DESCRIPTION", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "PRODUCT_IMAGE_ID",
                "LANGUAGE_ID"
        })
}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
        pkColumnValue = "product_image_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE,
        initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductImageDescription extends Description {
    private static final long serialVersionUID = 1L;

    @ManyToOne(targetEntity = ProductImage.class)
    @JoinColumn(name = "PRODUCT_IMAGE_ID", nullable = false)
    private ProductImage productImage;

    @Column(name = "ALT_TAG", length = 100)
    private String altTag;

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public String getAltTag() {
        return altTag;
    }

    public void setAltTag(String altTag) {
        this.altTag = altTag;
    }


}

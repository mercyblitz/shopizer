package com.salesmanager.shop.product.entity.variation;


import com.salesmanager.shop.commons.entity.common.audit.AuditListener;
import com.salesmanager.shop.commons.entity.common.audit.AuditSection;
import com.salesmanager.shop.commons.entity.common.audit.Auditable;
import com.salesmanager.shop.commons.entity.generic.SalesManagerEntity;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.product.entity.attribute.Optionable;
import com.salesmanager.shop.product.entity.attribute.ProductOption;
import com.salesmanager.shop.product.entity.attribute.ProductOptionValue;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;


/**
 * Product configuration pre 3.0
 * Contains possible product variations
 * <p>
 * color - red
 * size - small
 *
 * @author carlsamson
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT_VARIATION", uniqueConstraints =
@UniqueConstraint(columnNames = {"MERCHANT_ID", "PRODUCT_OPTION_ID", "OPTION_VALUE_ID"}))
public class ProductVariation extends SalesManagerEntity<Long, ProductVariation> implements Optionable, Auditable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Id
    @Column(name = "PRODUCT_VARIATION_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_VARIN_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    /**
     * can exist detached
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MERCHANT_ID", nullable = false)
    private MerchantStore merchantStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_OPTION_ID", nullable = false)
    private ProductOption productOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_VALUE_ID", nullable = false)
    private ProductOptionValue productOptionValue;

    @NotEmpty
    @Column(name = "CODE", length = 100, nullable = false)
    private String code;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    @Column(name = "VARIANT_DEFAULT")
    private boolean variantDefault = false;


    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

    public ProductOptionValue getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValue productOptionValue) {
        this.productOptionValue = productOptionValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isVariantDefault() {
        return variantDefault;
    }

    public void setVariantDefault(boolean variantDefault) {
        this.variantDefault = variantDefault;
    }
}

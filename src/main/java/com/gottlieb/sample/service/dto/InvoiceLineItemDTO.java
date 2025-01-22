package com.gottlieb.sample.service.dto;

import java.math.BigDecimal;

public class InvoiceLineItemDTO {
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal unitPrice;

    public ProductDTO getProduct() {
        return product;
    }
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}

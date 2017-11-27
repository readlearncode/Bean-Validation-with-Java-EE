package com.readlearncode.model;

import java.util.Objects;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Holding {

    private Integer transactionId;
    private Integer stockId;
    private String name;
    private Integer quantity;
    private Double price;
    private Double value;

    public Holding() {
    }

    public Holding(Integer transactionId, Integer stockId, String name, Integer quantity, Double price, Double value) {
        this.transactionId = transactionId;
        this.stockId = stockId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.value = value;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holding holding = (Holding) o;
        return Objects.equals(transactionId, holding.transactionId) &&
                Objects.equals(stockId, holding.stockId) &&
                Objects.equals(name, holding.name) &&
                Objects.equals(quantity, holding.quantity) &&
                Objects.equals(price, holding.price) &&
                Objects.equals(value, holding.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, stockId, name, quantity, price, value);
    }

    @Override
    public String toString() {
        return "Holding{" +
                "transactionId=" + transactionId +
                ", stockId=" + stockId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", value=" + value +
                '}';
    }
}
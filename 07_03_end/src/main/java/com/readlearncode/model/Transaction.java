package com.readlearncode.model;

import com.readlearncode.model.contraints.MaxDealSize;
import com.readlearncode.model.contraints.Price;

import javax.faces.annotation.FacesConfig;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.Objects;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@FacesConfig(version = JSF_2_3)
@MaxDealSize(size = 10_000, type = Transaction.TYPE.BUY)
public class Transaction {

    public enum TYPE {
        BUY, SELL
    }

    private Integer id;

    @NotNull
    private TYPE type;

    @NotNull
    private Stock stock;

    @Positive
    @NotNull
    private Integer quantity;

    @Price(min = 0, max = 100)
    private Double priceLimit;

    @NotNull
    @FutureOrPresent
    private Date exerciseDate;

    public Transaction() {
    }

    public Transaction(Integer id, TYPE type, Stock stock, Integer quantity, Double priceLimit, Date exerciseDate) {
        this.id = id;
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.priceLimit = priceLimit;
        this.exerciseDate = exerciseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(Double priceLimit) {
        this.priceLimit = priceLimit;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(stock, that.stock) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(priceLimit, that.priceLimit) &&
                Objects.equals(exerciseDate, that.exerciseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, stock, quantity, priceLimit, exerciseDate);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", stock=" + stock +
                ", quantity=" + quantity +
                ", priceLimit=" + priceLimit +
                ", exerciseDate=" + exerciseDate +
                '}';
    }
}
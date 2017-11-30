package com.readlearncode.model;

import com.readlearncode.model.contraints.StockCode;

import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Named
public class Stock {

    private Integer id;

    @NotNull
    private String name;

    @StockCode(size = 3, message = "Stock must be {size} characters in length")
    private String code;

    public Stock() {
    }

    public Stock(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id == stock.id &&
                Objects.equals(name, stock.name) &&
                Objects.equals(code, stock.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
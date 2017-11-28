package com.readlearncode.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Portfolio {

    private Integer id;
    private Integer clientId;
    private List<@NotNull Transaction> transactions = new ArrayList<>();

    public Portfolio() {
    }

    public Portfolio(Integer id, Integer clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public Portfolio(Integer id, Integer clientId, Transaction transaction) {
        this.id = id;
        this.clientId = clientId;
        this.transactions.add(transaction);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(id, portfolio.id) &&
                Objects.equals(clientId, portfolio.clientId) &&
                Objects.equals(transactions, portfolio.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, transactions);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", transactions=" + transactions +
                '}';
    }
}
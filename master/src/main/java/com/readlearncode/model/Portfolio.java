package com.readlearncode.model;

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
    private Long clientId;
    private List<Transaction> transactions = new ArrayList<>();

    public Portfolio() {
    }

    public Portfolio(Integer id, Long clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public Portfolio(Integer id, Long clientId, Transaction transaction) {
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
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
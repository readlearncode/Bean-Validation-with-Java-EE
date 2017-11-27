package com.readlearncode.service;

import com.readlearncode.model.Transaction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
@Startup
public class TransactionResource {

    private AtomicInteger nextId;

    private List<Transaction> all;

    @EJB
    private StockResource stockResource;

    @PostConstruct
    public void init() {
        all = new ArrayList<>();
        nextId = new AtomicInteger(0);
    }

    public List<Transaction> findAll() {
        return all;
    }

    public Transaction findById(Integer id) {
        return all.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst()
                .get();
    }

    public Transaction add(Transaction transaction) {
        transaction.setId(nextId.getAndIncrement());
        all.add(transaction);
        return transaction;
    }

    public void merge(Transaction transaction) {
        // no action required as the reference has been modified directly
    }

    public void delete(Transaction transaction) {
        all.remove(transaction);
    }

}
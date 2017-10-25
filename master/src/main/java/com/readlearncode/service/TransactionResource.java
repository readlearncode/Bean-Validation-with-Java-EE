package com.readlearncode.service;

import com.readlearncode.model.Transaction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            all.add(new Transaction(0, Transaction.TYPE.BUY, stockResource.findById(0), 100, 2.04, dateFormat.parse("1978/12/2")));
            all.add(new Transaction(1, Transaction.TYPE.BUY, stockResource.findById(1), 1000, 5.40, dateFormat.parse("1978/12/2")));
            all.add(new Transaction(2, Transaction.TYPE.BUY, stockResource.findById(2), 350, 4.20, dateFormat.parse("1978/12/2")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        nextId = new AtomicInteger(3);
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

    public void add(Transaction transaction) {
        transaction.setId(nextId.getAndIncrement());
        all.add(transaction);
    }

    public void merge(Transaction transaction) {
        // no action required as the reference has been modified directly
    }

    public void delete(Transaction transaction) {
        all.remove(transaction);
    }

}
package com.readlearncode.service;

import com.readlearncode.model.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
public class TransactionService {

    @EJB
    private TransactionResource transactionResource;

    @EJB
    private PortfolioService portfolioService;

    public void remove(Transaction transaction) {
        transactionResource.delete(transaction);
    }

    public Transaction persist(Transaction transaction) {
        return transactionResource.add(transaction);
    }

    public void merge(Transaction transaction) {
        transactionResource.merge(transaction);
    }

    public Transaction find(Integer id) {
        return transactionResource.findById(id);
    }

    public List<Transaction> getAll() {
        return transactionResource.findAll();
    }

    public List<Transaction> getAllForClient(Integer id) {
        return portfolioService.getTransactionsForClient(id);
    }

    public Transaction getTransaction(Integer id) {
       return  transactionResource.findById(id);
    }
}
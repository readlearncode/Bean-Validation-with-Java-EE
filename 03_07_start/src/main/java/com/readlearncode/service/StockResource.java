package com.readlearncode.service;

import com.readlearncode.model.Stock;

import javax.annotation.PostConstruct;
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
public class StockResource {

    private AtomicInteger nextId;

    private List<Stock> all;

    @PostConstruct
    public void init() {
        all = new ArrayList<>();
        nextId = new AtomicInteger(0);
    }

    public List<Stock> findAll() {
        return all;
    }

    public Stock findById(int id) {
        return all.stream()
                .filter(stock -> stock.getId().equals(id))
                .findFirst()
                .get();
    }

    public void add(Stock stock) {
        stock.setId(nextId.getAndIncrement());
        all.add(stock);
    }

    public void merge(Stock stock) {
        // no action required as the reference has been modified directly
    }

    public void delete(Stock stock) {
        all.remove(stock);
    }

}
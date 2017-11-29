package com.readlearncode.service;

import com.readlearncode.model.Stock;

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
public class StockService {

    @EJB
    private StockResource stockResource;

    public void remove(Stock stock) {
        stockResource.delete(stock);
    }

    public void persist(Stock stock) {
        stockResource.add(stock);
    }

    public void merge(Stock stock) {
        stockResource.merge(stock);
    }

    public Stock find(Integer id) {
        return stockResource.findById(id);
    }

    public List<Stock> getAll() {
        return stockResource.findAll();
    }

}
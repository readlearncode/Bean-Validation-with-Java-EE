package com.readlearncode;

import com.readlearncode.model.Client;
import com.readlearncode.model.Stock;
import com.readlearncode.model.Transaction;
import com.readlearncode.service.ClientResource;
import com.readlearncode.service.PortfolioService;
import com.readlearncode.service.StockResource;
import com.readlearncode.service.TransactionResource;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Startup
@Singleton
public class Bootstrap {

    @EJB
    private StockResource stockResource;

    @EJB
    private TransactionResource transactionResource;

    @EJB
    private ClientResource clientResource;

    @EJB
    private PortfolioService portfolioService;

    @PostConstruct
    public void init() {
        try {
            stockResource.add(new Stock(0, "Big Corp Inc", "BIG"));
            stockResource.add(new Stock(1, "Mama Pizza Inc", "MAM"));
            stockResource.add(new Stock(2, "ICU Search Inc", "ICU"));
            stockResource.add(new Stock(4, "Banana Inc", "BAN"));
            stockResource.add(new Stock(5, "ABC Car Rental", "ABC"));

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            transactionResource.add(new Transaction(0, Transaction.TYPE.BUY, stockResource.findById(0), 100, 2.04, dateFormat.parse("2017/10/26")));
            transactionResource.add(new Transaction(1, Transaction.TYPE.SELL, stockResource.findById(1), 1000, 5.40, dateFormat.parse("2017/10/25")));
            transactionResource.add(new Transaction(2, Transaction.TYPE.BUY, stockResource.findById(2), 350, 4.20, dateFormat.parse("2017/10/15")));
            transactionResource.add(new Transaction(3, Transaction.TYPE.BUY, stockResource.findById(2), 300, 4.30, dateFormat.parse("2017/10/18")));
            transactionResource.add(new Transaction(4, Transaction.TYPE.SELL, stockResource.findById(2), 150, 4.10, dateFormat.parse("2017/10/20")));

            clientResource.add(new Client(0, "Jane Brown", dateFormat.parse("1978/12/2"), "jane@brown.xx.com", true, "5105105105105100"));
            clientResource.add(new Client(1, "John Smith", dateFormat.parse("1965/10/12"),  "john@example.com", true, "5105105105105100"));
            clientResource.add(new Client(2, "Gary Gecko", dateFormat.parse("1987/12/11"), "gary@gecko.com", true, "5105105105105100"));

            portfolioService.processTransaction(clientResource.findById(0), transactionResource.findById(0));
            portfolioService.processTransaction(clientResource.findById(0), transactionResource.findById(1));
            portfolioService.processTransaction(clientResource.findById(0), transactionResource.findById(2));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
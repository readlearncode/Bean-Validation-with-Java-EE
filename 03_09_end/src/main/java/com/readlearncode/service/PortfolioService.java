package com.readlearncode.service;

import com.readlearncode.model.Client;
import com.readlearncode.model.Holding;
import com.readlearncode.model.Portfolio;
import com.readlearncode.model.Transaction;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
public class PortfolioService {

    private AtomicInteger nextId;

    private Map<Integer, Portfolio> portfolios = new HashMap<>();

    @PostConstruct
    public void init() {
        nextId = new AtomicInteger(0);
    }

    public void processTransaction(Client client, Transaction transaction) {
        Portfolio portfolio = portfolios.get(client.getId());
        if (portfolio == null) {
            portfolios.put(client.getId(), newPortfolio(client, transaction));
        } else {
            updatePortfolio(portfolio, transaction);
        }
    }

    private void updatePortfolio(Portfolio portfolio, Transaction transaction) {
        portfolio.getTransactions().add(transaction);
    }

    public Portfolio newPortfolio(Client client, Transaction transaction) {
        return new Portfolio(nextId.getAndIncrement(), client.getId(), transaction);
    }

    public List<Transaction> getTransactionsForClient(Integer clientId) {
        if (portfolios.containsKey(clientId)) {
            return portfolios.get(clientId).getTransactions();
        }
        return new ArrayList<>();
    }

    public List<Holding> getHoldingsForClient(Integer clientId) {

        List<Transaction> transactions = portfolios.get(clientId).getTransactions();

        List<Holding> holdings = new ArrayList<>();

        transactions.stream().forEach(transaction -> {

                    Optional<Holding> holding = holdings.stream()
                            .filter(h -> h.getStockId().equals(transaction.getId()))
                            .findFirst();

                    if (holding.isPresent()) {
                        // Calculate new value
                        Double currentValue = holding.get().getValue();
                        Double transactionValue = transaction.getPriceLimit() * transaction.getPriceLimit();
                        Double newValue = transaction.getType().equals(Transaction.TYPE.BUY)
                                ? currentValue + transactionValue : currentValue - transactionValue;

                        holding.get().setValue(newValue);

                        // Calculate new quantity
                        Integer currentQuantity = holding.get().getQuantity();
                        Integer transactionQuantity = transaction.getQuantity();
                        Integer newQuantity = transaction.getType().equals(Transaction.TYPE.BUY)
                                ? currentQuantity + transactionQuantity : currentQuantity - transactionQuantity;

                        holding.get().setQuantity(newQuantity);

                        // Calculate new average price
                        holding.get().setPrice(newValue / newQuantity);
                    }
                }


        );

        return holdings;
    }

}
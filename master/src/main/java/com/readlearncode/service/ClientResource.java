package com.readlearncode.service;

import com.readlearncode.model.Client;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless @Startup
public class ClientResource {

    private AtomicLong nextId;

    private List<Client> all;

    @PostConstruct
    public void init() {
        all = new ArrayList<>();
        all.add(new Client(0L, 1, "Alex Theedom", "alex.theedom@gmail.com", true));
        all.add(new Client(1L, 1, "John Smith", "email", true));
        all.add(new Client(2L, 1, "TEST", "alex.theedom@gmail.com", true));
        nextId = new AtomicLong(3);
    }

    public List<Client> findAll() {
        return all;
    }

    public Client findById(Long id) {
        return all.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .get();
    }

    public void add(Client client) {
        client.setId(nextId.getAndIncrement());
        all.add(client);
    }

    public void merge(Client client) {
        // no action required as the reference has been modified directly
    }

    public void delete(Client client) {
        all.remove(client);
    }

}
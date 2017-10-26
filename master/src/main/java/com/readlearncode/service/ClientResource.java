package com.readlearncode.service;

import com.readlearncode.model.Client;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
@Startup
public class ClientResource {

    private AtomicLong nextId;

    private List<Client> all;

    @PostConstruct
    public void init() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        all = new ArrayList<>();
        try {
            all.add(new Client(0L, "Alex Theedom", dateFormat.parse("1978/12/2"), "alex.theedom@gmail.com", true, "5105105105105100"));
            all.add(new Client(1L, "John Smith", dateFormat.parse("1965/10/12"), "email", true, "5105105105105100"));
            all.add(new Client(2L, "TEST", dateFormat.parse("1984/1/5"), "alex.theedom@gmail.com", true, "5105105105105100"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
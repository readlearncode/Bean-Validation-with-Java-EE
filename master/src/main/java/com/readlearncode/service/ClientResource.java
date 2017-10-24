package com.readlearncode.service;

import com.readlearncode.model.Client;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
public class ClientResource {

    private List<Client> all;

    @PostConstruct
    public void init() {
        all = new ArrayList<>();
        all.add(new Client(0L, 1, "Alex Theedom", "alex.theedom@gmail.com", true));
        all.add(new Client(1L, 1, "John Smith", "email", true));
        all.add(new Client(2L, 1, "TEST", "alex.theedom@gmail.com", true));

        // 	public Client(int version, String name, String email, boolean acceptTOC) {

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
        all.add(client);
    }

    public void delete(Client client) {
        all.remove(client);
    }
}
package com.readlearncode.service;

import com.readlearncode.model.Client;

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
public class ClientService {

    @EJB
    private ClientResource clientResource;

    public void remove(Client client) {
        clientResource.delete(client);
    }

    public void persist(Client client) {
        clientResource.add(client);
    }

    public void merge(Client client) {
        clientResource.merge(client);
    }

    public Client find(Integer id) {
        return clientResource.findById(id);
    }

    public List<Client> getAll() {
        return clientResource.findAll();
    }

    }
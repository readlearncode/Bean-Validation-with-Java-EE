package com.readlearncode.rest;

import com.readlearncode.model.Transaction;
import com.readlearncode.service.TransactionService;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Path("/transactions")
public class TransactionResource {

    @EJB
    private TransactionService transactionService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTransactions(){
        return Response.ok(transactionService.getAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getTransaction(@PathParam("id") final Integer id){
        Transaction transaction = transactionService.getTransaction(id);
        return Response.ok(transaction).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(@Valid final Transaction transaction) {
        Transaction persistedTransaction = transactionService.persist(transaction);
        return Response.status(Response.Status.OK).entity(persistedTransaction).build();
    }


}
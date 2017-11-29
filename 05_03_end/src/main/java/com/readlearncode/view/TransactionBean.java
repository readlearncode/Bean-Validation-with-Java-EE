package com.readlearncode.view;

import com.readlearncode.model.Client;
import com.readlearncode.model.Transaction;
import com.readlearncode.service.PortfolioService;
import com.readlearncode.service.TransactionService;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Stateful
@ConversationScoped
public class TransactionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private TransactionService transactionService;

    @EJB
    private PortfolioService portfolioService;

    @Inject
    private FacesContext facesContext;

    @Inject
    private Conversation conversation;

    private Integer id;

    private Transaction transaction;

    private Client client;

    private Integer clientId;

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer id) {
        this.clientId = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String create() {
        this.conversation.begin();
        this.conversation.setTimeout(1800000L);
        return "create?faces-redirect=true";
    }

    public void retrieve() {

        if (facesContext.isPostback()) {
            return;
        }

        if (this.conversation.isTransient()) {
            this.conversation.begin();
            this.conversation.setTimeout(1800000L);
        }

        if (this.id == null) {
            this.transaction = this.example;
        } else {
            this.transaction = findById(getId());
        }
    }

    public Transaction findById(Integer id) {
        return this.transactionService.find(id);
    }

    public Transaction.TYPE[] getAllTypes() {
        return Transaction.TYPE.values();
    }

    public String update() {
        this.conversation.end();

        try {
            if (this.transaction.getId() == null) {
                this.portfolioService.processTransaction(client, transaction);
                this.transactionService.persist(this.transaction);
            } else {
                this.portfolioService.processTransaction(client, transaction);
                this.transactionService.merge(this.transaction);
            }
            return "search?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public String delete() {
        this.conversation.end();

        try {
            Transaction deletableEntity = findById(getId());
            this.transactionService.remove(deletableEntity);
            return "search?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(e.getMessage()));
            return null;
        }
    }


    private int page;
    private long count;
    private List<Transaction> pageItems;

    private Transaction example = new Transaction();

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return 10;
    }

    public Transaction getExample() {
        return this.example;
    }

    public void setExample(Transaction example) {
        this.example = example;
    }


    public void paginate() {
        if (clientId != null) {
            this.pageItems = transactionService.getAllForClient(clientId);
        } else {
            this.pageItems = transactionService.getAll();
        }
        this.count = pageItems.size();
    }

    public List<Transaction> getPageItems() {
        return this.pageItems;
    }

    public long getCount() {
        return this.count;
    }

    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    private Transaction add = new Transaction();

    public Transaction getAdd() {
        return this.add;
    }

    public Transaction getAdded() {
        Transaction added = this.add;
        this.add = new Transaction();
        return added;
    }
}

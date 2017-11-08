package com.readlearncode.view;

import com.readlearncode.model.Client;
import com.readlearncode.model.Transaction;
import com.readlearncode.service.PortfolioService;
import com.readlearncode.service.TransactionService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean for Transaction entities.
 * <p/>
 * This class provides CRUD functionality for all Transaction entities. It
 * focuses purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt>
 * for state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class TransactionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Support creating and retrieving Transaction entities
     */
    @EJB
    private TransactionService transactionService;

    @EJB
    private PortfolioService portfolioService;

    @Inject
    private Conversation conversation;

    private Integer id;

    private Transaction transaction;

    private Client client;

    private Long clientId;

    public Long getClientId() {
        return this.clientId;
    }

    public void setClientId(Long id) {
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

        if (FacesContext.getCurrentInstance().isPostback()) {
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


	/*
     * Support updating and deleting Transaction entities
	 */

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
            FacesContext.getCurrentInstance().addMessage(null,
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
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
            return null;
        }
    }

	/*
     * Support searching Transaction entities with pagination
	 */

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

    public String search() {
        this.page = 0;
        return null;
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

	/*
     * Support listing and POSTing back Transaction entities (e.g. from inside
	 * an HtmlSelectOneMenu)
	 */

    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @Resource
    private SessionContext sessionContext;

    public Converter getConverter() {

        final TransactionBean ejbProxy = this.sessionContext
                .getBusinessObject(TransactionBean.class);

        return new Converter() {

            @Override
            public Object getAsObject(FacesContext context,
                                      UIComponent component, String value) {

                return ejbProxy.findById(Integer.valueOf(value));
            }

            @Override
            public String getAsString(FacesContext context,
                                      UIComponent component, Object value) {

                if (value == null) {
                    return "";
                }

                return String.valueOf(((Transaction) value).getId());
            }
        };
    }

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

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

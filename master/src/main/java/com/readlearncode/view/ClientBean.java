package com.readlearncode.view;

import com.readlearncode.model.Client;
import com.readlearncode.service.ClientService;

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

/**
 * Backing bean for Client entities.
 * <p/>
 * This class provides CRUD functionality for all Client entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ClientBean implements Serializable {

    private static final long serialVersionUID = 1L;

	/*
     * Support creating and retrieving Client entities
	 */

    @EJB
    private ClientService clientService;


    @Inject
    private FacesContext facesContext;

    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Client client;

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Inject
    private Conversation conversation;

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
            this.client = this.example;
        } else {
            this.client = findById(this.id);
        }
    }

    public Client findById(Long id) {
        return this.clientService.find(id);
    }

	/*
     * Support updating and deleting Client entities
	 */

    public String update() {
        this.conversation.end();

        try {
            if (this.client.getId() == null) {
                this.clientService.persist(this.client);
            } else {
                this.clientService.merge(this.client);
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
            Client deletableEntity = findById(getId());
            this.clientService.remove(deletableEntity);
            return "search?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(e.getMessage()));
            return null;
        }
    }

	/*
     * Support searching Client entities with pagination
	 */

    private int page;
    private long count;
    private List<Client> pageItems;

    private Client example = new Client();

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return 10;
    }

    public Client getExample() {
        return this.example;
    }

    public void setExample(Client example) {
        this.example = example;
    }

    public String search() {
        this.page = 0;
        return null;
    }

    public void paginate() {
        this.pageItems = clientService.getAll();
        this.count = pageItems.size();
    }

    public List<Client> getPageItems() {
        return this.pageItems;
    }

    public long getCount() {
        return this.count;
    }


    public List<Client> getAll() {
        return clientService.getAll();
    }

//    @Resource
//    private SessionContext sessionContext;

//    public Converter getConverter() {
//
//        final ClientBean ejbProxy = this.sessionContext
//                .getBusinessObject(ClientBean.class);
//
//        return new Converter() {
//
//            @Override
//            public Object getAsObject(FacesContext context,
//                                      UIComponent component, String value) {
//
//                return ejbProxy.findById(Long.valueOf(value));
//            }
//
//            @Override
//            public String getAsString(FacesContext context,
//                                      UIComponent component, Object value) {
//
//                if (value == null) {
//                    return "";
//                }
//
//                return String.valueOf(((Client) value).getId());
//            }
//        };
//    }

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

    private Client add = new Client();

    public Client getAdd() {
        return this.add;
    }

//    public Client getAdded() {
//        Client added = this.add;
//        this.add = new Client();
//        return added;
//    }
}

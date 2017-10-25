package com.readlearncode.view;

import com.readlearncode.model.Portfolio;
import com.readlearncode.model.Transaction;
import com.readlearncode.service.PortfolioService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean for Stock entities.
 * <p/>
 * This class provides CRUD functionality for all Stock entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class PortfolioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Stock entities
	 */

	@EJB
	private PortfolioService portfolioService;

	private Integer id;
	private Long clientId;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Portfolio portfolio;

	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	@Inject
	private Conversation conversation;

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

//		if (this.id == null) {
//			this.portfolio = this.example;
//		} else {
//			this.portfolio = findById(getId());
//		}
	}

//	public Stock findById(Integer id) {
//		return this.portfolioService.find(id);
//	}

	/*
	 * Support updating and deleting Stock entities
	 */

//	public String update() {
//		this.conversation.end();
//
//		try {
//			if(this.stock.getId() == null){
//				this.stockService.persist(this.stock);
//			} else {
//				this.stockService.merge(this.stock);
//			}
//			return "search?faces-redirect=true";
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(e.getMessage()));
//			return null;
//		}
//	}

//	public String delete() {
//		this.conversation.end();
//
//		try {
//			Stock deletableEntity = findById(getId());
//			this.stockService.remove(deletableEntity);
//			return "search?faces-redirect=true";
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(e.getMessage()));
//			return null;
//		}
//	}

	/*
	 * Support searching Stock entities with pagination
	 */

	private int page;
	private long count;
	private List<Transaction> pageItems;

	private Portfolio example = new Portfolio();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Portfolio getExample() {
		return this.example;
	}

	public void setExample(Portfolio example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {
		this.pageItems = portfolioService.getTransactionsForClient(clientId);
		this.count = pageItems.size();
	}


	public List<Transaction> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Stock entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

//	public List<Portfolio> getAll() {
//		return portfolioService.getAll();
//	}

	@Resource
	private SessionContext sessionContext;

//	public Converter getConverter() {
//
//		final PortfolioBean ejbProxy = this.sessionContext
//				.getBusinessObject(PortfolioBean.class);
//
//		return new Converter() {
//
//			@Override
//			public Object getAsObject(FacesContext context,
//					UIComponent component, String value) {
//
//				return ejbProxy.findById(Integer.valueOf(value));
//			}
//
//			@Override
//			public String getAsString(FacesContext context,
//					UIComponent component, Object value) {
//
//				if (value == null) {
//					return "";
//				}
//
//				return String.valueOf(((Stock) value).getId());
//			}
//		};
//	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Portfolio add = new Portfolio();

	public Portfolio getAdd() {
		return this.add;
	}

	public Portfolio getAdded() {
		Portfolio added = this.add;
		this.add = new Portfolio();
		return added;
	}
}

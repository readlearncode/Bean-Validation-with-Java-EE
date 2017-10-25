package com.readlearncode.view;

import com.readlearncode.model.Stock;
import com.readlearncode.service.StockService;

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
public class StockBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Stock entities
	 */

	@EJB
	private StockService stockService;

	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Stock stock;

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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

		if (this.id == null) {
			this.stock = this.example;
		} else {
			this.stock = findById(getId());
		}
	}

	public Stock findById(Integer id) {
		return this.stockService.find(id);
	}

	/*
	 * Support updating and deleting Stock entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if(this.stock.getId() == null){
				this.stockService.persist(this.stock);
			} else {
				this.stockService.merge(this.stock);
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
			Stock deletableEntity = findById(getId());
			this.stockService.remove(deletableEntity);
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching Stock entities with pagination
	 */

	private int page;
	private long count;
	private List<Stock> pageItems;

	private Stock example = new Stock();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Stock getExample() {
		return this.example;
	}

	public void setExample(Stock example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {
		this.pageItems = stockService.getAll();
		this.count = 3;
	}


	public List<Stock> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Stock entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Stock> getAll() {
		return stockService.getAll();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final StockBean ejbProxy = this.sessionContext
				.getBusinessObject(StockBean.class);

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

				return String.valueOf(((Stock) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Stock add = new Stock();

	public Stock getAdd() {
		return this.add;
	}

	public Stock getAdded() {
		Stock added = this.add;
		this.add = new Stock();
		return added;
	}
}

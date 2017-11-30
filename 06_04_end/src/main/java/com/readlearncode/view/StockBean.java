package com.readlearncode.view;

import com.readlearncode.model.Stock;
import com.readlearncode.service.StockService;

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
public class StockBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private StockService stockService;

	@Inject
	private FacesContext facesContext;

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

		if (facesContext.isPostback()) {
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
			facesContext.addMessage(null,
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
			facesContext.addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}


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


	public List<Stock> getAll() {
		return stockService.getAll();
	}

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

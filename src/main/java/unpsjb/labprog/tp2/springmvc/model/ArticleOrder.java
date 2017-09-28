package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "article_order")
@NamedQueries({
	   @NamedQuery(name = "ArticleOrder.findAllWithoutShipmentOrderByAddress", query = "SELECT ap FROM ArticleOrder ap "
				+ " WHERE ap.shipment IS NULL" + " ORDER BY ap.order.address ASC"),
		@NamedQuery(name = "ArticleOrder.findAllWithShipmentDeliveredWithOutReceiptOrderByCustomer", query = "SELECT ap FROM ArticleOrder ap JOIN ap.shipment s "
				+ "WHERE ap.receipt IS NULL" + " AND s.deliver = TRUE " + "ORDER BY ap.order.customer ASC") })
public class ArticleOrder implements Serializable {
	private static final long serialVersionUID = 1351862960441149074L;

	@Id
	@SequenceGenerator(name = "ARTICLE_ORDER_ID_GENERATOR", sequenceName = "ARTICLE_ORDER_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ORDER_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	private Float price;

	@Basic(optional = false)
	private Float quantity;

	@Basic(optional = false)
	private boolean active;

	@ManyToOne
	private Order order;

	@ManyToOne(cascade= CascadeType.ALL)
	@Basic(optional = false)
	private Article article;	

	@ManyToOne
	@JoinColumn(nullable = true)
	private Shipment shipment;// recibo

	@ManyToOne
	@JoinColumn(nullable = true)
	private Receipt receipt;// factura

	public ArticleOrder() {
	}

	public ArticleOrder(Order order, Article article, Float price, Float quantity) {
		this.price = price;
		this.quantity = quantity;
		this.order = order;
		this.article = article;
		this.active = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "ArticleOrder [id=" + id + ", order=" + order + ", article=" + article + ", shipment=" + shipment
				+ ", receipt=" + receipt + "]";
	}

}

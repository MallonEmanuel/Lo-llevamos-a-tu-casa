package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "article_order_provider")


@NamedQueries({ 
		@NamedQuery(name = "ArticleOrderProvider.findByIdShipmentProvider", query = "SELECT e FROM ArticleOrderProvider e where e.shipmentProvider.id = :shipmentProviderId")
	    })
public class ArticleOrderProvider implements Serializable{
	private static final long serialVersionUID = 2851673061785354300L;

	@Id
	@SequenceGenerator(name = "ARTICLE_ORDER_PROVIDER_ID_GENERATOR", sequenceName = "ARTICLE_ORDER_PROVIDER_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ORDER_PROVIDER_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	private Float price;

	@Basic(optional = false)
	private Float quantity;

	@Basic(optional = false)
	private boolean active;

	@ManyToOne(cascade = CascadeType.ALL)
	@Basic(optional = false)
	private Article article;

	@ManyToOne
	@Basic(optional = false)
//	@Column(name= "shipment_provider")
	private ShipmentProvider shipmentProvider;// recibo

	public ArticleOrderProvider() {
	}

	public ArticleOrderProvider(Article article, ShipmentProvider shipmentProvider,Float price, Float quantity) {
		this.active = true;
		this.price = price;
		this.quantity = quantity;
		this.article = article;
		this.shipmentProvider = shipmentProvider;
	}

	public ArticleOrderProvider(Article article, ShipmentProvider shipmentProvider,Float quantity) {
		this.active = true;
		this.price = article.getPrice();
		this.quantity = quantity;
		this.article = article;
		this.shipmentProvider = shipmentProvider;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public ShipmentProvider getShipmentProvider() {
		return shipmentProvider;
	}

	public void setShipmentProvider(ShipmentProvider shipmentProvider) {
		this.shipmentProvider = shipmentProvider;
	}

	@Override
	public String toString() {
		return "ArticleOrderProvider [id=" + id + ", price=" + price + ", quantity=" + quantity + ", article=" + article
				+ ", shipmentProvider=" + shipmentProvider + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((shipmentProvider == null) ? 0 : shipmentProvider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleOrderProvider other = (ArticleOrderProvider) obj;
		if (active != other.active)
			return false;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (id != other.id)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (shipmentProvider == null) {
			if (other.shipmentProvider != null)
				return false;
		} else if (!shipmentProvider.equals(other.shipmentProvider))
			return false;
		return true;
	}
	
}

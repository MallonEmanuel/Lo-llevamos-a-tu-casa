package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// Factura
@Entity
@Table(name = "receipt")
@NamedQueries({ @NamedQuery(name = "Receipt.getAllNonPaymentOrderByDeliveryDateDesc", query = "SELECT r FROM Receipt r "
		+ "WHERE r.paymentDate IS NULL" + " ORDER BY r.deliveryDate DESC")})

public class Receipt implements Serializable {
	private static final long serialVersionUID = -2657813551088071577L;

	@Id
	@SequenceGenerator(name = "RECEIPT_ID_GENERATOR", sequenceName = "RECEIPT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEIPT_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "payment_date", nullable = true)
	private Date paymentDate;

	@Basic(optional = false)
	private boolean active;

	@ManyToOne
	@Basic(optional = false)
	private Customer customer;

	// @OneToMany (mappedBy = "receipt",cascade = CascadeType.ALL,fetch =
	// FetchType.LAZY)
	// private Collection <ArticleOrder> articlesOrder;

	public Receipt() {
	}

	public Receipt(Customer customer, Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		this.customer = customer;
		this.active = true;
		this.paymentDate = null;
		// this.articlesOrder = new ArrayList<ArticleOrder>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	// public Collection<ArticleOrder> getArticlesOrder() {
	// return articlesOrder;
	// }
	//
	// public void setArticlesOrder(Collection<ArticleOrder> articlesOrder) {
	// this.articlesOrder = articlesOrder;
	// }

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", deliveryDate=" + deliveryDate + ", paymentDate=" + paymentDate + ", customer="
				+ customer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
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
		Receipt other = (Receipt) obj;
		if (active != other.active)
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id != other.id)
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		return true;
	}

}

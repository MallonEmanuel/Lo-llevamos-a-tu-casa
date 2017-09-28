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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "order_")
public class Order implements Serializable {
	private static final long serialVersionUID = 6098703276837281217L;

	@Id
	@SequenceGenerator(name="ORDER_ID_GENERATOR", sequenceName="ORDER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_ID_GENERATOR")
	private long id;

	@Column (name="date_of_entry")
	private Date dateOfEntry;

	private String comment;

	@Basic(optional = false)
	private boolean active;

	@ManyToOne
	@Basic(optional = false)
	private Address address;

	@ManyToOne
	@Basic(optional = false)
	private Customer customer;
	
	public Order() {
	}

	public Order(Customer customer,Address address, Date dateOfEntry) {
		this.customer = customer;
		this.dateOfEntry = dateOfEntry;
		this.address = address;
		this.active = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getDateOfEntry() {
		return dateOfEntry;
	}

	public void setDateOfEntry(Date dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", address=" + address.getId()+", dateOfEntry=" + dateOfEntry + "]";
	}
	
	

}

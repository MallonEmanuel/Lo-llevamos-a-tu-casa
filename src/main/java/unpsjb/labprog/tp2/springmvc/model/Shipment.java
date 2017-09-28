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

@Entity
@Table(name = "shipment")
@NamedQueries({
	@NamedQuery(name = "Shipment.findAllWithOutDeliver", query = "SELECT s FROM Shipment s "
			+ " WHERE s.deliver = false" + " ORDER BY s.releaseDate") })
public class Shipment implements Serializable {
	private static final long serialVersionUID = -4860544711927118872L;

	@Id
	@SequenceGenerator(name="SHIPTMENT_ID_GENERATOR", sequenceName="SHIPTMENT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHIPTMENT_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	@Column(name = "release_date") // fecha de armado -- fecha de salida
	private Date releaseDate;
	
	@Column(name = "deliver_date",nullable = true) // fecha de armado -- fecha de salida
	private Date deliverDate;
	
	private boolean deliver;

	@Basic(optional = false)
	private boolean active;

	@ManyToOne
	@Basic(optional = false)
	private Address address;

//	@OneToMany (mappedBy = "shipment",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private Collection <ArticleOrder> articlesOrder;
	
	public Shipment() {
	}

	public Shipment(Address address, Date releaseDate) {
		this.releaseDate = releaseDate;
		this.address = address;
		this.active = true;
		this.deliver = false;
//		this.articlesOrder = new ArrayList<ArticleOrder>();
	}

	public Shipment(Address address) {
		this.address = address;
		this.active = true;
		this.deliver = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	
	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

//	public Collection<ArticleOrder> getArticlesOrder() {
//		return articlesOrder;
//	}
//
//	public void setArticlesOrder(Collection<ArticleOrder> articlesOrder) {
//		this.articlesOrder = articlesOrder;
//	}

	public boolean isDeliver() {
		return deliver;
	}

	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
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

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", address=" + address.getId()+", releaseDate=" + releaseDate + ", deliverDate=" + deliverDate + ", deliver="
				+ deliver + "]";
	}
	
	

}

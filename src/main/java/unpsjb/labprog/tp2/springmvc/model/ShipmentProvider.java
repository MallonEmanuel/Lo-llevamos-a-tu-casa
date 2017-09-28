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
@Table(name="shipment_provider")
@NamedQueries({		
		@NamedQuery(name = "ShipmentProvider.filter", query = "SELECT s FROM ShipmentProvider s "
        + "WHERE (0L = :shipmentProviderId OR :shipmentProviderId = s.id) "
        + "AND ('TODOS' = :providerName OR UPPER(s.provider.name) LIKE CONCAT (UPPER(:providerName),'%'))"
        + "AND ( s.deliveryDate BETWEEN :beginDate AND :endDate)"
        + "ORDER BY s.deliveryDate DESC"
        )
})
public class ShipmentProvider implements Serializable{
	private static final long serialVersionUID = 1505626224889977638L;

	@Id
	@SequenceGenerator(name="SHIPTMENT_PROVIDER_ID_GENERATOR", sequenceName="SHIPTMENT_PROVIDER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHIPTMENT_PROVIDER_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	@Column(name = "delivery_date",nullable = true) // fecha de entrega
	private Date deliveryDate;
	
	@Basic(optional = false)
	private boolean active;
	
	@ManyToOne
	@Basic(optional = false)
	private Provider provider;

	public ShipmentProvider() {
	}

	public ShipmentProvider(Provider provider,Date deliveryDate) {
		this.active = true;
		this.deliveryDate = deliveryDate;
		this.provider = provider;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "ShipmentProvider [id=" + id + ", deliveryDate=" + deliveryDate + ", active=" + active + ", provider="
				+ provider + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
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
		ShipmentProvider other = (ShipmentProvider) obj;
		if (active != other.active)
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (id != other.id)
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}
	
	

}

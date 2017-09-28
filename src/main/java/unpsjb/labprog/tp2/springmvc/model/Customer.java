package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@NamedQueries(
		{ @NamedQuery(name = "Customer.findByName", query = "SELECT e FROM Customer e where e.name = :name"),
		@NamedQuery(name = "Customer.filterByName", query = "SELECT e FROM Customer e where UPPER(e.name) LIKE CONCAT (UPPER(:name),'%')"),
		
		@NamedQuery(name = "Customer.filter", query = "SELECT c FROM Customer c "
        + "WHERE ('TODOS' = :name OR UPPER(c.name) LIKE CONCAT (UPPER(:name),'%')) "
        + "AND ('TODOS' = :surname OR UPPER(c.surname) LIKE CONCAT (UPPER(:surname),'%'))"
        + "AND ('TODOS' = :cuit OR UPPER(c.cuit) LIKE CONCAT (UPPER(:cuit),'%'))"
        ),
		 @NamedQuery(name = "Customer.findByRandom", query = "SELECT e FROM Customer e ORDER BY RANDOM()") }
)
public class Customer implements Serializable {
	private static final long serialVersionUID = 2270762174373291350L;

	@Id
	@SequenceGenerator(name="CUSTOMER_ID_GENERATOR", sequenceName="CUSTOMER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	private String name;

	@Basic(optional = false)
	private String surname;

	@Basic(optional = false)
	private String cuit;

	@Basic(optional = false)
	private boolean active;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinColumn(name = "customer_id")
	private List<Address> address;

	public Customer() {
		this.address = new ArrayList<>();
	}

	public Customer(String name, String surname, String cuit) {
		this.name = name;
		this.surname = surname;
		this.cuit = cuit;
		this.active = true;
		this.address = new ArrayList<Address>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", name=" + name + ", cuit=" + cuit +" active :"+active+", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cuit == null) {
			if (other.cuit != null)
				return false;
		} else if (!cuit.equals(other.cuit))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

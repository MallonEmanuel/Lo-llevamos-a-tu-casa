package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="provider")

@NamedQueries(
		@NamedQuery(name = "Provider.filterByName", query = "SELECT e FROM Provider e where UPPER(e.name) LIKE CONCAT (UPPER(:name),'%')")
)
public class Provider implements Serializable {
	private static final long serialVersionUID = -7921972473221727696L;

	@Id
	@SequenceGenerator(name="PROVIDER_ID_GENERATOR", sequenceName="PROVIDER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVIDER_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	private String name;

	@Basic(optional = false)
	private String cuit;
	

	@Basic(optional = false)
	private boolean active;

	public Provider() {
	}

	public Provider(String name, String cuit) {
		this.active = true;
		this.name = name;
		this.cuit = cuit;
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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", cuit=" + cuit + ", active=" + active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
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
		Provider other = (Provider) obj;
		if (active != other.active)
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


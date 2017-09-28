package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;

import javax.persistence.Basic;
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
@Table(name = "province")
@NamedQueries({ @NamedQuery(name = "Province.findByName", query = "SELECT e FROM Province e where e.name = :name") })
public class Province implements Serializable{
	private static final long serialVersionUID = -2348211100285810983L;

	@Id
	@SequenceGenerator(name="PROVINCE_ID_GENERATOR", sequenceName="PROVINCE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVINCE_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	private String name;


	@Basic(optional=false)
	private boolean active;
	
	@ManyToOne
	@Basic(optional = false)
	private Country country;

	public Province(String name, Country country) {
		this.name = name;
		this.country = country;
		this.id = 0L;
		this.active = true;
	}
	public Province(){
		this.id =0L;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		Province other = (Province) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
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

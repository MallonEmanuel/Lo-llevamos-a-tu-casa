package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "city")
@NamedQueries({
    @NamedQuery(name = "City.findByName", query = "SELECT e FROM City e WHERE e.name = :name"),
//    @NamedQuery(name = "City.findByNameConcat", query = "SELECT e FROM City e WHERE e.name LIKE CONCAT( :name,'%')"
//            + "ORDER BY e.name ASC"),
    @NamedQuery(name = "City.findByIdProvince", query = "SELECT e FROM City e WHERE e.province.id = :province_id")
})
public class City implements Serializable {
	private static final long serialVersionUID = 9145762777791095010L;

	@Id
	@SequenceGenerator(name="CITY_ID_GENERATOR", sequenceName="CITY_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITY_ID_GENERATOR")
	private long id;

	@Basic(optional = false)
	private String name;

	@Column(name = "zip_code")
	private String zipCode;

	@Basic(optional = false)
	private boolean active;

	@ManyToOne
	@Basic(optional = false)
	private Province province;

	public City(String name, Province province) {
		this.id = 0L;
		this.name = name;
		this.province = province;
		this.active = true;
	}
	public City(){
		this.id= 0L;
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + province + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
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
		City other = (City) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		return true;
	}

}

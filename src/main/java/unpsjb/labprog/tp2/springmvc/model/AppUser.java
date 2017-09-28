package unpsjb.labprog.tp2.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({ @NamedQuery(name = "AppUser.findByName", query = "SELECT e FROM AppUser e where e.username = :username") })
public class AppUser implements Serializable{
	private static final long serialVersionUID = 5354750618107797573L;

	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	private long id;
	
	private String username;
	
	private String address;
	
	private String email;
	
	public AppUser(String userName){
		id=0L;
		this.username = userName;
	}
	
	public AppUser(){
		id=0L;
	}
	
	public AppUser(long id, String username, String address, String email){
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AppUser))
			return false;
		AppUser other = (AppUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address
				+ ", email=" + email + "]";
	}
	

	
}

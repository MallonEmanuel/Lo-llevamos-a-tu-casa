package unpsjb.labprog.tp2.springmvc.util;

public class RankingCustomer {
	
	private int month;
	
	private String name;
	
	private String surname;
	
	private Double total;

	public RankingCustomer(){
	}
	
	public RankingCustomer(int month, String name, String surname, Double total) {
		this.month = month;
		this.name = name;
		this.surname = surname;
		this.total = total;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}

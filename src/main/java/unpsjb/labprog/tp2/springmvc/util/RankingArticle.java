package unpsjb.labprog.tp2.springmvc.util;

public class RankingArticle {

	private int month;
	
	private Long articleId;
	
	private String articleName;
	
	private Double price;
	
	private Double total;

	public RankingArticle(){
		
	}
	
	
	public RankingArticle(int month, String articleName, Double price, Double total) {
		super();
		this.month = month;
		this.articleName = articleName;
		this.price = price;
		this.total = total;
	}

	public RankingArticle(int month,Long articleId ,String articleName, Float price, Double total) {
		super();
		this.month = month;
		this.articleId = articleId;
		this.articleName = articleName;
		this.price = Double.valueOf(price);
		this.total = total;
	}

	
	public RankingArticle(int month, String articleName, Float price, Double total) {
		super();
		this.month = month;
		this.articleName = articleName;
		this.price = Double.valueOf(price);
		this.total = total;
	}

	
	public RankingArticle(int month, String articleName, Double total) {
		super();
		this.month = month;
		this.articleName = articleName;
//		this.price = Double.valueOf(price.toString());
		this.total = total;
	}

	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	

	public Long getArticleId() {
		return articleId;
	}


	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}


	@Override
	public String toString() {
		return "RankingArticle [month=" + month + ", articleId=" + articleId + ", articleName=" + articleName
				+ ", price=" + price + ", total=" + total + "]";
	}

	
}

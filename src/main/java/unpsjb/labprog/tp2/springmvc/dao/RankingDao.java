package unpsjb.labprog.tp2.springmvc.dao;

import java.util.Date;
import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.util.RankingArticle;
import unpsjb.labprog.tp2.springmvc.util.RankingCustomer;

public interface RankingDao extends GenericDao<ArticleOrder, Long> {

	public List<RankingCustomer> getRankingCustomer(Date beginDate,Date endDate); 
	
	public List<RankingArticle> getRankingArticle(Date beginDate,Date endDate);
	
}

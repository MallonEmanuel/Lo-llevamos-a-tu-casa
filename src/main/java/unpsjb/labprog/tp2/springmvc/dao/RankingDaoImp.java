package unpsjb.labprog.tp2.springmvc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.util.RankingArticle;
import unpsjb.labprog.tp2.springmvc.util.RankingCustomer;

@Repository
public class RankingDaoImp extends GenericDaoSpringHibernateTemplate<ArticleOrder, Long>implements RankingDao  {

	public RankingDaoImp() {
		super(ArticleOrder.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RankingCustomer> getRankingCustomer(Date beginDate, Date endDate) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery("SELECT NEW unpsjb.labprog.tp2.springmvc.util.RankingCustomer( "
										+ " month(ao.receipt.paymentDate) , "
										+ "	ao.receipt.customer.name , "
										+ "	ao.receipt.customer.surname ,"
										+ " sum(ao.price * ao.quantity)) "
										+ " FROM ArticleOrder ao "
										+ " WHERE ao.receipt.paymentDate BETWEEN :beginDate AND :endDate "
										+ " GROUP BY ao.receipt.customer.name , ao.receipt.customer.surname , month(ao.receipt.paymentDate) "
										+ " ORDER BY sum(ao.price * ao.quantity) DESC ").setParameter("beginDate", beginDate).setParameter("endDate",endDate).setMaxResults(30);		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RankingArticle> getRankingArticle(Date beginDate, Date endDate) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery("SELECT NEW unpsjb.labprog.tp2.springmvc.util.RankingArticle( "
										+ " month(ao.receipt.paymentDate) , "
										+ "	ao.article.id, "
										+ "	ao.article.name , "
										+ "	ao.article.price ,"
										+ " sum(ao.price * ao.quantity)) "
										+ " FROM ArticleOrder ao "
										+ " WHERE ao.receipt.paymentDate BETWEEN :beginDate AND :endDate "
										+ " GROUP BY ao.article.name ,ao.article.id ,ao.article.price, month(ao.receipt.paymentDate) "
										+ " ORDER BY sum(ao.price * ao.quantity) DESC ").setParameter("beginDate", beginDate).setParameter("endDate",endDate).setMaxResults(30);		
		return query.list();
	}
}

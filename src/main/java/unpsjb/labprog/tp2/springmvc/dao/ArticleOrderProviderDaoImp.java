package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrderProvider;

@Repository
public class ArticleOrderProviderDaoImp extends GenericDaoSpringHibernateTemplate<ArticleOrderProvider, Long> implements ArticleOrderProviderDao{

	public ArticleOrderProviderDaoImp() {
		super(ArticleOrderProvider.class);
	}

	@Override
	public List<ArticleOrderProvider> findByShipmentProvider(long shipmentProviderId) {
		String[] params = {"shipmentProviderId"};
		Object[] values = {shipmentProviderId};
		List<ArticleOrderProvider> list = super.findByNameQuery("ArticleOrderProvider.findByIdShipmentProvider", params, values);
		return list;
	}
	
	
}

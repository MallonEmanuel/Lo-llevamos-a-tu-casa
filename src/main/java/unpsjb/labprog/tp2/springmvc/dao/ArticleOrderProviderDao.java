package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrderProvider;

public interface ArticleOrderProviderDao extends GenericDao< ArticleOrderProvider, Long>{

	public List<ArticleOrderProvider> findByShipmentProvider(long id);

}

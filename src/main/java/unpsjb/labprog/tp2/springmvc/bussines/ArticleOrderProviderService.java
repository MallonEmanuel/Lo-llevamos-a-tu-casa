package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderProviderDao;
import unpsjb.labprog.tp2.springmvc.dao.ShipmentProviderDao;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrderProvider;
import unpsjb.labprog.tp2.springmvc.model.ShipmentProvider;

@Component
public class ArticleOrderProviderService {

	@Autowired
	ShipmentProviderDao shipmentProviderDao;
	@Autowired
	ArticleOrderProviderDao articleOrderProviderDao;

	public ArticleOrderProviderService() {
	}

	public int refresh(List<ArticleOrderProvider> articleOrderProviders, long shipmentProviderId) {
		ShipmentProvider shipmentProvider = shipmentProviderDao.find(shipmentProviderId);
		for (ArticleOrderProvider articleOrderProvider : articleOrderProviders) {
			refreshArticleOrderProvider(articleOrderProvider, shipmentProvider);
		}
		articleOrderProviderDao.bulk(articleOrderProviders);
		return articleOrderProviders.size();
	}

	public void refreshArticleOrderProvider(ArticleOrderProvider articleOrderProvider,
			ShipmentProvider shipmentProvider) {
		float quantity = articleOrderProvider.getQuantity();
		float oldStock = articleOrderProvider.getArticle().getStock();
		float newStock = oldStock + quantity;
		articleOrderProvider.getArticle().setStock(newStock);
		articleOrderProvider.setShipmentProvider(shipmentProvider);
		System.out.println("stock : " + oldStock + " + " + quantity + " = " + newStock);
		System.out.println(""+articleOrderProvider);
	}

}

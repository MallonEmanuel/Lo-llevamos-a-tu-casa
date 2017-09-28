package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.ShipmentProvider;
import unpsjb.labprog.tp2.springmvc.util.FilterProvider;

@Repository
public class ShipmentProviderDaoImp extends GenericDaoSpringHibernateTemplate<ShipmentProvider, Long> implements ShipmentProviderDao{

	public ShipmentProviderDaoImp() {
		super(ShipmentProvider.class);
	}

	@Override
	public List<ShipmentProvider> filter(FilterProvider filterProvider) {
		String[] params = {"shipmentProviderId","providerName","beginDate","endDate"};
		validateFilterProvider(filterProvider);
		System.out.println("filter provider : "+filterProvider);
		Object[] values = {filterProvider.getShipmentProviderId(),filterProvider.getProviderName(),filterProvider.getBeginDate(),filterProvider.getEndDate()};
		List<ShipmentProvider> list = super.findByNameQuery("ShipmentProvider.filter", params, values);
		return list;
	}

	private void validateFilterProvider(FilterProvider filterProvider) {
		if(filterProvider.getProviderName().isEmpty()){
			filterProvider.setProviderName("TODOS");
		}
		
	}

}

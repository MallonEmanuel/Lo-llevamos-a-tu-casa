package unpsjb.labprog.tp2.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unpsjb.labprog.tp2.springmvc.bussines.AddressGenerator;
import unpsjb.labprog.tp2.springmvc.dao.CityDao;
import unpsjb.labprog.tp2.springmvc.dao.CustomerDao;

@Service
@RestController
public class AddressGeneratorRestController {

	@Autowired
	CustomerDao customerDao;
	@Autowired
	CityDao cityDao;
	
	protected AddressGenerator addressGenerator; 
	
	@RequestMapping(value = "generator/address/", method = RequestMethod.GET)
    public String generate() {
		addressGenerator = new AddressGenerator(customerDao.findAll(),cityDao.findByProvinceId(5));	
		String s = addressGenerator.generate();
		return s;
	}
}

package unpsjb.labprog.tp2.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unpsjb.labprog.tp2.springmvc.bussines.SimulatorController;
import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderDao;
import unpsjb.labprog.tp2.springmvc.dao.CustomerDao;
import unpsjb.labprog.tp2.springmvc.dao.OrderDao;

@Service
@RestController
public class SimulatorRestController {
	
	@Autowired
	CustomerDao customerDao;
	@Autowired
	OrderDao orderDao;
	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleOrderDao articleOrderDao;
	
	@Autowired
	SimulatorController simulationController; 
	
	@RequestMapping(value = "generator/order/{id}", method = RequestMethod.GET)
    public String generate(@PathVariable("id") int id) {
		String s="";
		if(id == 1){
			s =	simulationController.init();	
		} 	
		return s;
	}
}

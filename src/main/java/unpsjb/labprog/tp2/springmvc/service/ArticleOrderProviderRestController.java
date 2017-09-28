package unpsjb.labprog.tp2.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unpsjb.labprog.tp2.springmvc.bussines.ArticleOrderProviderService;
import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderProviderDao;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrderProvider;

@Service
@RestController
public class ArticleOrderProviderRestController {

	@Autowired
	ArticleOrderProviderDao articleOrderProviderDao;
	
	@Autowired
	ArticleOrderProviderService articleOrderProviderService;
	
	/**
	 * Retorna todos los articleOrderProvider 
	 */
	@RequestMapping(value = "/articleOrderProviders/", method = RequestMethod.GET)
	public ResponseEntity<List<ArticleOrderProvider>> listAll() {
		List<ArticleOrderProvider> articleOrderProviders = articleOrderProviderDao.findAll();
		if (articleOrderProviders.isEmpty()) {
			return new ResponseEntity<List<ArticleOrderProvider>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ArticleOrderProvider>>(articleOrderProviders, HttpStatus.OK);
	}


	/**
	 * Retorna todos los articleOrderProvider de un ShipmentProvider 
	 */
	@RequestMapping(value = "/articleOrderProviders/shipmentProvider/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ArticleOrderProvider>> findByShipmentProvider(@PathVariable("id") long id) {
		List<ArticleOrderProvider> articleOrderProviders = articleOrderProviderDao.findByShipmentProvider(id);
		if (articleOrderProviders.isEmpty()) {
			return new ResponseEntity<List<ArticleOrderProvider>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<ArticleOrderProvider>>(articleOrderProviders, HttpStatus.OK);
	}

	
	
	/**
	 * Retorna un articleProviderOrder a travez de su id 
	 */
	@RequestMapping(value = "/articleOrderProvider/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArticleOrderProvider> findById(@PathVariable("id") long id) {
		ArticleOrderProvider articleOrderProvider = articleOrderProviderDao.find(id);
		if (articleOrderProvider== null) {
			return new ResponseEntity<ArticleOrderProvider>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<ArticleOrderProvider>(articleOrderProvider, HttpStatus.OK);
	}
	

	/**
	 * Guarda todos los ArticleOrderProviders y actualiza el stock
	 */
	   @RequestMapping(value = "articleOrderProviders/{id}", method = RequestMethod.POST)
	    public ResponseEntity<List<ArticleOrderProvider>> saveAll(@PathVariable("id") long id, @RequestBody List<ArticleOrderProvider> articleOrderProviders) {
		   System.out.println("id : "+id);
	        System.out.println("ArticleOrderProviders : "+articleOrderProviders);
	         
	        articleOrderProviderService.refresh(articleOrderProviders, id);
	        
	        return new ResponseEntity<List<ArticleOrderProvider>>(articleOrderProviders, HttpStatus.OK);
	    }
	
}


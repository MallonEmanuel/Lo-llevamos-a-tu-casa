package unpsjb.labprog.tp2.springmvc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import unpsjb.labprog.tp2.springmvc.dao.RankingDao;
import unpsjb.labprog.tp2.springmvc.util.FilterDate;
import unpsjb.labprog.tp2.springmvc.util.RankingArticle;
import unpsjb.labprog.tp2.springmvc.util.RankingCustomer;

@Service
@RestController
public class RankingRestController {

	@Autowired
	RankingDao rankingDao;

	/**
	 * Obtiene las estadisticas de clientes 
	 */
	@RequestMapping(value = "/ranking/customers/{beginDate}/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<List<RankingCustomer>> listAll(@PathVariable("beginDate") String beginDate, @PathVariable("endDate") String endDate) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		
		List<RankingCustomer> rankingCustomers = rankingDao.getRankingCustomer(s.parse(beginDate),s.parse(endDate));
		if (rankingCustomers.isEmpty()) {
			return new ResponseEntity<List<RankingCustomer>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<RankingCustomer>>(rankingCustomers, HttpStatus.BAD_REQUEST);
	} 
	
	/**
	 * Obtiene la estadisticas de clientes
	 */
	@RequestMapping(value = "/ranking/customers/{id}", method = RequestMethod.PUT)
	public ResponseEntity<List<RankingCustomer>> getRanking(@PathVariable("id") long id, @RequestBody FilterDate dates) {

		List<RankingCustomer> rankingCustomers = rankingDao.getRankingCustomer(dates.getBeginDate(),dates.getEndDate());
		if (rankingCustomers.isEmpty()) {
			return new ResponseEntity<List<RankingCustomer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RankingCustomer>>(rankingCustomers, HttpStatus.OK);
	}

	/**
	 * Obtiene las estadisticas de articulos  
	 */
	@RequestMapping(value = "/ranking/articles/{beginDate}/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<List<RankingArticle>> listAllArticles(@PathVariable("beginDate") String beginDate, @PathVariable("endDate") String endDate) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		
		List<RankingArticle> rankingArticles = rankingDao.getRankingArticle(s.parse(beginDate),s.parse(endDate));
		if (rankingArticles.isEmpty()) {
			return new ResponseEntity<List<RankingArticle>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<RankingArticle>>(rankingArticles, HttpStatus.BAD_REQUEST);
	} 
		

	/**
	 * Obtiene la estadisticas de clientes
	 */
	@RequestMapping(value = "/ranking/articles/{id}", method = RequestMethod.PUT)
	public ResponseEntity<List<RankingArticle>> getRankingArticle(@PathVariable("id") long id, @RequestBody FilterDate dates) {

		List<RankingArticle> rankingArticles = rankingDao.getRankingArticle(dates.getBeginDate(),dates.getEndDate());
		if (rankingArticles.isEmpty()) {
			return new ResponseEntity<List<RankingArticle>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RankingArticle>>(rankingArticles, HttpStatus.OK);
	}
}

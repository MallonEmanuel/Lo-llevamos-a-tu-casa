package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderDao;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Receipt;

@Component("receipt_generator")
public class ReceiptGenerator {

	@Autowired
	ArticleOrderDao articleOrderDao;

	@Autowired 
	ReceiptService receiptService;
	
	ReceiptServ receiptServ;
	private MyDate myDate;
	
	public ReceiptGenerator() {
	}

	public void init() {
		receiptServ = new ReceiptServ(3);
		myDate = Factory.getMyDate();
	}
	
	public int generate() {
		Receipt receiptBuild = null;
		receiptService.restart();
		List<ArticleOrder> articleOrderList = articleOrderDao
				.findAllWithShipmentDeliveredWithOutReceiptOrderByCustomer();
		
        for (ArticleOrder articleOrder : articleOrderList) {
        	if(receiptServ.generateReceipt(articleOrder, myDate.getGregorianCalendar())){
        		receiptBuild = receiptService.getReceipt(articleOrder, myDate.getGregorianCalendar().getTime());
    			articleOrder.setReceipt(receiptBuild);	
        	}
        }	
        articleOrderDao.bulk(articleOrderList);
        return receiptService.getCount();
	}

	
	
}

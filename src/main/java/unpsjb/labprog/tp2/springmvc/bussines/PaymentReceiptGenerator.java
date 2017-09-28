package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ReceiptDao;
import unpsjb.labprog.tp2.springmvc.model.Receipt;

@Component
public class PaymentReceiptGenerator {

	@Autowired
	ReceiptDao receiptDao;

	private RandomGenerator randomGenerator;
	private DateUtil dateUtil;
	private MyDate myDate;

	public PaymentReceiptGenerator() {
	}

	public void init() {
		randomGenerator = Factory.getRandomGenerator();
		dateUtil = Factory.getDateUtil();
		myDate = Factory.getMyDate();
	}

	public int generate() {
		List<Receipt> receiptList = receiptDao.getAllNonPaymentOrderByDeliveryDateDesc();
		int count =0;
		for (Receipt receipt : receiptList) {
			if (myDate.getGregorianCalendar().before(dateUtil.getNextValidDay(myDate.getGregorianCalendar(), 3))) {
				if (randomGenerator.generate(1, 3) == 1) {
					refreshShipment(receipt, myDate.getGregorianCalendar().getTime());
					count ++;
				}
			} else {
				refreshShipment(receipt, myDate.getGregorianCalendar().getTime());
				count++;
			}
		}
		receiptDao.bulk(receiptList);
		return count;
	}

	private void refreshShipment(Receipt receipt, Date today) {
		receipt.setPaymentDate(today);
	}

}

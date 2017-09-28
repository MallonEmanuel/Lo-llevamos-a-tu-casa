package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimulatorController {

	private RandomGenerator randomGenerator;

	@Autowired
	OrderGenerator orderGenerator;
	@Autowired
	ShipmentGenerator shipmentGenerator;
	@Autowired
	DeliverShipmentGenerator deliverShipmentGenerator;
	@Autowired
	ReceiptGenerator receiptGenerator;
	@Autowired
	PaymentReceiptGenerator paymentReceiptGenerator;
	@Autowired
	RefresherStock refresherStock;

	private DateUtil dateUtil;
	private MyDate myDate;

	public SimulatorController() {
		this.randomGenerator = Factory.getRandomGenerator();
		this.dateUtil = Factory.getDateUtil();
		this.myDate = Factory.getMyDate();
	}

	public String init() {
		int year = 2016;
		List<Integer> orderForMonth = randomGenerator.generate(12, 700, 1300, 12000);
		List<Integer> validDaysForMonth = dateUtil.getValidDaysForMonth(year);
		List<List<Integer>> orderForEachDay = new ArrayList<>();
		System.out.println("init");

		configureSimulator();
		long start = System.currentTimeMillis();

		for (int month = Calendar.JANUARY; month <= Calendar.DECEMBER; month++) {
			orderForEachDay
					.add(generateOrdersForEachDayOfMonth(validDaysForMonth.get(month), orderForMonth.get(month)));
			simulateMonth(year, month, validDaysForMonth.get(month), orderForEachDay);
		}
		long end = System.currentTimeMillis();
		long res = end - start;
		return "OK - Time : " + res / 1000 + " seconds";
	}

	private void configureSimulator() {
		dateUtil = Factory.getDateUtil();
		orderGenerator.init();
		shipmentGenerator.init();
		deliverShipmentGenerator.init();
		receiptGenerator.init();
		paymentReceiptGenerator.init();
		refresherStock.init();
	}

	private void simulateMonth(int year, int month, int validDaysForMonth, List<List<Integer>> orderForEachDay) {
		GregorianCalendar today = new GregorianCalendar(year, month, 1);
		myDate.setGregorianCalendar(today);
		
		@SuppressWarnings("unused")
		int orders, shipments,deliveryShipments,receipts,paymentReceipts,articles;
		
		for (int day = 0; day < validDaysForMonth; day++) {
			if (myDate.getGregorianCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				articles =refresherStock.refresh();
				System.out.println(myDate.getGregorianCalendar().getTime()+" Refresh Stock "+articles +"  articles");
			}
			orders = orderGenerator.generate((int) orderForEachDay.get(month).get(day));
			shipments = shipmentGenerator.generate();
			deliveryShipments = deliverShipmentGenerator.generate();
			receipts =receiptGenerator.generate();
			paymentReceipts =paymentReceiptGenerator.generate();
//			System.out.println(myDate.getGregorianCalendar().getTime() + "  order: " + orders + "  shipment: " 
//					+ shipments + "  deliveryShipment: "+deliveryShipments+"  receipt: "+receipts+"  paymentReceipt: "+paymentReceipts);

			GregorianCalendar gc = dateUtil.getNextValidDay(myDate.getGregorianCalendar());
			myDate.setGregorianCalendar(gc);

		}
	}

	private List<Integer> generateOrdersForEachDayOfMonth(int validDaysForThisMonth, int orderForThisMonth) {
		return randomGenerator.generate(validDaysForThisMonth, orderForThisMonth - 5, orderForThisMonth + 5,
				orderForThisMonth);
	}

}

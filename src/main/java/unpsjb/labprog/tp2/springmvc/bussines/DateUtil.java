package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	private Calendar calendar;
	
	/**
	 * Constructor de Clase
	 */
	public DateUtil(){
		calendar = Calendar.getInstance();
		gregorianCalendar = new GregorianCalendar();
	}
	
	/**
	 * Este metodo retorna la cantidad de dias de un mes
	 */
	public int getDays(int year, int month) {
		calendar.set(year, month, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
	}

	public int getInvalidDays(Calendar start, int days) {
		int count = 0;
		GregorianCalendar end = (GregorianCalendar) start.clone();
		end.add(Calendar.DATE, days);
		for (Calendar date = start; date.before(end); date.add(Calendar.DATE, 1)) {
			if (date.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				count++;
			}
		}
		return count;
	}

	
	public List<Integer> getValidDaysForMonth(int year) {
		List<Integer> daysForMonth = new ArrayList<>();
		
		for (int month = Calendar.JANUARY; month <= Calendar.DECEMBER; month++) {
			int days = getDays(year, month);
			GregorianCalendar begin = new GregorianCalendar(year, month, 1);
			int invalidDays = getInvalidDays(begin, days);
			daysForMonth.add(month, days - invalidDays);
		}
		return daysForMonth;
	}
	private GregorianCalendar gregorianCalendar;
	
//	private void setGregoriaCalendar(GregorianCalendar gregorianCalendar){
//		this.gregorianCalendar.set(Calendar.DATE, gregorianCalendar.get(Calendar.DATE));
//		this.gregorianCalendar.set(Calendar.MONTH, gregorianCalendar.get(Calendar.MONTH));
//		this.gregorianCalendar.set(Calendar.YEAR, gregorianCalendar.get(Calendar.YEAR));
//	}
	
	public GregorianCalendar getNextValidDay(GregorianCalendar startDate) {
		this.gregorianCalendar = (GregorianCalendar) startDate.clone();
		for (; true;) {
			gregorianCalendar.add(Calendar.DATE, 1);
			if (!(gregorianCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| gregorianCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				return gregorianCalendar;
			}
		}
	}

	public GregorianCalendar getNextValidDay(GregorianCalendar startDate, int days) {
		GregorianCalendar aux =null;
		this.gregorianCalendar = (GregorianCalendar) startDate.clone();
		for (; days!=0;) {
			gregorianCalendar.add(Calendar.DATE, 1);
			if (!(gregorianCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| gregorianCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				days--;
				aux = gregorianCalendar;
			}
		}
		return aux;
	}

	public boolean isValidDay(GregorianCalendar today){
		if ((today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			return false;
		}
		return true;
	}
	
}

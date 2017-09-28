package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.GregorianCalendar;

public class MyDate {

	private GregorianCalendar gregorianCalendar;
	
	public MyDate(){
		gregorianCalendar = new GregorianCalendar();
	}
	public synchronized GregorianCalendar getGregorianCalendar(){
		return gregorianCalendar;
	}
	public synchronized void setGregorianCalendar(GregorianCalendar gregorianCalendar) {
		this.gregorianCalendar = gregorianCalendar;
	}
	
	
}

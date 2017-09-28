package unpsjb.labprog.tp2.springmvc.bussines;

public class Factory {

	private static RandomGenerator randomGenerator;
	private static DateUtil dateUtil;
	private static MyDate myDate;
	
	public static RandomGenerator getRandomGenerator(){
		if(randomGenerator == null){
			randomGenerator = new RandomGenerator();
		}
		return randomGenerator;
	}
	
	public static DateUtil getDateUtil(){
		if(dateUtil == null){
			dateUtil = new DateUtil();
		}
		return dateUtil;
	}

	public static MyDate getMyDate(){
		if(myDate == null){
			myDate = new MyDate();
		}
		return myDate;
	}
}

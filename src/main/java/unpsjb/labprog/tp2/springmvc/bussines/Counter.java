package unpsjb.labprog.tp2.springmvc.bussines;

public class Counter {

	private int value;

	public Counter() {
		this.value = 0;
	}

	public void add(int a) {
		this.value += a;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}

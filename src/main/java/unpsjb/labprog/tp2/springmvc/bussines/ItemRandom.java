package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.ArrayList;
import java.util.List;

import unpsjb.labprog.tp2.springmvc.dao.Finder;

public class ItemRandom {

	private List<Object> list;
	private Finder finder;
	private int limit;
	public ItemRandom(){
	}
	
	public ItemRandom(Finder finder, int limit) {
		this.list = new ArrayList<>();
		this.finder = finder;
		this.limit = limit;
	}

	public Object getItem() {
		if (list.isEmpty()) {
			list.addAll(finder.findByRandom(limit));
		}
		return list.remove(0);
	}

}

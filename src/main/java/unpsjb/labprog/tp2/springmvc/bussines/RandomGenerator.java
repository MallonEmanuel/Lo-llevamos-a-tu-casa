package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
	private Random random;

	/**
	 * Contructor de Clase
	 */
	public RandomGenerator() {
		random = new Random();
	}

	/**
	 * @return Random
	 */
	public Random getRandom() {
		return random;
	}

	/**
	 * Este metodo genera una lista de numeros aleatorios, entre un minimo y
	 * maximo para cada cliente ingresado.
	 * 
	 * @return una lista de numeros aleatorios
	 */
	public List<Integer> generate(int clients, int min, int max, int total) {
		List<Integer> list = new ArrayList<Integer>();
		int prom = total / clients; // calcula promedio de clientes
		for (int i = 0; i < clients; i++) {
			int num = generate(min, max);
			list.add(num);
			solveError(list, i, prom);
		}
		list.set(list.size() - 1, list.get(list.size() - 2));
		return list;
	}

	/**
	 * Este metodo se ocupa de resolver errores de generate.
	 */
	private List<Integer> solveError(List<Integer> list, int i, int prom) {
		if (isMulti(i + 1, 2) && i != 0) { // cada dos elementos
			int error = (prom * 2) - (list.get(i) + list.get(i - 1));
			if (isMulti(error, 2)) {
				list.set(i, list.get(i) + (error / 2));
				list.set(i - 1, list.get(i - 1) + (error / 2));
			} else {
				int aux = error + 1;
				list.set(i, list.get(i) + (aux / 2));
				list.set(i - 1, list.get(i - 1) + (aux / 2) - 1);
			}
		}
		return list;
	}

	/**
	 * Este metodo genera un numero aleatorio entre, minimo y maximo
	 * 
	 * @return un numero aleatorio
	 */
	public int generate(int min, int max) {
		return random.nextInt(max - min) + min;
	}

	/**
	 * Este metodo verifica si value es multiplo de multi
	 * 
	 * @return true si value es multiple de multi
	 */
	public boolean isMulti(int value, int multi) {
		return (value % multi == 0);
	}

	/**
	 * Este metodo obtiene un elemento aleatorio de una lista
	 * 
	 * @return un elemento aleatorio de una lista
	 */
	public Object getRandomItem(List<? extends Object> list) {
		int index = random.nextInt(list.size());
		return list.get(index);
	}
	
	/**
	 * Este metodo obtiene un elemento aleatorio de una lista
	 * 
	 * @return un elemento aleatorio de una lista
	 */
	public Object getRandomItemDistinct(List<? extends Object> list, Object o) {
		int index = random.nextInt(list.size());
		Object o1 = list.get(index);
		while (o1.equals(o)) {
			index = random.nextInt(list.size());
			o1 = list.get(index);
		}
		return o1;
	}

}
import java.util.Comparator;

public class ComparadorPeso implements Comparator<Arista>{

	@Override
	public int compare(Arista arista1, Arista arista2) {
		return arista2.getPeso() - arista1.getPeso();
	}

}

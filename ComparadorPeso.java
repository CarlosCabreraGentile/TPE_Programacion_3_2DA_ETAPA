import java.util.Comparator;

public class ComparadorPeso implements Comparator<Arista>{

	@Override
	public int compare(Arista a0, Arista a1) {
		// TODO Auto-generated method stub
		return a1.getPeso() - a0.getPeso();
	}

}

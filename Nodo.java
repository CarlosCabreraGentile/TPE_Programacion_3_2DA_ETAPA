import java.util.ArrayList;
import java.util.Collections;

public class Nodo { 
	String valor;
	ArrayList<Arista> aristasAdyacentes; 
	Nodo padre;
	String estado;
	
	public Nodo(String valor) {
		this.valor = valor;
		aristasAdyacentes = new ArrayList<Arista>();
	}
	public void resetAristas() {
		aristasAdyacentes.removeAll(this.aristasAdyacentes);
	}
	public Nodo getPadre() {
		return padre;
	}
	
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getValor() {
		return valor;
	}
	
	/**
	 * @return
	 * Devuelve las aristas adyacentes que tiene un nodo
	 */
	public ArrayList <Arista> getAristasAdyacentes() {
		return aristasAdyacentes;
	} 
	
	/**
	 * @param arista
	 * Agrega aristas adyacentes al nodo
	 */
	public void addAristasAdyacentes(Arista arista) {
		aristasAdyacentes.add(arista);
	}

	/**
	 * @param nodoDestino
	 * @return
	 * Devuelve verdadero o falso dependiendo 
	 * si existe el nodo destino 
	 */
	public Arista existeAristaHaciaNodoDestino(Nodo nodoDestino) { 
		Arista aux = null;
		int i = 0;
		while(i < aristasAdyacentes.size()) { 
			if(aristasAdyacentes.get(i).getNodoDestino().equals(nodoDestino)) {
				return aristasAdyacentes.get(i);
			}
			i++;
		} 
		return aux;
	}
	
	/**
	 * @return
	 * Devuelve la cantidad de aristas adyacentes que tiene un nodo
	 */
	public int cantAristasAdyacentes() {
		return aristasAdyacentes.size();
	}

	/**
	 * Ordena los pesos de las aristas
	 * del nodo de mayor a menor
	 * 
	 */
	public void ordenarAristasXpeso() { 
		Collections.sort(aristasAdyacentes, new ComparadorPeso());
	}

}

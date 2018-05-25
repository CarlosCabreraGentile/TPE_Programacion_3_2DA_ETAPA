

import java.util.ArrayList;
import java.util.Collections;

public class Nodo { 
	String valor;
	ArrayList <Arista> aristasAdyacentes;
	Nodo padre;
	String estado;
	int distancia;
	
	public Nodo(String valor) {
		this.valor = valor;
		aristasAdyacentes = new ArrayList<Arista>();
	}

	public int getDistancia() {
		return distancia;
	}
	
	public void setDistancia(int distancia) {
		this.distancia = distancia;
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
	
	public ArrayList <Arista> getAristasAdyacentes() {
		return aristasAdyacentes;
	} 
	
	public void addAristasAdyacentes(Arista arista) {
		aristasAdyacentes.add(arista);
	}
	
	private void insertXPeso(Arista arista) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param nodoDestino
	 * @return
	 * Devuelve verdadero o falso dependiendo 
	 * si existe el nodo destino
	 */
	public Arista existeArista(Nodo nodoDestino) { 
		Arista aux=null;
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

	public void ordenarAristasXpeso() {
		// TODO Auto-generated method stub
		Collections.sort(aristasAdyacentes, new ComparadorPeso());
	}

}

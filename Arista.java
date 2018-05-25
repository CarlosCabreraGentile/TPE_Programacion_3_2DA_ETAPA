

public class Arista {
	Nodo origen;
	Nodo destino;
	int peso;
	
	public Arista(Nodo origen, Nodo destino) {
		this.origen = origen;
		this.destino = destino;
		this.setPeso();
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso() {
		this.peso += 1;
	}
	public void setPesoMerge(int peso) {
		this.peso=peso;
	}

	public Nodo getNodoOrigen() {
		return origen;
	}
	
	public Nodo getNodoDestino() {
		return destino;
	}

	public void setNodoOrigen(Nodo origen) {
		this.origen = origen;
	}
	
	public void setNodoDestino(Nodo destino) {
		this.destino = destino;
	}
	
}

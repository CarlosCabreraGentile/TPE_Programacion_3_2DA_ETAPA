import java.util.ArrayList;

public class GrafoDirigido {
	static int contador = 1;
	ArrayList<Nodo> listaNodos;
	ArrayList<String> estado;
	ArrayList<Nodo> padre;

	public GrafoDirigido() {
		listaNodos = new ArrayList<Nodo>(); 
		estado = new ArrayList<String>();
		padre = new ArrayList<Nodo>();
	} 

	public void agregarNodo(Nodo n) {  
		listaNodos.add(n);
	}

	public void agregarArista(Nodo nodoOrigen, Nodo nodoDestino) {
		Arista arista = new Arista(nodoOrigen, nodoDestino);
		nodoOrigen.addAristasAdyacentes(arista);
	}

	/**
	 * @param n
	 * @return Busca en el grafo el nodo que se pasa por parametro en 
	 * caso de encontrarlo lo devuelve, sino lo encuentra devuelve null
	 */
	public Nodo obtenerNodo(String n) {
		Nodo aux = null;

		int i = 0;
		while (i < listaNodos.size()) {
			if (n.equals(listaNodos.get(i).getValor())) {
				return listaNodos.get(i);
			}
			i++;
		}
		return aux;
	}

	/**
	 * @return Devuelve todos los nodos de la lista
	 */
	public ArrayList<Nodo> obtenerNodos() {
		return listaNodos;
	}

	/**
	 * @return Devuelve en numeros la cantidad de nodos que hay
	 */
	public int numNodos() {
		return listaNodos.size();
	}

	/**
	 * @return Devuelve en numeros la cantidad de aristas que hay
	 */
	public int numAristas() { 
		int sum = 0;
		for (int i = 0; i < listaNodos.size(); i++) {
			sum += listaNodos.get(i).cantAristasAdyacentes();
		}
		return sum;
	}  

	/**
	 * @param nodoOrigen
	 * @param nodoDestino
	 * @return
	 * Devuelve la arista entre ambos nodos dependiendo 
	 * si existe una que los conecte 
	 */
	public Arista existeArista(Nodo nodoOrigen, Nodo nodoDestino) {
		Arista aux = null;
		int i = 0; 
		
		while (i < listaNodos.size()) {
			if (nodoOrigen.equals(listaNodos.get(i))) { 
				return nodoOrigen.existeAristaHaciaNodoDestino(nodoDestino);
			} 
			i++;
		}
		return aux;
	} 

	public ArrayList<Arista> obtenerAristasAdyacentes(Nodo n) { 
		return n.getAristasAdyacentes();
	}

	/*
	 * VERSION MEGA NODO
	 * 
	 * */

	public void DFS() {  
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).setEstado("blanco");
			listaNodos.get(i).setPadre(null);
		}
		for (int i = 0; i < listaNodos.size(); i++) {
			if (listaNodos.get(i).getEstado().equals("blanco")) {
				dfsVisitar(listaNodos.get(i)); 
			}
		}
		for (int i = 0; i < listaNodos.size(); i++) {
			System.out.print("hijo " + listaNodos.get(i).getValor() + " ");
			System.out.print(listaNodos.get(i).getEstado() + " - ");
			if(listaNodos.get(i).getPadre()!=null) {
				System.out.print("padre" + listaNodos.get(i).getPadre().getValor());
			}else {
				System.out.print("padre " + listaNodos.get(i).getPadre());
			}

			System.out.println("");
		} 
	}  

	private void dfsVisitar(Nodo vertice) { 
		vertice.setEstado("amarillo");
		 
		/*Traigo todas las aristas del vertice*/
		ArrayList <Arista> listAux = vertice.getAristasAdyacentes();
		
		for (int j = 0; j < vertice.getAristasAdyacentes().size(); j++) {
			if(listAux.get(j).getNodoDestino().getEstado().equals("blanco")) {
				listAux.get(j).getNodoDestino().setPadre(vertice);
				dfsVisitar(listAux.get(j).getNodoDestino());
			}
		}
		vertice.setEstado("negro");
	}

	/*
	 * FIN VERSION MEGA NODO
	 * 
	 * */

	public void BFS(Nodo s) { 
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).setDistancia(0);
			listaNodos.get(i).setEstado("no_visitado");
			listaNodos.get(i).setPadre(null);
		}
		s.setEstado("visitado");
		s.setDistancia(0);
		s.setPadre(null);
	}
 
	public boolean DFS_Ciclo() {
		boolean hayCiclo = false;
		int j = 0;
		
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).setEstado("blanco"); 
			listaNodos.get(i).setPadre(null);
		}
		
		/*Traigo todas las aristas del vertice*/
		ArrayList <Arista>auxAdyacentes = listaNodos.get(0).getAristasAdyacentes();
		
		while( (j < auxAdyacentes.size()) && (!hayCiclo) ) {
			if(auxAdyacentes.get(j).getNodoDestino().getEstado().equals("blanco")) {
				hayCiclo = hayCiclo(auxAdyacentes.get(j).getNodoDestino());
			}
			j++;
		}
		System.out.println("Largo del ciclo: " + contador);
		return hayCiclo; 
	}
	private boolean hayCiclo(Nodo v) {
		int i = 0;
		boolean hayCiclo = false;
		//seteo el estado del nodo en amarillo
		v.setEstado("amarillo");
		
		ArrayList <Arista>auxAdya = v.getAristasAdyacentes();

		while((i<auxAdya.size())&&(!hayCiclo)) {
			if(auxAdya.get(i).getNodoDestino().getEstado().equals("blanco")) {
				contador++;
				hayCiclo = hayCiclo(auxAdya.get(i).getNodoDestino());
			}
			else if(auxAdya.get(i).getNodoDestino().getEstado().equals("amarillo")) {
				hayCiclo = true;
			}
			i++;
		}
		v.setEstado("negro");
		return hayCiclo;
	} 

	public ArrayList<Nodo> maxDistancia (Nodo i, Nodo j){
		ArrayList<Nodo> listaMayor = new ArrayList <Nodo>();
		ArrayList<Nodo> listaAux = new ArrayList <Nodo>();

		ArrayList<Arista> adyacentes = i.getAristasAdyacentes();

		if ((adyacentes.size() > 0) && !(i.equals(j))) {
			for (int k=0;  k < adyacentes.size(); k++) {
				listaAux.add(i);
				listaAux.addAll(maxDistancia(adyacentes.get(k).getNodoDestino(), j));
				if (!listaAux.isEmpty() && (listaMayor.size() < listaAux.size())) {
					listaMayor = listaAux;
				}
			}
		}
		return listaMayor;
	}
	public void printGrafo() {
		for (int i = 0; i < listaNodos.size(); i++) {
				printNodo(listaNodos.get(i)); 
		}
	}

	private void printNodo(Nodo nodo) {
		/*Traigo todas las aristas del vertice*/
		ArrayList <Arista> listAux = nodo.getAristasAdyacentes();
		
		for (int j = 0; j < listAux.size(); j++) {
				System.out.println(nodo.getValor() + "->" + listAux.get(j).getNodoDestino().getValor() +
						"[label=" + '"' + listAux.get(j).getPeso() + '"' + ",weight =" + '"' +
						listAux.get(j).getPeso() + '"' + "];");
			}
	}
	
	public void ordenarAristasNodo() { 
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).ordenarAristasXpeso();
		}
	}

}

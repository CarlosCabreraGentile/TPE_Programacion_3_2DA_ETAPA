import java.util.ArrayList;

public class GrafoDirigido {
	static int contador = 1;
	ArrayList<Nodo> listaNodos;
	ArrayList<String> estado;
	ArrayList<Nodo> padre;
	boolean hayCiclo=false;

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
//	public boolean DFS_Ciclo() {
//		boolean hayCiclo = false;
//		int j = 0;
//		
//		for (int i = 0; i < listaNodos.size(); i++) {
//			listaNodos.get(i).setEstado("blanco"); 
//			listaNodos.get(i).setPadre(null);
//		}
//		
//		/*Traigo todas las aristas del vertice*/
//		ArrayList <Arista>auxAdyacentes = listaNodos.get(0).getAristasAdyacentes();
//		
//		while( (j < auxAdyacentes.size()) && (!hayCiclo) ) {
//			if(auxAdyacentes.get(j).getNodoDestino().getEstado().equals("blanco")) {
//				hayCiclo = hayCiclo(auxAdyacentes.get(j).getNodoDestino());
//			}
//			j++;
//		}
//		System.out.println("Largo del ciclo: " + contador);
//		return hayCiclo; 
//	}
//	private boolean hayCiclo(Nodo v) {
//		int i = 0;
//		boolean hayCiclo = false;
//		//seteo el estado del nodo en amarillo
//		v.setEstado("amarillo");
//		
//		ArrayList <Arista>auxAdya = v.getAristasAdyacentes();
//
//		while((i<auxAdya.size())&&(!hayCiclo)) {
//			if(auxAdya.get(i).getNodoDestino().getEstado().equals("blanco")) {
//				contador++;
//				hayCiclo = hayCiclo(auxAdya.get(i).getNodoDestino());
//			}
//			else if(auxAdya.get(i).getNodoDestino().getEstado().equals("amarillo")) {
//				hayCiclo = true;
//			}
//			i++;
//		}
//		v.setEstado("negro");
//		return hayCiclo;
//	} 

//	public ArrayList<Nodo> maxDistancia (Nodo i, Nodo j){
//		ArrayList<Nodo> listaMayor = new ArrayList <Nodo>();
//		ArrayList<Nodo> listaAux = new ArrayList <Nodo>();
//
//		ArrayList<Arista> adyacentes = i.getAristasAdyacentes();
//
//		if ((adyacentes.size() > 0) && !(i.equals(j))) {
//			for (int k=0;  k < adyacentes.size(); k++) {
//				listaAux.add(i);
//				listaAux.addAll(maxDistancia(adyacentes.get(k).getNodoDestino(), j));
//				if (!listaAux.isEmpty() && (listaMayor.size() < listaAux.size())) {
//					listaMayor = listaAux;
//				}
//			}
//		}
//		return listaMayor;
//	}
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
	
	public ArrayList<Nodo> dfsgenerosMasBuscados(String genero) { 
		Nodo temporal = this.obtenerNodo(genero);
		ArrayList <Arista> listaAristaAux=temporal.getAristasAdyacentes();
		ArrayList <Nodo> retorno=new ArrayList<Nodo>();
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).setEstado("blanco");
		}
		for (int i = 0; i < listaAristaAux.size(); i++) {
			if (listaAristaAux.get(i).getNodoDestino().getEstado().equals("blanco")) {
				dfsGenerosMasBuscadosVisitar(listaAristaAux.get(i).getNodoDestino(),retorno); 
			}
		}
		return retorno;
	}

	private void dfsGenerosMasBuscadosVisitar(Nodo nodo, ArrayList<Nodo> retorno) {
		// TODO Auto-generated method stub
		if(nodo.getEstado().equals("blanco")) {
			retorno.add(nodo);
		}
		nodo.setEstado("amarillo");

		ArrayList <Arista> listAux = nodo.getAristasAdyacentes();
		
		for (int j = 0; j < listAux.size(); j++) {
			if(listAux.get(j).getNodoDestino().getEstado().equals("blanco")) {
				dfsGenerosMasBuscadosVisitar(listAux.get(j).getNodoDestino(),retorno);
			}
		}
		nodo.setEstado("negro");
	}
	public GrafoDirigido DFS_Ciclo(String genero) {
		GrafoDirigido retornoGrafo = new GrafoDirigido();
		boolean exit = false;
		int j = 0;
		Nodo temporal = this.obtenerNodo(genero);
		Nodo tempAux = new Nodo(temporal.getValor());
		retornoGrafo.agregarNodo(tempAux);
		
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).setEstado("blanco"); 
		}
		
		/*Traigo todas las aristas del vertice*/
		ArrayList <Arista>auxAdyacentes = temporal.getAristasAdyacentes();
		
		while( (j < auxAdyacentes.size())&&(!exit)) {
			if(auxAdyacentes.get(j).getNodoDestino().getEstado().equals("blanco")) {
				Nodo aux1 =new Nodo(auxAdyacentes.get(j).getNodoDestino().getValor());
				retornoGrafo.agregarArista(tempAux, aux1);
				hayCiclo(auxAdyacentes.get(j).getNodoDestino(),retornoGrafo,temporal);
				exit=retornoGrafo.hayCiclo;
			}
			j++;
		}
//		System.out.println("Largo del ciclo: " + contador);
		return retornoGrafo; 
	}
	private void hayCiclo(Nodo v,GrafoDirigido retornoGrafo,Nodo temporal) {
		temporal.setEstado("amarillo");
		boolean exit=false;
		int i = 0;
		//seteo el estado del nodo en amarillo+
		v.setEstado("amarillo");
		Nodo auxV = new Nodo(v.getValor());
		retornoGrafo.agregarNodo(auxV);
		ArrayList <Arista>auxAdya = v.getAristasAdyacentes();
		if(auxAdya.isEmpty()) {
			retornoGrafo.remove(auxV);
		}else {
			while((i<auxAdya.size())&&(!exit)) {
				Nodo AuxDestino = new Nodo(auxAdya.get(i).getNodoDestino().getValor());
				if(auxAdya.get(i).getNodoDestino().getEstado().equals("blanco")) {
					 hayCiclo(auxAdya.get(i).getNodoDestino(),retornoGrafo,temporal);
					 if(retornoGrafo.obtenerNodo(auxAdya.get(i).getNodoDestino().getValor())==null) {
						 retornoGrafo.remove(v);
					 }else { 
						retornoGrafo.agregarArista(auxV,AuxDestino); 
					 }
				}
				else if(auxAdya.get(i).getNodoDestino().getEstado().equals("amarillo")&&(auxAdya.get(i).getNodoDestino().equals(temporal))) {
					retornoGrafo.agregarArista(auxV,AuxDestino);
					retornoGrafo.hayCiclo = true;
					exit=true;
				}
				i++;
			}
		}
		v.setEstado("negro");
	}

	private void remove(Nodo v) {
		// TODO Auto-generated method stub
		listaNodos.remove(v);
	} 

}

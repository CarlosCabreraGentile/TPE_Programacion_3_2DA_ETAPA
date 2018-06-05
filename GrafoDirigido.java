import java.util.ArrayList;

public class GrafoDirigido {
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
	 * @return Busca en el grafo el nodo que se pasa por parametro en caso de
	 *         encontrarlo lo devuelve, sino lo encuentra devuelve null
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
	 * @return Devuelve la arista entre ambos nodos dependiendo si existe una que
	 *         los conecte
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

	/**
	 * Este Metodo lo utilizamos para poder comprobar en la pagina
	 * graphs.grevian.org si nuestros grafos estaban bien
	 */
	public void printGrafo() {
		for (int i = 0; i < listaNodos.size(); i++) {
			printNodo(listaNodos.get(i));
		}
	}

	/**
	 * @param nodo
	 *            metodo soporte de printGrafo
	 */
	private void printNodo(Nodo nodo) {
		/* Traigo todas las aristas del vertice */
		ArrayList<Arista> listAux = nodo.getAristasAdyacentes();

		for (int j = 0; j < listAux.size(); j++) {
			System.out.println(nodo.getValor() + "->" + listAux.get(j).getNodoDestino().getValor() + "[label=" + '"'
					+ listAux.get(j).getPeso() + '"' + ",weight =" + '"' + listAux.get(j).getPeso() + '"' + "];");
		}
	}

	/**
	 * Este metodo lo utilizamos para ordenar las aristas de cada nodo por peso para
	 * posteriormente realizar una consulta mas simple al obtener los n mas buscados
	 */
	public void ordenarAristasNodo() {
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).ordenarAristasXpeso();
		}
	}

	/**
	 * @param genero
	 * @return ArrayList<Nodo> Este Metodo implementa la estructura del algoritmo
	 *         DFS para resolver el servicio Obtener todos los géneros que fueron
	 *         buscados luego de buscar por el género X
	 */
	public ArrayList<Nodo> dfsGenBuscadosAfterXGen(String genero) {
		Nodo temporal = this.obtenerNodo(genero);
		ArrayList<Arista> listaAristaAux = temporal.getAristasAdyacentes();
		ArrayList<Nodo> retorno = new ArrayList<Nodo>();
		for (int i = 0; i < listaNodos.size(); i++) {
			listaNodos.get(i).setEstado("blanco");
		}
		for (int i = 0; i < listaAristaAux.size(); i++) {
			if (listaAristaAux.get(i).getNodoDestino().getEstado().equals("blanco")) {
				dfsGenBuscadosAfterXGenVisitar(listaAristaAux.get(i).getNodoDestino(), retorno);
			}
		}
		return retorno;
	}

	/**
	 * @param nodo
	 * @param retorno
	 * Este metodo hace de soporte dfsGenBuscadosAfterXGen en la recursion 
	 */
	private void dfsGenBuscadosAfterXGenVisitar(Nodo nodo, ArrayList<Nodo> retorno) {
		if (nodo.getEstado().equals("blanco")) {
			retorno.add(nodo);
		}
		nodo.setEstado("amarillo");

		ArrayList<Arista> listAux = nodo.getAristasAdyacentes();

		for (int j = 0; j < listAux.size(); j++) {
			if (listAux.get(j).getNodoDestino().getEstado().equals("blanco")) {
				dfsGenBuscadosAfterXGenVisitar(listAux.get(j).getNodoDestino(), retorno);
			}
		}
		nodo.setEstado("negro");
	}

	/**
	 * @param genero
	 * @return
	 * Este Metodo implementa la estructura del algoritmo
	 * DFS para poder encontrar los ciclos
	 */
	public ArrayList<Nodo> DFS_Ciclo(String genero) {
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		for (int i = 0; i < listaNodos.size(); i++) {
			if (listaNodos.get(i).getValor().equals(genero)) {
				listaNodos.get(i).setEstado("negro");
			} else {
				listaNodos.get(i).setEstado("blanco");
			}
			aristas.addAll(listaNodos.get(i).getAristasAdyacentes());
		}
		Nodo temporal = this.obtenerNodo(genero);
		ArrayList<Nodo> auxRetorno = new ArrayList<Nodo>();
		ArrayList<Arista> auxAdyacentes = temporal.getAristasAdyacentes();
		auxRetorno.add(temporal);
		for (int i = 0; i < auxAdyacentes.size(); i++) {
			Nodo auxDestino = auxAdyacentes.get(i).getNodoDestino();
			if (auxDestino.getEstado().equals("blanco")) {
				hayCiclo(auxDestino, auxRetorno, temporal);
			}

		}
		for (int i = 0; i < auxRetorno.size(); i++) {
			controlFaltanCiclos(auxRetorno.get(i), auxRetorno, aristas);
		}
	
		return auxRetorno;
	}
	/**
	 * @param v
	 * @param auxRetorno
	 * @param temporal
	 * Este metodo hace de soporte DFS_Ciclo en la recursion
	 */
	private void hayCiclo(Nodo v, ArrayList<Nodo> auxRetorno, Nodo temporal) {
		v.setEstado("amarillo");
		ArrayList<Arista> auxAdya = v.getAristasAdyacentes();
		for (int i = 0; i < auxAdya.size(); i++) {
			Nodo auxDestino = auxAdya.get(i).getNodoDestino();
			if (auxDestino.getEstado().equals("blanco")) {
				hayCiclo(auxDestino, auxRetorno, temporal);
				if ((auxRetorno.contains(auxDestino)) && !(auxRetorno.contains(v))) {
					auxRetorno.add(v);
				}
			} else if ((auxDestino.getEstado().equals("negro")) && ((auxDestino.equals(temporal)))) {
				auxRetorno.add(v);
			} else if (((auxDestino.getEstado().equals("negro")) || (auxDestino.getEstado().equals("amarillo")))
						&& (auxRetorno.contains(auxDestino)) && !(auxRetorno.contains(v))) {
				auxRetorno.add(v);
			}
		}
		v.setEstado("negro");
	}

	/**
	 * @param aux
	 * @param auxRetorno
	 * @param aristas
	 * En este metodo solucionamos los ciclos que no se agregaron
	 */
	private void controlFaltanCiclos(Nodo aux, ArrayList<Nodo> auxRetorno, ArrayList<Arista> aristas) {
		ArrayList<Arista> soyDestino = soyDest(aux, aristas);
		int max = soyDestino.size();
		for (int i = 0; i < max; i++) {
			Nodo auxNodoOrigen = soyDestino.get(i).getNodoOrigen();
			if (!auxRetorno.contains(auxNodoOrigen)) {
				controlFaltanCiclos(auxNodoOrigen, auxRetorno, aristas);
				if (auxRetorno.contains(auxNodoOrigen) && !(auxRetorno.contains(aux))) {
					auxRetorno.add(aux);
				}
			} else if (auxRetorno.contains(auxNodoOrigen) && !(auxRetorno.contains(aux))) {
				auxRetorno.add(aux);
			}
		}

	}

	/**
	 * @param destino
	 * @param aristas
	 * @return
	 * Metodo que nos proporciona las arista que apuntan a un nodo
	 */
	private ArrayList<Arista> soyDest(Nodo destino, ArrayList<Arista> aristas) {
		ArrayList<Arista> soyDestino = new ArrayList<Arista>();
		for (int j = 0; j < aristas.size(); j++) {
			if (aristas.get(j).getNodoDestino().equals(destino)) {
				soyDestino.add(aristas.get(j));
			}
		}
		return soyDestino;
	}


}

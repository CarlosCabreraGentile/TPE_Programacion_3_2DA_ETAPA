public interface Grafo {
	
	public void agregarNodo(Nodo n); 
	/*agrega al grafo una nueva	arista dirigida que conecta dos vértices.*/
	public void agregarArista(Nodo a, Nodo b); 
	/* encuentra el vértice en el grafo con	nombre claveNodo.*/
	public Nodo obtenerNodo(Object o);

	/* devuelve la cantidad de Vértices.*/
	public int numVertices();
	/*devuelve la cantidad de Aristas.*/
	public int  numAristas();
	/* devuelve true si existe una arista que conecte los vértices dados.*/
	public boolean existeArista(Nodo o, Nodo a);

}

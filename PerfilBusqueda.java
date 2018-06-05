import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PerfilBusqueda {

	public static void main(String[] args) {
		GrafoDirigido profile=new GrafoDirigido();
//		ArrayList<Nodo> prueba;
//		Nodo v1 = new Nodo ("1");
//		Nodo v2 = new Nodo ("2");
//		Nodo v3 = new Nodo ("3");
//		Nodo v4 = new Nodo ("4");
//		Nodo v5 = new Nodo ("5");
//		Nodo v6 = new Nodo ("6");
//		Nodo v7 = new Nodo ("7");
//		Nodo v8 = new Nodo ("8");
//		Nodo v9 = new Nodo ("9");
//		Nodo v10 = new Nodo ("10");
//		Nodo v11 = new Nodo ("11");
//		profile.agregarNodo(v1);
//		profile.agregarNodo(v2);
//		profile.agregarNodo(v3);
//		profile.agregarNodo(v4);
//		profile.agregarNodo(v5);
//		profile.agregarNodo(v6);
//		profile.agregarNodo(v7);
//		profile.agregarNodo(v8);
//		profile.agregarNodo(v9);
//		profile.agregarNodo(v10);
//		profile.agregarNodo(v11);
//		profile.agregarArista(v1, v2);
//		profile.agregarArista(v2, v3);
//		profile.agregarArista(v3, v4);
//
//		profile.agregarArista(v4, v1);
//		profile.agregarArista(v4, v5);
//		profile.agregarArista(v5, v1);
//		profile.agregarArista(v2, v8);
//		profile.agregarArista(v4, v7);
//	
//		profile.agregarArista(v7, v5);
//		profile.agregarArista(v5, v6);
//		profile.agregarArista(v6, v9);
//		profile.agregarArista(v4, v9);
//		profile.agregarArista(v9, v5);
//		profile.agregarArista(v9, v11);
//		profile.agregarArista(v11, v10);
//		profile.agregarArista(v10, v2);
//		profile.printGrafo();
//		System.out.println("aca termina");
//		prueba=profile.DFS_Ciclo("1");
//		printArray(prueba);
		profile = input(obtenerNumero());
		profile.ordenarAristasNodo();
		profile.printGrafo();
		printArray(obtenerLosNMasBuscados(5,"viajes",profile));
		printArray(profile.DFS_Ciclo("viajes"));
		printArray(profile.dfsGenBuscadosAfterXGen("viajes"));
	}
	public static void printArray(ArrayList<Nodo>aux) {
		for (int i = 0; i < aux.size(); i++) {
			System.out.println(aux.get(i).getValor()+" ");
		}
	}
	public static void printPeso(Nodo a) {
		ArrayList <Arista>aux = a.getAristasAdyacentes();
		for (int i = 0; i < aux.size(); i++) {
			System.out.print(aux.get(i).getNodoDestino().getValor()+" "+aux.get(i).getPeso()+" ");
		}
		System.out.println("");
	}
	
	public static int obtenerNumero() {
		boolean exit = false;
		int valor = 0;

		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

		do {
			try {
				System.out.println("Ingrese numero");
				valor = new Integer(entrada.readLine());
				if ((valor > 0) && (valor < 5)) {
					exit = true;
				} else {
					System.out.println("Numero incorrecto");
					exit = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("Numero invalido");
				exit = false;
			} catch (Exception exc) {
				System.out.println(exc);
				exit = false;
			}
		} while (!exit);
		return valor;  
	}
	
	public static GrafoDirigido input(int numero) {
		String csvFile = "C:\\Users\\maxi\\Desktop\\TPE_Programacion_3_2DA_ETAPA-master\\dataset" + numero + ".csv";
		String line = "";
		String cvsSplitBy = ",";
		GrafoDirigido profileSearch = new GrafoDirigido();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				if(items.length>1) {
					armarPerfilDeBusqueda(profileSearch,items);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return profileSearch; 
	}

	/**
	 * @param perfil
	 * @param cadenaDeBusqueda
	 * A partir de una cadena de busqueda, se toman dos nodos y si no existen los agrega al grafo,
	 * sino le suma peso a la arista que los une 
	 */
	public static void armarPerfilDeBusqueda (GrafoDirigido perfil, String []cadenaDeBusqueda ){
		for (int i=0; i<cadenaDeBusqueda.length-1; i++) {
			 
			Nodo aux1 = perfil.obtenerNodo(cadenaDeBusqueda[i]);
			Nodo aux2 = perfil.obtenerNodo(cadenaDeBusqueda[i+1]);

			if(aux1 == null){
				Nodo v = new Nodo (cadenaDeBusqueda[i]);
				perfil.agregarNodo(v);
				aux1 = v;
			}
			if(aux2 == null){
				Nodo v2 = new Nodo (cadenaDeBusqueda[i+1]);
				perfil.agregarNodo(v2);
				aux2 = v2;
			}

			Arista a = perfil.existeArista(aux1, aux2); 
			if(a == null){
				perfil.agregarArista(aux1, aux2);
			}
			else{ 
				a.setPeso();
			}	
		} 
	}
	 
	/**
	 * @param n
	 * @param genero
	 * @param profile
	 * @return
	 * Duvuelve los N nodos mas buscados a partir de 
	 * uno pasado por parametro
	 */
	public static ArrayList<Nodo> obtenerLosNMasBuscados(int n, String genero, GrafoDirigido profile){
		ArrayList<Nodo> listaNodosAuxiliar = new ArrayList<Nodo>();
		Nodo temporal = profile.obtenerNodo(genero);
		ArrayList<Arista> listaAristasAuxiliar = temporal.getAristasAdyacentes();
		
		if(n > listaAristasAuxiliar.size()) {
			for (int i = 0; i < listaAristasAuxiliar.size(); i++) {
				listaNodosAuxiliar.add(listaAristasAuxiliar.get(i).getNodoDestino());
			}
		}
		else {
			for (int i = 0; i < n; i++) {
				listaNodosAuxiliar.add(listaAristasAuxiliar.get(i).getNodoDestino());			
				}
		}
		return listaNodosAuxiliar;
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PerfilBusqueda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrafoDirigido profile;
//		Nodo nod1=new Nodo("viajes");
//		Nodo nod2=new Nodo("viajes1");
//		Nodo nod3=new Nodo("viajes2");
//		Nodo nod4=new Nodo("viajes3");
//		profile.agregarNodo(nod1);
//		profile.agregarNodo(nod2);
//		profile.agregarNodo(nod3);
//		profile.agregarNodo(nod4);
//		profile.agregarArista(nod1, nod2);
//		profile.agregarArista(nod2, nod3);
//		profile.agregarArista(nod3, nod4);
		profile = input(obtenerNumero());
		printPeso(profile.obtenerNodo("viajes"));
		profile.ordenarAristasNodo();
		printPeso(profile.obtenerNodo("viajes"));
		System.out.println(profile.listaNodos.size());
		System.out.println(obtenerLosNMasBuscados(5,"viajes",profile));
//		profile.printGrafo();

	}
	public static void printPeso(Nodo a) {
		ArrayList <Arista>aux=a.getAristasAdyacentes();
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
		String csvFile = "C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe\\dataset2daEntrega\\dataset" + numero + ".csv";
		String line = "";
		String cvsSplitBy = ",";
		GrafoDirigido profileSearch = new GrafoDirigido();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				if(items.length>1) {
				armarPerfilDeBusqueda (profileSearch,items);
				}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		profileSearch.ordenarAristasNodo();
		return profileSearch; 
	}
//	private static String[] separadorGeneros(String generos) {
//		String[] arrGeneros = generos.split(" ");
//		return arrGeneros;
//	}
//	public static void output(MySimpleLinkedList listaLibros) {
//		BufferedWriter bw = null;
//		try {
//			File file = new File("C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe/salida.csv");
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			FileWriter fw = new FileWriter(file);
//			bw = new BufferedWriter(fw);
//			bw.write("Lista de libros encontrada: \n");
//
//			for (int i = 0; i < listaLibros.size(); i++) {
//				String contenidoLinea1 = listaLibros.getNodo().getNombre();
//				bw.write(contenidoLinea1);
//				bw.newLine();
//			}
//			listaLibros.resetCursor();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		} finally {
//			try {
//				if (bw != null)
//					bw.close();
//			} catch (Exception ex) {
//				System.out.println("Error cerrando el BufferedWriter" + ex);
//			}
//		}
//	}
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
	public static ArrayList<Nodo> obtenerLosNMasBuscados(int n,String genero,GrafoDirigido profile){
		ArrayList<Nodo> auxList= new ArrayList<Nodo>();
		Nodo temp = profile.obtenerNodo(genero);
		ArrayList<Arista> auxArista= temp.getAristasAdyacentes();
		if(n>auxArista.size()) {
			for (int i = 0; i < auxArista.size(); i++) {
				auxList.add(auxArista.get(i).getNodoDestino());
			}
		}else {
			for (int i = 0; i < n; i++) {
				auxList.add(auxArista.get(i).getNodoDestino());			
				}
		}
		return auxList;
	}

}

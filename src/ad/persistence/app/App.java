package ad.persistence.app;

import java.util.Scanner;

import org.hibernate.service.spi.ServiceException;

import ad.persistence.domain.Artefactos;
import ad.persistence.domain.Elemento;
import ad.persistence.domain.Personaje;
import ad.persistence.service.ArtefactosMySQLService;
import ad.persistence.service.ElementoMySQLService;
import ad.persistence.service.PersonajeMySQLService;

public class App {
	public static void main(String[] args) {
		PersonajeMySQLService p = new PersonajeMySQLService();
		ArtefactosMySQLService a = new ArtefactosMySQLService();
		ElementoMySQLService e = new ElementoMySQLService();
		Scanner sc = new Scanner(System.in);
		String opcion = "";
		String nombrePersonaje, nivelPersonaje, nombreNuevoPersonaje;
		String nombreArtefacto, tipoArtefacto, nombreNuevoArtefacto, tipoNuevoArtefacto;
		String nombreElemento, nombreNuevoElemento;
		Personaje personaje = null;
		Elemento elemento = null;
		Artefactos artefacto = null;

		System.out.println(
				"Escribir el número de la opción que se quiera realizar. Para salir escriba Exit " + "\n Personajes:"
						+ "\n 1.Añadir Personaje \n 2.Borrar Personaje \n 3.Ver Personajes \n 4.Modificar Personaje "
						+ "\n Artefactos:"
						+ "\n 5.Añadir Artefacto \n 6.Borrar Artefacto \n 7.Ver Artefactos \n 8.Modificar Artefacto "
						+ "\n Elementos:"
						+ "\n 9.Añadir Elemento \n 10.Borrar Elemento \n 11.Ver Elementos \n 12.Modificar Elemento");

		do {
			try {
				opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					System.out.println("Escriba el nombre del personaje a añadir:\n");
					nombreNuevoPersonaje = sc.nextLine();
					System.out.println("Introduzca su nivel:\n");
					nivelPersonaje = sc.nextLine();
					personaje = new Personaje(nombreNuevoPersonaje, nivelPersonaje);
					p.anyadirPersonaje(personaje);
					System.out.println("Personaje añadido");
					break;
				case "2":
					System.out.println("Personajes:\n");
					p.verPersonajes();
					System.out.println("Escriba el nombre del personaje a borrar:\n");
					nombrePersonaje = sc.nextLine();
					if (p.comprobarExistencia(nombrePersonaje) == true) {
						p.borrarPersonajePorNombre(nombrePersonaje);
						System.out.println("Personaje borrado");
					} else {
						System.out.println("El personaje no existe, no se ha realizado ninguna acción");
					}
					break;
				case "3":
					p.verPersonajes();
					break;
				case "4":
					System.out.println("Personajes:\n");
					p.verPersonajes();
					System.out.println("Escriba el nombre del personaje a modificar:\n");
					nombrePersonaje = sc.nextLine();
					System.out.println("Escriba el nuevo nombre del personaje:\n");
					nombreNuevoPersonaje = sc.nextLine();
					System.out.println("Introduzca su nivel:\n");
					nivelPersonaje = sc.nextLine();
					p.modificarPersonaje(nombrePersonaje, nombreNuevoPersonaje, nivelPersonaje);
					System.out.println("Personaje modificado");
					break;
				case "5":
					System.out.println("Escriba el nombre del artefacto a añadir:\n");
					nombreNuevoArtefacto = sc.nextLine();
					System.out.println("Introduzca su tipo:\n");
					tipoNuevoArtefacto = sc.nextLine();
					artefacto = new Artefactos(nombreNuevoArtefacto, tipoNuevoArtefacto);
					a.anyadirArtefacto(artefacto);
					System.out.println("Artefacto añadido");
					break;
				case "6":
					System.out.println("Artefactos:\n");
					a.verArtefactos();
					System.out.println("Escriba el nombre del artefacto a borrar:\n");
					nombreArtefacto = sc.nextLine();
					System.out.println("Escriba el tipo del artefacto a borrar:\n");
					tipoArtefacto = sc.nextLine();
					if (a.comprobarExistencia(nombreArtefacto, tipoArtefacto) == true) {
						a.borrarArtefactoPorNombreYTipo(nombreArtefacto, tipoArtefacto);
						System.out.println("Artefacto borrado");
					} else {
						System.out.println("El artefacto no existe, no se ha realizado ninguna acción");
					}
					break;
				case "7":
					System.out.println("Artefactos:\n");
					a.verArtefactos();
					break;
				case "8":
					System.out.println("Artefactos:\n");
					a.verArtefactos();
					System.out.println("Escriba el nombre del artefacto a modificar:\n");
					nombreArtefacto = sc.nextLine();
					System.out.println("Escriba el tipo del artefacto a modificar:\n");
					tipoArtefacto = sc.nextLine();
					System.out.println("Escriba el nuevo nombre del artefacto:\n");
					nombreNuevoArtefacto = sc.nextLine();
					System.out.println("Escriba el nuevo tipo del artefacto:\n");
					tipoNuevoArtefacto = sc.nextLine();
					a.modificarArtefacto(nombreArtefacto, tipoArtefacto, nombreNuevoArtefacto, tipoNuevoArtefacto);
					System.out.println("Artefacto modificado");
					break;
				case "9":
					System.out.println("Escriba el nombre del elemento a añadir:\n");
					nombreNuevoElemento = sc.nextLine();
					elemento = new Elemento(nombreNuevoElemento);
					e.anyadirElemento(elemento);
					System.out.println("Elemento añadido");
					break;
				case "10":
					System.out.println("Elementos:\n");
					e.verElementos();
					System.out.println("Escriba el nombre del elemento a borrar:\n");
					nombreElemento = sc.nextLine();
					if (e.comprobarExistencia(nombreElemento) == true) {
						e.borrarElemento(nombreElemento);
						System.out.println("Elemento borrado");
					} else {
						System.out.println("El elemento no existe, no se ha realizado ninguna acción");
					}
					break;
				case "11":
					System.out.println("Elementos:\n");
					e.verElementos();
					break;
				case "12":
					System.out.println("Elementos:\n");
					e.verElementos();
					System.out.println("Escriba el nombre del elemento a modificar:\n");
					nombreElemento = sc.nextLine();
					System.out.println("Escriba el nuevo nombre del elemento:\n");
					nombreNuevoElemento = sc.nextLine();
					e.modificarElemento(nombreElemento, nombreNuevoElemento);
					System.out.println("Elemento modificado");
					break;
				}
			} catch (ServiceException se) {
				System.out.println(
						"Fallo al conectar con la base de datos, asegurese de que MySQL está encendido.\nError: "
								+ se.getMessage());
			}
		} while (!opcion.toLowerCase().equals("exit"));
		System.out.println();
	}
}

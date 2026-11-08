package biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class GestionBiblioteca {

    public static void main(String args[]){
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la biblioteca: ");
        String nombreBiblioteca = teclado.nextLine();

        Biblioteca biblioteca = new Biblioteca(nombreBiblioteca);// Crea una instancia de la biblioteca

        GestionBiblioteca.cargarDatosPrueba(biblioteca); // Carga datos de prueba para poblar la biblioteca con libros, socios y préstamos.

        int eleccion = -1; // Variable de control para las opciones del menú (switch case)
        
        do{
            GestionBiblioteca.mostrarOpcionesMenu(biblioteca); // Muestra un menú con las operaciones disponibles para el usuario

            System.out.print("\nIngrese una opción: ");
            eleccion = teclado.nextInt();
            teclado.nextLine(); // Limpia el salto de linea pendiente de la entrada anterior

            switch(eleccion){
                case 1:
                    GestionBiblioteca.contarEstudiantes(biblioteca);
                    break;
                
                case 2:
                    GestionBiblioteca.contarDocentes(biblioteca);
                    break;

                case 3:
                    GestionBiblioteca.listarDocentesResponsables(biblioteca);
                    break;

                case 4:
                    GestionBiblioteca.listarLibros(biblioteca);
                    break;
                
                case 5:
                    GestionBiblioteca.listarTodosLosSocios(biblioteca);
                    break;

                case 6:
                    System.out.print("\nIngrese el título del libro: ");
                    String tituloLibro = teclado.nextLine();

                    Libro libroSolicitado = biblioteca.buscarLibro(tituloLibro);

                    if(libroSolicitado == null){
                        System.out.printf("\nEl libro con título %s no existe en la biblioteca.\n", tituloLibro);
                    }else{
                        try{
                            String poseedorDelLibro = biblioteca.quienTieneElLibro(libroSolicitado);
                            System.out.printf("\nEl libro %s está en préstamo por %s.\n", tituloLibro, poseedorDelLibro);
                        }
                        catch(LibroNoPrestadoException e){
                            System.out.println("\nEl libro " +tituloLibro +" se encuentra en la biblioteca.");
                        }
                    }

                    break;
                case 0:
                    System.out.println("\n\nGracias por utilizar el sistema de gestión de la bilioteca " +biblioteca.getNombre() +". ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    break;
            }

        }while(eleccion != 0);

        System.out.println("\nAutores: ");
        System.out.println("\t - Barrientos, Valentina");
        System.out.println("\t - De Weert, Lautaro"); 
        System.out.println("\t - Riquelme Griffith, Juliana Maillen ");
        System.out.println("\t - Sandoval, Diego Ulises"); 
        System.out.println("\t - Scher, Emily Giuliana");
        System.out.println("\t - Urbina, Facundo\n");
        
        teclado.close();
    }

    private static void mostrarOpcionesMenu(Biblioteca p_biblioteca){
        System.out.println("\n****************************************************************************");
        System.out.println("\tGestión de la bilioteca: " +p_biblioteca.getNombre());
        System.out.println("****************************************************************************");

        System.out.println("1 - Contar socios estudiantes");
        System.out.println("2 - Contar socios docentes");
        System.out.println("3 - Lista de docentes responsables (nunca han adeudado ni adeudan libros)");
        System.out.println("4 - Lista de libros de la biblioteca");
        System.out.println("5 - Lista de todos los socios de la biblioteca");
        System.out.println("6 - ¿Quién tiene este libro en préstamo?");


        System.out.println("\n0- Salir del sistema");
        System.out.println("***************************************************************************");
    }

    private static void cargarDatosPrueba(Biblioteca p_biblioteca){
        // Instanciamos algunos libros de prueba
        Libro libro1 = new Libro("Programando con JAVA", 1, "O'Reilly", 2026 );
        Libro libro2 = new Libro("Sistemas Operativos", 2, "Pearson Educación", 2025);
        Libro libro3 = new Libro("Base de datos", 3, "Ediciones Paraninfo", 2024);
        Libro libro4 = new Libro("Programación Orientada a Objetos con C++", 5, "Alfaomega Grupo Editor Argentino", 2018);

        // Agregamos los libros a la biblioteca
        p_biblioteca.agregarLibro(libro1);
        p_biblioteca.agregarLibro(libro2);
        p_biblioteca.agregarLibro(libro3);
        p_biblioteca.agregarLibro(libro4);

         // Instanciamos y agregramos a la biblioteca algunos socios de prueba
        p_biblioteca.nuevoSocioEstudiante(48131588, "Sandoval, Diego Ulises", "Licenciatura en Sistemas de Información");
        p_biblioteca.nuevoSocioEstudiante(45942750, "Urbina, Facundo", "Ingeniería Eléctrica");

        p_biblioteca.nuevoSocioDocente(12345678, "Ringa, Monica", "Paradigmas y Lenguajes de Programación");
        p_biblioteca.nuevoSocioDocente(98765432, "Gomez, Juan Carlos", "Base de datos 1");

        // Asignamos algunos prestamos de prueba
        p_biblioteca.prestarLibro(new GregorianCalendar(2026, Calendar.NOVEMBER, 10), p_biblioteca.buscarSocio(48131588), libro1);
        p_biblioteca.prestarLibro(new GregorianCalendar(2026, Calendar.NOVEMBER, 11), p_biblioteca.buscarSocio(12345678), libro4);
        p_biblioteca.prestarLibro(new GregorianCalendar(2026, Calendar.NOVEMBER, 9), p_biblioteca.buscarSocio(98765432), libro3);
    }

    private static void contarEstudiantes(Biblioteca p_biblioteca){
        int cantidadEstudiantes = p_biblioteca.cantidadDeSociosPorTipo("Estudiante");

        if(cantidadEstudiantes == 0){
            System.out.println("\nNingún socio de la biblioteca es un Estudiante.");
        }else{
            System.out.println("\nLa cantidad de socios de la biblioteca que son Estudiantes es: " +cantidadEstudiantes);
        }
    }

    private static void contarDocentes(Biblioteca p_biblioteca){
        int cantidadDocentes = p_biblioteca.cantidadDeSociosPorTipo("Docente");

        if(cantidadDocentes == 0){
            System.out.println("\nNingún socio de la biblioteca es un Docente.");
        }else{
            System.out.println("\nLa cantidad de socios de la biblioteca que son Docentes es: " +cantidadDocentes);
        }
    }

    private static void listarDocentesResponsables(Biblioteca p_biblioteca){
        ArrayList<Docente> docentesResponsables = p_biblioteca.docentesResponsables();

        if(docentesResponsables == null || docentesResponsables.isEmpty()){
            System.out.println("\nNo hay ningún docente responsable en la biblioteca.");
        }else{
            System.out.println("\nLista de docentes responsables (nunca han adeudado ni adeudan libros):");
            for(Docente docente : docentesResponsables){
                System.out.println(docente.toString());
            }
        }
    }

    private static void listarLibros(Biblioteca p_biblioteca){
        ArrayList<Libro> libros = p_biblioteca.getLibros();

        if(libros == null || libros.isEmpty()){
            System.out.println("\nNo hay libros en la biblioteca.");
        }else{
            System.out.println(p_biblioteca.listaDeLibros());
        }
    }

    private static void listarTodosLosSocios(Biblioteca p_biblioteca){
        ArrayList<Socio> socios = p_biblioteca.getSocios();

        if(socios == null || socios.isEmpty()){
            System.out.println("\nNo hay socios en la biblioteca.");
        }else{
            System.out.println(p_biblioteca.listaDeSocios());
        }
    }
}
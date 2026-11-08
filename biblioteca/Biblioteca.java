package biblioteca;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Write a description of class Biblioteca here.
 * 
 * @author Riquelme Griffith, Juliana - Scher, Emily Giuliana 
 * @version 0.2
 */
public class Biblioteca
{
    private String nombre;
    private ArrayList<Socio> socios;
    private ArrayList<Libro> libros;
    
    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setSocios(new ArrayList<Socio>());
        this.setLibros(new ArrayList<Libro>());
    }
    
    public Biblioteca(String p_nombre, ArrayList<Socio> p_socios, ArrayList<Libro> p_libros) {
    this.setNombre(p_nombre);
    this.setSocios(new ArrayList<Socio>(p_socios));
    this.setLibros(new ArrayList<Libro>(p_libros));
    } 
    //Setters
    public void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    public void setSocios(ArrayList<Socio> p_socios) {
        this.socios = p_socios;
    }

    public void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

    //Getters

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList<Socio> getSocios() {
        return this.socios;
    }

    public ArrayList<Libro> getLibros() {
        return this.libros;
    }
     
    
    public boolean agregarSocio(Socio p_socios) {
    return this.getSocios().add(p_socios);
    }

    public boolean agregarLibro(Libro p_libros) {
    return this.getLibros().add(p_libros);
    }

    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
       Libro libro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
       this.agregarLibro(libro);
    }

    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
       Estudiante estudiante = new Estudiante(p_dniSocio, p_nombre, p_carrera);
       this.agregarSocio(estudiante);
    }

    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
       Docente docente = new Docente(p_dniSocio, p_nombre, p_area);
       this.agregarSocio(docente);
    }

    public Socio buscarSocio(int p_dni){
        for (Socio s : this.getSocios()){
            if(p_dni == s.getDniSocio()){
                return s; 
            }
        }
        return null;
    }

    public Libro buscarLibro(String p_titulo){

        for(Libro unLibro : this.getLibros()){

            if(unLibro.getTitulo().equalsIgnoreCase(p_titulo)){
                return unLibro;
            }

        }
        return null;
    }
    
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        if(!p_socio.puedePedir() || p_libro.prestado()){
            return false;
        }else{
            Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);
            p_libro.agregarPrestamo(prestamo);
            p_socio.agregarPrestamo(prestamo);
            return true;
        }
    }
    
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException{
        if(!p_libro.prestado()){
            throw new LibroNoPrestadoException("El libro " + p_libro.getTitulo() + " no se puede devolver ya que se encuentra en la biblioteca");
        }
        p_libro.ultimoPrestamo().registrarFechaDevolucion(Calendar.getInstance());
    }
    
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException{
        if(!p_libro.prestado()){
            throw new LibroNoPrestadoException("El libro se encuentra en la biblioteca.");
        }else{
            return p_libro.ultimoPrestamo().getSocio().getNombre();
        }
    }
    
    public int cantidadDeSociosPorTipo(String p_objeto){
        int cantidad = 0;
        for(Socio unSocio : this.getSocios()){
            if(unSocio.soyDeLaClase().equalsIgnoreCase(p_objeto)){
                cantidad++;
            }
        }
        return cantidad;
    }
    
    public ArrayList<Prestamo> prestamosVencidos(){
        ArrayList<Prestamo> prestamosVencidos = new ArrayList<Prestamo>();
        Calendar fechaHoy = Calendar.getInstance();
        
        for(Libro unLibro : this.getLibros()){
            if(unLibro.prestado()){
                Prestamo ultimo = unLibro.ultimoPrestamo();
                if(ultimo.vencido(fechaHoy)){
                    prestamosVencidos.add(ultimo);
                }
            }
        }
        return prestamosVencidos;
    }

    // reportes e informes

    // Devuelve los docentes que nunca tuvieron ni tienen un prestamo vencido
    public ArrayList<Docente> docentesResponsables(){
        ArrayList<Docente> responsables = new ArrayList<Docente>();
        for(Socio unSocio : this.getSocios()){
            // solo los Docente tienen esResponsable(), por eso se pregunta el tipo
            if(unSocio instanceof Docente){
                Docente unDocente = (Docente) unSocio;
                if(unDocente.esResponsable()){
                    responsables.add(unDocente);
                }
            }
        }
        return responsables;
    }

    // Formato1: lista de socios numerada y al final la cantidad de cada tipo
    public String listaDeSocios(){
        String lista = "Lista de Socios:\n\n";
        int numero = 1;
        for(Socio unSocio : this.getSocios()){
            lista += numero + ") " + unSocio.toString() + "\n";
            numero++;
        }
        lista += "**************************************\n";
        lista += "Cantidad de Socios del tipo Estudiante: " + this.cantidadDeSociosPorTipo("Estudiante") + "\n";
        lista += "Cantidad de Socios del tipo Docente: " + this.cantidadDeSociosPorTipo("Docente") + "\n";
        lista += "**************************************";
        return lista;
    }

    // Formato2: lista de libros numerada, indicando si estan prestados (Si/No)
    public String listaDeLibros(){
        String lista = "Lista de Libros:\n\n";
        int numero = 1;
        for(Libro unLibro : this.getLibros()){
            String prestado = "No";
            if(unLibro.prestado()){
                prestado = "Si";
            }
            lista += numero + ") " + unLibro.toString() + " || Prestado: (" + prestado + ")\n";
            numero++;
        }
        return lista;
    }

    // Formato3: un docente responsable por linea
    public String listaDeDocentesResponsables(){
        String lista = "Lista de Docentes Responsables:\n\n";
        for(Docente unDocente : this.docentesResponsables()){
            lista += "* " + unDocente.toString() + "\n";
        }
        return lista;
    }

    // Titulos de la biblioteca sin repetir (si hay 2 ejemplares del mismo libro, aparece una vez)
    public String listaDeTitulos(){
        ArrayList<String> titulos = new ArrayList<String>();
        for(Libro unLibro : this.getLibros()){
            if(!titulos.contains(unLibro.getTitulo())){
                titulos.add(unLibro.getTitulo());
            }
        }
        String lista = "Lista de Titulos:\n\n";
        int numero = 1;
        for(String unTitulo : titulos){
            lista += numero + ") " + unTitulo + "\n";
            numero++;
        }
        return lista;
    }

}

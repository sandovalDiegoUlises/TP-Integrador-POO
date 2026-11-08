package biblioteca;

import java.util.ArrayList;

/**
 * Write a description of class Libro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Libro
{
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;
    
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio){
        
        this.setTitulo(p_titulo); 
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList<Prestamo>());
        
    }
    
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos){
        
        this.setTitulo(p_titulo); 
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(p_prestamos);
        
    }
    //SETTERS
    
    private void setTitulo(String p_titulo){
        this.titulo = p_titulo;
    }
    
    private void setEdicion(int p_edicion){
        this.edicion = p_edicion;
    }
    
    private void setEditorial(String p_editorial){
        this.editorial = p_editorial;
    }
    
    private void setAnio(int p_anio){
        this.anio = p_anio;
    }
    
    private void setPrestamos(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }
    
    //GETTERS
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public int getEdicion(){
        return this.edicion;
    }
    
    public String getEditorial(){
        return this.editorial;
    }
    
    public int getAnio(){
        return this.anio;
    }
    
    public ArrayList<Prestamo> getPrestamos() {
        return this.prestamos;
    }
    
    //metodos para agregar y quitar prestamos(obligatorios aunque no se muestre en el UML)
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    public boolean quitarPrestamo(Prestamo p_prestamo){
        if(this.getPrestamos().isEmpty()){
            return false;
        }else{
            return this.getPrestamos().remove(p_prestamo);
        }
    }
    
    public Prestamo ultimoPrestamo(){
        // pregunta si la coleccion de prestamos esta vacia y si no lo esta retorna el ultimo prestamo de esta. si esta vacia retorna nada
        if(this.getPrestamos() != null && !this.getPrestamos().isEmpty()){
           return this.getPrestamos().get(this.getPrestamos().size()-1);
        }else{
            return null;
        }
    }
    
    public boolean prestado(){
       Prestamo ultPrestamo = this.ultimoPrestamo();
       //pregunta si la fecha de devolucion esta vacia, si lo esta significa que el libro esta prestado y si no esta vacia el libro sigue en la biblioteca
       if(ultPrestamo != null){
           return ultPrestamo.getFechaDevolucion() == null;
       }else{
           return false;
       }
    }
    
    public String toString(){
        return "Titulo: " + this.getTitulo();
    }
}
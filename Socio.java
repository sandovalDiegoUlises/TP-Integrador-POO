import java.util.ArrayList;
import java.util.Calendar;

/**
 * Write a description of class Socio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Socio
{
    // instance variables - replace the example below with your own
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;

    /**
     * Constructor for objects of class Socio
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo)
    {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
    }
    
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos)
    {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }
    
    // --- SETTERS ---

    public void setDniSocio(int p_dniSocio) {
        this.dniSocio = p_dniSocio;
    }

    public void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    public void setDiasPrestamo(int p_diasPrestamo) {
        this.diasPrestamo = p_diasPrestamo;
    }

    public void setPrestamos(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }

    // --- GETTERS ---

    public int getDniSocio() {
        return this.dniSocio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getDiasPrestamo() {
        return this.diasPrestamo;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return this.prestamos;
    }
    
    public boolean agregarPrestamo(Prestamo p_prestamo) {
    return this.getPrestamos().add(p_prestamo);
    }
    
    public boolean quitarPrestamo(Prestamo p_prestamo) {
        return this.getPrestamos().remove(p_prestamo);
    }
    
    public int cantLibrosPrestados() {
        return this.getPrestamos().size();
    }
    
    public String toString(){
        return this.getDniSocio() + " || " + this.getNombre() + " || " +  "Libros prestados: " + this.cantLibrosPrestados();
    }
    
    public boolean puedePedir() {
    Calendar fechaHoy = Calendar.getInstance();
    
    for(Prestamo p : this.getPrestamos()){
        if (p.vencido(fechaHoy)) {
            return false;
        }
    }
    return true; 
    }
    
    public abstract String soyDeLaClase();
}
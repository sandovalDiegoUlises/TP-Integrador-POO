package biblioteca;

import java.util.Calendar;

/**
 * Write a description of class Prestamo here.
 * 
 * @author 
 * @version 0.2
 */
public class Prestamo
{
    // instance variables - replace the example below with your own
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;

    // correccion del constructor
    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio); 
        this.setLibro(p_libro);
        this.setFechaDevolucion(null); // inicialmnte no tiene fecha de devolución
    }

    // Setters
    public void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    public void setFechaDevolucion(Calendar p_fechaDevolucion) {
        this.fechaDevolucion = p_fechaDevolucion;
    }

    public void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }
    
    public void setSocio(Socio p_socio){
        this.socio = p_socio;
    }

    // Getters
    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public Libro getLibro() {
        return this.libro;
    }
    
    public Socio getSocio(){
        return this.socio;
    }
    
    //metodos
     public void registrarFechaDevolucion(Calendar p_fecha) {
        this.setFechaDevolucion(p_fecha);
    }
    
    public boolean vencido(Calendar p_fecha) {
        Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();
        fechaVencimiento.add(Calendar.DAY_OF_MONTH, this.getSocio().getDiasPrestamo());
        return p_fecha.after(fechaVencimiento);
    }
    
    public String toString(){
        int anioRetiro = this.getFechaRetiro().get(Calendar.YEAR);
        int mesRetiro = this.getFechaRetiro().get(Calendar.MONTH) + 1;
        int diaRetiro = this.getFechaRetiro().get(Calendar.DAY_OF_MONTH);

        String retiroStr = String.format("%04d/%02d/%02d", anioRetiro, mesRetiro, diaRetiro);
        String devolucionStr = "Pendiente";

        if (this.getFechaDevolucion() != null) {
            int anioDev = this.getFechaDevolucion().get(Calendar.YEAR);
            int mesDev = this.getFechaDevolucion().get(Calendar.MONTH) + 1;
            int diaDev = this.getFechaDevolucion().get(Calendar.DAY_OF_MONTH);
            devolucionStr = String.format("%04d/%02d/%02d", anioDev, mesDev, diaDev);
        }

        return "Retiro: " + retiroStr + " - Devolución: " + devolucionStr + "\n" +
               "Libro: " + this.libro.getTitulo() + "\n" +
               "Socio: " + this.socio.getNombre();
    }
}

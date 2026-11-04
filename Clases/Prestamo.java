import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Prestamo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prestamo
{
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;

    public Prestamo(Calendar p_fechaRetiro, Calendar p_fechaDevolucion, Libro p_libro)
    {
        this.setFechaRetiro(p_fechaRetiro);
        this.setFechaDevolucion(p_fechaDevolucion);
        this.setLibro(p_libro);
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
    
     public void registrarFechaDevolucion(Calendar p_fecha) {
        this.setFechaDevolucion(p_fecha);
    }
    
    public boolean vencido(Calendar p_fecha) {
        return p_fecha.after(this.fechaDevolucion);
    }
    
    public String toString() {
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

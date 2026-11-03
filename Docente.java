import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Docente extends Socio{
    private String area;

    Docente(int p_dniSocio, String p_nombre, String p_area){
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    } 

    Docente(int p_dniSocio, String p_nombre, ArrayList<Prestamo> p_prestamos, String p_area){
        super(p_dniSocio, p_nombre, 5, p_prestamos);
        this.setArea(p_area);
    }

    private void setArea(String p_area){
        this.area = p_area;
    }

    public String getArea(){
        return this.area;
    }

    public boolean esResponsable(){
        for(Prestamo unPrestamo : this.getPrestamos()){
            if (unPrestamo.vencido(new GregorianCalendar())){
                return false;
            }
        }
        return true; 
    }

    public void cambiarDiasDePrestamo(int p_dias){
        if(this.esResponsable()){
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
        }
    }

    @Override
    public boolean puedePedir() {
        Calendar fechaHoy = Calendar.getInstance();
        
        for(Prestamo unPrestamo : this.getPrestamos()) {
            
            if (unPrestamo.getFechaDevolucion() == null) {
                if (unPrestamo.vencido(fechaHoy)) {
                    return false;
                }
            }
        }
        return true; 
    }
    
    public String soyDeLaClase(){
        return "Docente";
    }
}
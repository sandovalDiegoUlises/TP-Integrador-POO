import java.util.Calendar;

public class Estudiante extends Socio{
    private String carrera;

    Estudiante(int p_dni, String p_nombre, String p_carrera){
        super(p_dni, p_nombre, 20);
        this.setCarrera(p_carrera);
    }

    private void setCarrera(String p_carrera){
        this.carrera = p_carrera;
    }

    public String getCarrera(){
        return this.carrera;
    }

    public String soyDeLaClase(){
        return "Estudiante";
    }

    @Override 
    public boolean puedePedir(){
        if(this.cantLibrosPrestados() > 3) return false;

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
}
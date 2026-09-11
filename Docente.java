/**
 * Clase que representa a un docente, que es un tipo de socio.
 * Los docentes tienen un área de especialización y un préstamo fijo de 5 días.
 */
import java.util.*;

public class Docente extends Socio
{
    private String area;

    /**
     * Constructor de la clase para cardinalidad 0
     * @param p_dniSocio
     * @param p_nombre
     * @param p_area
     */
    public Docente(int p_dniSocio, String p_nombre, String p_area)
    {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }

    /**
     * Constructor de la clase para cardinalidad 1...n
     * @param p_dniSocio
     * @param p_nombre
     * @param p_prestamos
     * @param p_area
     */
    public Docente(int p_dniSocio, String p_nombre, ArrayList <Prestamo> p_prestamos, String p_area)
    {
        super(p_dniSocio, p_nombre, 5, p_prestamos);
        this.setArea(p_area);
    }

    private void setArea(String p_area) {
        this.area = p_area;
    }

    public String getArea() {
        return this.area;
    }

    /**
     * Método que devuelve la clase del socio
     * @param no recibe parametros
     * @return retorna un String con el nombre de la clase
     */
    public String soyDeLaClase() {
        return "Docente";
    }

    /**
     * Método que determina si el docente es responsable, en base a si
     * tuvo o tiene algún préstamo vencido.
     * @param no recibe parametros
     * @return
     */
    public boolean esResponsable(){
        boolean vencido = false;
        for(Prestamo p : this.getPrestamos()){
            vencido = vencido || (p.vencido(Calendar.getInstance()));
            //vencido || ... significa: si 'vencido' ya es 'true', se queda 'true'
            //Si es false, comprueba si el préstamo 'p' estuvo vencido
        }
        
        // Es responsable si cumple 2 condiciones:
        // 1 - this.puedePedir(): No tiene préstamos vencidos en este momento        
        // 2 - !vencido: nunca tuvo un préstamo vencido en su historial
        return this.puedePedir() && (!vencido);
    }

    /**
     * Método que permite cambiar los días de préstamo si el docente es responsable
     * @param p_dias
     * @return no retorna nada
     */
    public void cambiarDiasDePrestamos(int p_dias) {
        if(this.esResponsable()){
            this.setDiasPrestamo(p_dias);
        }
    }
}   
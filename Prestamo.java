/**
 * Clase que representa un préstamo de un libro a un socio en una biblioteca.
 */
import java.util.*;
import java.text.SimpleDateFormat;

public class Prestamo
{
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;


    /**
     * Constructor de la clase Prestamo
     * @param p_fechaRetiro
     * @param p_socio
     * @param p_libro
     */
    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
    }
    
    private void setFechaRetiro(Calendar p_fechaRetiro){
        this.fechaRetiro = p_fechaRetiro;
    }
    
    private void setFechaDevolucion(Calendar p_fechaDevolucion){
        this.fechaDevolucion = p_fechaDevolucion;
    }
    
    private void setSocio(Socio p_socio){
        this.socio = p_socio;
    }
    
    private void setLibro(Libro p_libro){
        this.libro = p_libro;
    }
    
    public Calendar getFechaRetiro(){
        return this.fechaRetiro;
    }
    
    public Calendar getFechaDevolucion(){
        return this.fechaDevolucion;
    }
    
    public Socio getSocio(){
        return this.socio;
    }
    
    public Libro getLibro(){
        return this.libro;
    }
    
    /**
      * Método que registra la fecha de devolución del libro, comprobando que la fecha ingresada
      * sea posterior o igual a la fecha de retiro.
     */
    public void registrarFechaDevolucion(Calendar p_fecha){
        if(p_fecha.after(this.getFechaRetiro()) || p_fecha.equals(this.getFechaRetiro())){ //se comprueba que la fecha ingresada sea posterior o igual a la fecha de retiro
            this.setFechaDevolucion(p_fecha);//si es valida, la asigna
        }else{//si no es valida...
            System.out.println("\nLa fecha de devolución no puede ser inferior a la fecha de retiro");
        }
    }

    /**
      * Método que comprueba si un préstamo esta vencido, primero crea una fecha de vencimiento que será
      * la suma de la fecha de retiro con los días de préstamos asignados al socio, luego comprueba si la
      * fecha ingresada es posterior a la fecha de vencimiento o no.
     */
    public boolean vencido(Calendar p_fecha) {
        if(p_fecha == null){//verifica que la fecha no sea nula
            return false;
        }
        Calendar fechaLimite = (Calendar) this.getFechaRetiro().clone();//clona y guarda la fecha de retiro en fechaLimite
        fechaLimite.add(Calendar.DAY_OF_YEAR, this.getSocio().getDiasPrestamo());//agrega los dias de prestamo a la fecha limite
        return p_fecha.after(fechaLimite);//define si se paso o no la fecha de vencimiento
    }
    

    /**
     * Método que devuelve una representación en String del préstamo
     * @param no recibe parametros
     * @return retorna una cadena con los datos del préstamo
     */
    public String toString(){
        Date fechaRetiro = this.getFechaRetiro().getTime();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDevolucionStr = "No se ha devuelto";
        if (this.getFechaDevolucion() != null) {//comprueba si la fecha de devolución NO es nula (o sea, si se devolvió)
            Date fechaDevolucion = this.getFechaDevolucion().getTime();
            fechaDevolucionStr = formato.format(fechaDevolucion);//formatea a texto
        }
        return "Retiro: "+formato.format(fechaRetiro)+" - Devolución: "+fechaDevolucionStr+
            "\\nLibro: "+this.getLibro().getTitulo()+//titulo del libro asociado
            "\\nSocio: "+this.getSocio().getNombre();//nombre del socio asociado
    }
}
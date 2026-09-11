/**
 * Clase abstracta Socio
 */
import java.util.*;

public abstract class Socio
{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList <Prestamo> prestamos;


    /**
     * Constructor de la clase para cardinalidad 0
     * @param p_dniSocio
     * @param p_nombre
     * @param p_area
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo)
    {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList <> ());
    }
    /**
     * Constructor de la clase para cardinalidad 1...n
     * @param p_dniSocio
     * @param p_nombre
     * @param p_area
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList <Prestamo> p_prestamos){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }
    
    private void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }

    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }

    protected void setDiasPrestamo(int p_diasPrestamo){
        this.diasPrestamo = p_diasPrestamo;
    }

    private void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    
    public int getDniSocio(){
        return this.dniSocio;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getDiasPrestamo(){
        return this.diasPrestamo;
    }

    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    
    /**
     * Agrega un prestamo a la lista de prestamos
     * @param p_prestamo
     * @return no retorna nada
     */
    public void agregarPrestamo(Prestamo p_prestamo){
        this.getPrestamos().add(p_prestamo);
    }
    
    /**
     * Quita un prestamo de la lista de prestamos
     * @param p_prestamo
     * @return no retorna nada
     */
    public void quitarPrestamo(Prestamo p_prestamo){
        this.getPrestamos().remove(p_prestamo);
    }
    
    /**
     * Devuelve el préstamo buscado
     * @param p_prestamo
     * @return el préstamo buscado, o null si no existe.
     */
    public Prestamo buscarPrestamo(Prestamo p_prestamo) {
        int i = this.getPrestamos().indexOf(p_prestamo);//Busca el préstamo y devuelve su índice (posición)
        //si no lo encuentra devuelve -1
        if (i < 0){//si el indice es negativo
            return null;
        } else{//si no es negativo
            return this.getPrestamos().get(i);//devuelve el prestamo que esta en esa posicion
        }
    }
    
    /**
     * Cantidad de libros prestados por el socio
     * @param no recibe parametros
     * @return retorna la cantidad de libros prestados
     */
    public int cantLibrosPrestados(){
        int libros = 0;
        for(Prestamo unPrestamo: this.getPrestamos()){
            if(unPrestamo.getFechaDevolucion() == null){//fecha nula = libro no devuelto
                libros++;
            }
        }
        return libros;
    }
    
    /**
    * Método toString de la clase Socio
    * @param no recibe parametros
    * @return retorna un String con los datos del socio
    */
    public String toString (){
        return "D.N.I.: "+this.getDniSocio()+" || "+this.getNombre()+" ("+this.soyDeLaClase()+") "
        +" || "+"Libros Prestados: "+this.cantLibrosPrestados();
    }
    
    /**
     * Método que comprueba si el socio puede pedir un nuevo préstamo en base a
     * si tiene préstamos vencidos actualmente.
     * @param no recibe parametros
     * @return retorna true si puede pedir, false en caso contrario
     */
    public boolean puedePedir(){
        Calendar hoy = Calendar.getInstance();
        boolean puedePedir = true;
        for (Prestamo unPrestamo : this.getPrestamos()){
            if (unPrestamo.getFechaDevolucion() == null) {
                puedePedir = (puedePedir && (!unPrestamo.vencido(hoy)));
            }
        }
        return puedePedir;
    }
    
    /**
     * Método abstracto que determina comportamiento de las subclases
     * @param no recibe parametros
     * @return no retorna nada
     */
    public abstract String soyDeLaClase();
}
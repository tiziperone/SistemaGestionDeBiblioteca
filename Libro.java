/**
 * Clase Libro que epresenta un libro en una biblioteca
 * 
 */
import java.util.*;

public class Libro
{
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;
    
    /**
     * Constructor de la clase Libro para cardinalidad 0
     * @param p_titulo
     * @param p_edicion
     * @param p_editorial
     * @param p_anio
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList<Prestamo>());
    }
    
    /**
     * Constructor de la clase Libro para cardinalidad 1...n
     * @param p_titulo
     * @param p_edicion
     * @param p_editorial
     * @param p_anio
     * @param p_prestamos
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(p_prestamos);
    }
    
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
    
    private void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    
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
    
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    
    /**
     * Agrega un préstamo a la lista
     * @param p_prestamo
     * @return retorna true si se agrego correctamente, false en caso contrario
     */
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    
    /**
     * Quita un préstamo de la lista
     * @param p_prestamo
     * @return retorna true si se quito correctamente, false en caso contrario
     */
    public boolean quitarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().remove(p_prestamo);
    }
    
    /**
     * Método que comprueba si el libro está prestado o no
     * @param no recibe parametros
     * @return retorna true si el libro está prestado, false en caso contrario
     */
    public boolean prestado(){
        Prestamo ultimoPrestamo = this.ultimoPrestamo();//ultimo prestamo registrado para este libro
        if(ultimoPrestamo != null){//si existe un ultimo prestamo, el libro fue prestado alguna vez
            return ultimoPrestamo.getFechaDevolucion() == null;
        }else{//si es null, nunca se prestó
            return false;
        }
    }
    
    /**
     * Método que devuelve el último préstamo realizado del libro
     * @param no recibe parametros
     * @return retorna el último préstamo realizado del libro o null si no tiene préstamos
     */
    public Prestamo ultimoPrestamo(){
        if(!this.getPrestamos().isEmpty()){//verifica si la lista de prestamos no esta en cero
            int longitud = this.getPrestamos().size();//obtiene la cantidad de prestamos
            return this.getPrestamos().get(longitud - 1);//resta por posicion 0 (si hay 5, es 0 1 2 3 4, (5-1 = 4)
        } else {//Si esta vacia...
            return null;
        }
    }
    
    /**
     * Método toString que devuelve una representación en String del libro
     * @param no recibe parametros
     * @return retorna una cadena con el título del libro   
     */
    public String toString(){
        return "Titulo: " + this.getTitulo();
    }   
}
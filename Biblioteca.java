/**
* Clase Biblioteca que lleva el manejo de los libros y socios (estudiantes y/o docentes).
*/

import java.util.*;

public class Biblioteca{
    private String nombre;
    private ArrayList <Libro> libros;
    private ArrayList <Socio> socios;

    /**
      * Constructor correspondiente de la clase para la cardinalidad 0
      * @param p_nombre
    */
    public Biblioteca(String p_nombre){
        this.setNombre(p_nombre);
        this.setArrayLibros(new ArrayList <Libro> ());
        this.setArraySocios(new ArrayList <Socio> ());
    }
    /**
      * Constructor para la cardinalidad n
      * @param p_nombre nombre de la biblioteca
      * @param p_libros coleccion de libros
      * @param p_socios colecion de socios
     */
    public Biblioteca(String p_nombre, ArrayList <Libro> p_libros, ArrayList <Socio> p_socios){
        this.setNombre(p_nombre);
        this.setArrayLibros(p_libros);
        this.setArraySocios(p_socios);
    }
    
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    
    private void setArrayLibros(ArrayList <Libro> p_libros){
        this.libros = p_libros;
    }
    
    private void setArraySocios(ArrayList <Socio> p_socios){
        this.socios = p_socios;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public ArrayList <Libro> getArrayLibros(){
        return this.libros;
    }
    
    public ArrayList <Socio> getArraySocios(){
        return this.socios;
    }
    
    /**
      * Agrega un nuevo socio a la coleccion de socios de la biblioteca
      * @param p_socio el objeto socio a ser añadido
     */
    public void agregarSocio(Socio p_socio){
        this.getArraySocios().add(p_socio);
    }
    
    /**
      * elimina un socio especifico de la coleccion de socios de la biblioteca
      * @param p_socio el objeto socio a ser eliminado de la lista
     */
    public void quitarSocio(Socio p_socio){
        this.getArraySocios().remove(p_socio);
    }
    
    /**
      * se agrega un nuevo libro a la coleccion de libros disponibles en la biblioteca
      * @param p_libros el objeto libro a ser añadido al inventario
     */
    public void agregarLibro(Libro p_libro){
        this.getArrayLibros().add(p_libro);
    }
    
    /**
      * elimina un libro especifico del inventario(coleccion de libros) de la biblioteca
      * @param p_libro el objeto libro a ser quitado de la coleccion
     */
    public void quitarLibro(Libro p_libro){
        this.getArrayLibros().remove(p_libro);
    }
    
    /**
      * crea una nueva instancia de la clase libro y la añade al inventario de la biblioteca
      * @param p_titulo el título del nuevo libro
      * @param p_edicion el número de edición del nuevo libro
      * @param p_editorial la editorial del nuevo libro
      * @param p_anio el año de publicación del nuevo libro
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio){
        Libro libro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
        this.getArrayLibros().add(libro);
    }
    
    /**
      * se crea una nueva instancia de la clase docente(hereda de socio) y la añade a la coleccion
      * de socios de la biblioteca
      * @param p_dniSocio el DNI o número de identificación del nuevo socio docente
      * @param p_nombre el nombre completo del nuevo socio docente
      * @param p_area el área o departamento al que pertenece el docente
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area){
        Socio docente = new Docente(p_dniSocio, p_nombre, p_area);
        this.getArraySocios().add(docente);
    }
    
    /**
      * se crea una nueva instancia de la clase estudiante(hereda de socio) y la añade a la coleccion
      * de socios de la biblioteca
      * @param p_dniSocio el DNI o número de identificación del nuevo socio estudiante
      * @param p_nombre el nombre completo del nuevo socio estudiante
      * @param p_area el área o departamento al que pertenece el estudiante
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera){
        Socio estudiante = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        this.getArraySocios().add(estudiante);
    }
    
    /**
      * Se registra un nuevo prestamo de un libro a un socio
      * @param p_fechaRetiro
      * @param p_socio
      * @param p_libro
      * @return devuelve true si se pudo realizar el prestamo, false en caso contrario
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        if(p_libro.prestado() || !p_socio.puedePedir()){//verificar si el libro no esta dispo. o si el socio no  puede pedir
            return false;//si alguna condicion se cumple, retorna false
        } else {
            Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);//crea un prestamo y luego lo asocia al libro y al socio
            p_libro.agregarPrestamo(prestamo);
            p_socio.agregarPrestamo(prestamo);
            return true;
        }
    }

    /**
      * Se registra la devolucion de un libro
      * o lanza una excepcion en caso de que el libro no este prestado
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException{
        if(p_libro.prestado()){//verifica si el libro esta prestado
            Prestamo prestamo = p_libro.ultimoPrestamo();//busca el ultimo prestamo que tiene el libro
            Socio socio = prestamo.getSocio();//busca el socio que tiene el libro
            prestamo.registrarFechaDevolucion(Calendar.getInstance());//registra la fecha de devolucion en el prestamo del libro
            socio.buscarPrestamo(prestamo).registrarFechaDevolucion(Calendar.getInstance());//registra la fecha de devolucion en el prestamo del socio
        }else{//si no esta prestado...
            throw new LibroNoPrestadoException("El libro no esta prestado.");//lanza la excepcion
        }
    }
    
    /**
      * Se calcula la cantidad de socios segun el
      * tipo pasado por parametro
     */
    public int cantidadDeSociosPorTipo(String p_objeto){
        int contador = 0;//se inicializa un contador para la cantidad de socios
        for(Socio s : this.getArraySocios()){//para recorrer/iterar sobre el array de socios
            if(s.soyDeLaClase().equals(p_objeto)){//con el metodo comprueba si el tipo de socio coincide con el del parametro 
                contador += 1;//si coincide, lo va contando y acumulando(si es que se ingresan mas de ese tipo)
            }
        }
        return contador;//devuelve la cantidad de socios totales luego de recorrer
    }
    
    /**
      * Se crea un arraylist con todos los prestamos actualmente
      * vencidos segun los registros de los libros.
     */
    public ArrayList<Prestamo> prestamosVencidos(){
        ArrayList<Prestamo> vencidos = new ArrayList<Prestamo>();//se inicializa un array de prestamos para los vencidos
        for(Libro libro : this.getArrayLibros()){//se recorre/itera sobre ese array
            for(Prestamo prestamo : libro.getPrestamos()){
                if(prestamo.vencido(prestamo.getFechaDevolucion())){//verifica si el prestamo esta vencido
                    vencidos.add(prestamo);//agrega el prestamo vencido al array inicializado en el metodo
                }
            }
        }
        return vencidos;//retorna el array de prestamos vencidos
    }
    
    /**
      * Se crea un ArrayList con todos
      * los docentes que no tuvieron ni tienen préstamos vencidos.
     */
    public ArrayList<Socio> docentesResponsables(){
        ArrayList<Socio> docentes = new ArrayList<>();//inicializa un array para los docentes responsables
        for(Socio socio : this.getArraySocios()){//recorre/itera sobre el array inicializado (de socio)
            if(socio.soyDeLaClase().equals("Docente") && ((Docente)socio).esResponsable()){
                //verifica que sea docente y que su "estado" de responsable sea true
                docentes.add(socio);//añande ese docente responsable al array
            }
        }
        return docentes;//devuelve el array inicializado en el metodo con los docentes responsables
    }
    
    /**
      * Devuelve al socio que tiene el libro en su poder junto al titulo del libro
      * caso contrario si no se encuentra prestado lanza una excepción de tipo
      * LibroNoPrestadoException con un mensaje descriptivo.
      * @param p_libro
      * @throw LibroNoPrestadoException
      * @return String
     */
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException{
        if(p_libro.prestado()){//verifica si el libro esta prestado
            return p_libro.ultimoPrestamo().getSocio().toString();//devuelve la informacion del socio que tiene el libro mediante el toString()
        }else{//si no esta prestado...
            throw new LibroNoPrestadoException("El libro no esta prestado");
        }
    }
    
    /**
      * Realiza una lista de todos los socios de la biblioteca y podemos saber
      * cuantos son Docentes y cuantos son Estudiantes
      * @return String
     */
    public String listaDeSocios(){
        String listar = "\tLista de Socios: \n";
        int cantEstudiantes = 0;
        int cantDocentes = 0;
        int item = 0;
        for(Socio socio : this.getArraySocios()){//recorre/itera sobre el array de socios
            item++;
            listar += item + ")" + socio.toString() + "\n";//añade la informacion de cada socio
        }
        cantDocentes = this.cantidadDeSociosPorTipo("Docente");//cuenta los docentes
        cantEstudiantes = this.cantidadDeSociosPorTipo("Estudiante");//cuenta los estudiantes
        listar += "\n**************************************\n";
        listar += "Cantidad de Socios del tipo Estudiante: " + cantEstudiantes + "\n";
        listar += "Cantidad de Socios del tipo Docente: " + cantDocentes + "\n";
        listar += "**************************************";
        return listar;//devuelve la lista completa
    }
    
    /**
      * Devuelve el Socio que tiene el dni que recibe como parametro,
      * o null si no lo encuentra
      * @param p_dni
      * @return {@code true} devuelve el socio, {@code false} si no null
     */
    public Socio buscarSocio(int p_dni){
        for(Socio socio : this.getArraySocios()){//itera/recorre el array de socios
            if(socio.getDniSocio() == p_dni){//compara el dni del socio actual con el ingresado por parametro
                return socio;//retorna el socio
            }
        }
        
        return null;//si el for terimna sin encontrar al socio, retorna null
    }
    
    /**
      * Lista los titulos de libros con los que cuenta la biblioteca, lo hace sin repetir elemento
      * @return String
     */
    public String listaDeTitulos(){
        //Hago un conjunto de titulos (sin repetir titulos), para asegurar titulos unicos
        HashSet<String> listaTitulos = new HashSet<>();
        for(Libro libro : this.getArrayLibros()){//recorre/itera sobre el array de libros
            listaTitulos.add(libro.getTitulo());//añade el titulo al conjunto
        }
        // Convertimos el conjunto de títulos en una cadena, separada por saltos de línea
        return String.join("\n", listaTitulos);//une los titulos unicos
    }
    
    /**
      * Lista los libros de la biblioteca y si estos fueron o no prestados
      * @return String
     */
    public String listaDeLibros(){
        StringBuilder lista = new StringBuilder("\tLista de Libros:\n");
        int item = 0;
        for(Libro libro : this.getArrayLibros()){//recorre/itera sobre los libros
            item++;
            String si_no = libro.prestado() ? "SI" : "NO";//determina si el libro esta prestado
            //añade numero, descripcion del libro y el estado del prestamo
            lista.append(item).append(") ").append(libro.toString()).append("|| Prestado: ").append(si_no).append("\n");
        }
        return lista.toString();//devuelve la lista completa
    }
    
    /**
      * Lista de Docentes Responsables
      * @return String
     */
    public String listaDeDocentesResponsables(){
        StringBuilder texto = new StringBuilder("\nLista de Docentes Responsables: \n");
        for(Socio socio : this.docentesResponsables()){//recorre/itera sobre el array de docentes responsables
            texto.append("* ").append(socio).append("\n");//añade la informacion a cada docente
        }
        return texto.toString();//devuelve la lista de docentes responsables
    }
}

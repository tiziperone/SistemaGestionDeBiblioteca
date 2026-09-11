import java.util.*;

/**
 * Programa ejecutable que administra una
 * biblioteca, agregando socios, libros y
 * creando prestamos
 */
public class GestionBiblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Calendar fecha_inicial = Calendar.getInstance();
        
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        
        biblioteca.nuevoLibro("Progamando con Java", 1, "Fuente", 2008);
        biblioteca.nuevoLibro("POO", 2, "TechBooks", 2009);
        
        biblioteca.nuevoSocioDocente(1111, "Lucas", "Filosofia");
        biblioteca.nuevoSocioEstudiante(2222, "Mateo", "Biologia");
        biblioteca.nuevoSocioEstudiante(3333, "Fabricio", "Lic. En Sistemas");
        
        
        //prestamo vencidos
        Libro unLibro = biblioteca.getArrayLibros().get(1);
        Socio unDocente = biblioteca.getArraySocios().get(0);
        Calendar fecha_vencido = (Calendar)(new GregorianCalendar(2024, 1, 10));
        
        biblioteca.prestarLibro(fecha_vencido, unDocente, unLibro);
        try{
            biblioteca.devolverLibro(biblioteca.getArrayLibros().get(1));
        }catch(LibroNoPrestadoException e){
            System.out.println(e.getMessage());
        }
        
        int option;
        
        do {
            System.out.println("\n******** MENÚ DE BIBLIOTECA ********");
            System.out.println("\n1. Agregar un nuevo libro.");
            System.out.println("2. Agregar un nuevo socio docente.");
            System.out.println("3. Agregar un nuevo socio estudiante.");
            System.out.println("4. Prestar un libro.");
            System.out.println("5. Devolver un libro.");
            System.out.println("6. Mostrar cantidad de socios por tipo.");
            System.out.println("7. Listar préstamos vencidos.");
            System.out.println("8. Listar docentes responsables.");
            System.out.println("9. Consultar quién tiene un libro.");
            System.out.println("10. Listar todos los socios.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                //Agregar libro
                case 1:
                    System.out.print("-- Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    
                    System.out.print("-- Ingrese la edición del libro: ");
                    int edicion = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("-- Ingrese la editorial del libro: ");
                    String editorial = scanner.nextLine();
                    
                    System.out.print("-- Ingrese el año de publicación del libro: ");
                    int anio = scanner.nextInt();
                    biblioteca.nuevoLibro(titulo, edicion, editorial, anio);
                    
                    System.out.println("-- Libro agregado exitosamente.");
                    break;
                //Agregar docente
                case 2:
                    System.out.print("-- Ingrese el DNI del socio docente: ");
                    int dniDocente = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("-- Ingrese el nombre del docente: ");
                    String nombreDocente = scanner.nextLine();
                    
                    System.out.print("-- Ingrese el área del docente: ");
                    String area = scanner.nextLine();
                    
                    biblioteca.nuevoSocioDocente(dniDocente, nombreDocente, area);
                    System.out.println("-- Socio docente agregado exitosamente.");
                    
                    break;
                //Agregar estudiante
                case 3: 
                    System.out.print("-- Ingrese el DNI del socio estudiante: ");
                    int dniEstudiante = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("-- Ingrese el nombre del estudiante: ");
                    String nombreEstudiante = scanner.nextLine();
                    
                    System.out.print("-- Ingrese la carrera del estudiante: ");
                    String carrera = scanner.nextLine();
                    
                    biblioteca.nuevoSocioEstudiante(dniEstudiante, nombreEstudiante, carrera);
                    System.out.println("-- Socio estudiante agregado exitosamente.");
                    break;
                
                //Prestar libro
                case 4:
                    {
                        int socioAPrestar = 0;
                        int libroAPrestar = 0;
                        
                        //seleccionar libro a prestar
                        int i = 0;
                        ArrayList<Libro> libros = biblioteca.getArrayLibros();
                        System.out.println("-- Libros disponibles: ");
                        for(Libro l : libros){
                            System.out.println("\t" + i + ". " + l.getTitulo());
                            i++;
                        }
                        System.out.print("-- Ingrese el libro por prestar: ");
                        libroAPrestar = scanner.nextInt();
                        
                        if( !(libroAPrestar >= 0 && libroAPrestar < libros.size()) ){
                            System.out.println("-- Ingreso invalido.");
                            continue;
                        }
                        
                        //seleccionar socio al que prestar
                        i = 0;
                        ArrayList<Socio> socios = biblioteca.getArraySocios();
                        System.out.println("-- Socios disponibles: ");
                        for(Socio s : socios){
                            System.out.println("\t" + i + ". " + s.getNombre());
                            i++;
                        }
                        System.out.print("-- Ingrese socio al que prestar: ");
                        socioAPrestar = scanner.nextInt();
                        
                        if( !(socioAPrestar >= 0 && socioAPrestar < socios.size()) ){
                            System.out.println("--Ingreso invalido.");
                            continue;
                        }
                        
                        //realizar el prestamo
                        Socio socio = socios.get(socioAPrestar);                    
                        fecha_inicial.add(Calendar.DATE, 3);
                        Calendar fechaRetiro = fecha_inicial;
                        
                        biblioteca.prestarLibro(fechaRetiro, socio, libros.get(socioAPrestar));
                        System.out.println("-- Préstamo realizado exitosamente.");
                    }
                    
                    break;
                //Devolver libro
                case 5:
                    {
                        int libroADevolver = 0;
                        
                        //seleccionar libro a devolver
                        int i = 0;
                        ArrayList<Libro> libros = biblioteca.getArrayLibros();
                        System.out.println("-- Libros disponibles: ");
                        for(Libro l : libros){
                            System.out.println("\t" + i + ". " + l.getTitulo());
                            i++;
                        }
                        System.out.print("-- Ingrese el libro a devolver: ");
                        libroADevolver = scanner.nextInt();
                        
                        if( !(libroADevolver >= 0 && libroADevolver < libros.size()) ){
                            System.out.println("-- Ingreso invalido.");
                            continue;
                        }   
                        Libro libro = libros.get(libroADevolver);
                        
                        try {
                            biblioteca.devolverLibro(libro);
                            System.out.println("-- Libro devuelto exitosamente");
                        }catch(LibroNoPrestadoException e){
                            System.out.println(e.getMessage());
                        }
                        
                        
                    }
                    
                    break;  
                //Mostrar socio por tipo
                case 6:
                    System.out.print("-- Ingrese el tipo de socio (Docente o Estudiante): ");
                    String tipoSocio = scanner.nextLine();
                    int cantidadSocios = biblioteca.cantidadDeSociosPorTipo(tipoSocio);
                    System.out.println("\nCantidad de socios del tipo " + tipoSocio + ": " + cantidadSocios);
                    break;
                
                //Mostrar prestamos vencidos
                case 7:
                    ArrayList<Prestamo> prestamosVencidos = biblioteca.prestamosVencidos();
                    if (prestamosVencidos.isEmpty()) {
                        System.out.println("-- No hay préstamos vencidos.");
                    } else {
                        System.out.println("-- Préstamos vencidos:");
                        for (Prestamo prestamo : prestamosVencidos) {
                            System.out.println(prestamo.toString());
                        }
                    }
                    break;
                
                //Mostrar docentes deudores
                case 8: 
                    ArrayList<Socio> docentesResponsables = biblioteca.docentesResponsables();
                    if (docentesResponsables.isEmpty()) {
                        System.out.println("-- No hay docentes con libros pendientes.");
                    } else {
                        System.out.println(biblioteca.listaDeDocentesResponsables());
                    }
                    break;
                
                //Consultar quien tiene libro
                case 9:
                    System.out.println("-- Seleccione al libro a consultar: ");
                    
                    int libro_seleccionado = 0;
                    int i = 1;
                    ArrayList<Libro> libros = biblioteca.getArrayLibros();
                    for(Libro l : libros){
                        System.out.println("--" + i + ". " + l.getTitulo());
                        i++;
                    }
                    libro_seleccionado = scanner.nextInt();
                    
                    if(!(libro_seleccionado >= 0 && libro_seleccionado < libros.size())){
                        System.out.println("--Ingreso invalido.");
                        continue;
                    }
                    
                    Libro libroConsulta = libros.get(libro_seleccionado);
                    try {
                        System.out.println(biblioteca.quienTieneElLibro(libroConsulta));
                    } catch (LibroNoPrestadoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                
                    //Listar socios
                case 10:
                    System.out.println(biblioteca.listaDeSocios());
                    break;
                    
                    //Salir
                case 0:
                    System.out.println("-- Saliendo del sistema de biblioteca.");
                    break;

                default:
                    System.out.println("-- Opción no válida. Intente nuevamente.");
                    break;
            }
        }while (option != 0);
    }
    
}
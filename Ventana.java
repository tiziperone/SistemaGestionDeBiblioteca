import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase Ventana que implementa una interfaz gráfica para gestionar la Biblioteca.
 */
public class Ventana extends JFrame implements ActionListener 
{ //hereda toda la funcionalidad de una ventana

    //Atributo principal para manejar la ventana (alli se dirije al acccionar algo).
    private Biblioteca biblioteca;

    //Botones del menú
    private JButton btnAgregarLibro;
    private JButton btnAgregarDocente;
    private JButton btnAgregarEstudiante;
    private JButton btnPrestar;
    private JButton btnDevolver;
    private JButton btnMostrarCantidad;
    private JButton btnListarVencidos;
    private JButton btnResponsables;
    private JButton btnConsultarQuienTiene;
    private JButton btnListarSocios;
    private JButton btnListarLibros; 
    private JButton btnSalir;        

    /**
     * Constructor de la Ventana.
     * Configura la ventana principal, inicializa la biblioteca con datos
     * y crea los botones del menú.
     */
    public Ventana(){ //aqui se "arma" la ventana
        // 1. Inicializar la biblioteca
        this.biblioteca = new Biblioteca("Biblioteca Central (GUI)");
        inicializarDatos();

        // 2. Configurar la ventana
        setTitle("Gestión de Biblioteca");//titulo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sirve para apretar la "x" de la ventana y cerrar
        setSize(400, 700);//tamaño
        setLocationRelativeTo(null);//Centrar en pantalla

        //Menú de botones, uno debajo del otro
        setLayout(new GridLayout(12, 1, 10, 10));//realizar un apartado de 12 filas y 1 columna

        //3. Crear y configurar los botones
        btnAgregarLibro = new JButton("1. Agregar nuevo libro");
        btnAgregarDocente = new JButton("2. Agregar nuevo socio docente");
        btnAgregarEstudiante = new JButton("3. Agregar nuevo socio estudiante");
        btnPrestar = new JButton("4. Prestar un libro");
        btnDevolver = new JButton("5. Devolver un libro");
        btnMostrarCantidad = new JButton("6. Mostrar cantidad de socios por tipo");
        btnListarVencidos = new JButton("7. Listar préstamos vencidos");
        btnResponsables = new JButton("8. Docentes responsables");
        btnConsultarQuienTiene = new JButton("9. Consultar quién tiene un libro");
        btnListarSocios = new JButton("10. Listar todos los socios");
        btnListarLibros = new JButton("11. Listar todos los libros"); 
        btnSalir = new JButton("0. Salir");                         

        //4. Para que los botones hagan algo al hacer clic)
        btnAgregarLibro.addActionListener(this);//cuando se haga click en el boton, avisar a this
        btnAgregarDocente.addActionListener(this);
        btnAgregarEstudiante.addActionListener(this);
        btnPrestar.addActionListener(this);
        btnDevolver.addActionListener(this);
        btnMostrarCantidad.addActionListener(this);
        btnListarVencidos.addActionListener(this);
        btnResponsables.addActionListener(this);
        btnConsultarQuienTiene.addActionListener(this);
        btnListarSocios.addActionListener(this);
        btnListarLibros.addActionListener(this); 
        btnSalir.addActionListener(this);        

        //5. Añadir los botones a la ventana
        add(btnAgregarLibro);
        add(btnAgregarDocente);
        add(btnAgregarEstudiante);
        add(btnPrestar);
        add(btnDevolver);
        add(btnMostrarCantidad);
        add(btnListarVencidos);
        add(btnResponsables);
        add(btnConsultarQuienTiene);
        add(btnListarSocios);
        add(btnListarLibros); 
        add(btnSalir);        

        //6. Hacer visible la ventana
        setVisible(true);
    }

    /**
     * Carga los datos iniciales en la biblioteca, tal como lo hace
     * el método main() de GestionBiblioteca.
     */
    private void inicializarDatos(){
        biblioteca.nuevoLibro("Progamando con Java", 1, "Fuente", 2008);
        biblioteca.nuevoLibro("POO", 2, "TechBooks", 2009);

        biblioteca.nuevoSocioDocente(1111, "Lucas", "Filosofia");
        biblioteca.nuevoSocioEstudiante(2222, "Mateo", "Biologia");
        biblioteca.nuevoSocioEstudiante(3333, "Fabricio", "Lic. En Sistemas");

        Libro unLibro = biblioteca.getArrayLibros().get(1);
        Socio unDocente = biblioteca.getArraySocios().get(0);
        Calendar fecha_vencido = (Calendar) (new GregorianCalendar(2024, 1, 10));

        biblioteca.prestarLibro(fecha_vencido, unDocente, unLibro);
        try {
            biblioteca.devolverLibro(biblioteca.getArrayLibros().get(1));
        } catch (LibroNoPrestadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Inicialización.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal que maneja todos los clics de los botones.
     */
    public void actionPerformed(ActionEvent e){
        Object botonPresionado = e.getSource();//informa que boton se preciono

        try {
            //1. Agregar Libro
            if (botonPresionado == btnAgregarLibro){//funciona de manera similar al switch de GestionBiblioteca
                String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro:");//ventana emergente que solicita algo
                if (titulo == null || titulo.trim().isEmpty()) return; 
                int edicion = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la edición:"));//ventana emergente que muestra un dialogo
                String editorial = JOptionPane.showInputDialog(this, "Ingrese la editorial:");
                if (editorial == null || editorial.trim().isEmpty()) return;
                int anio = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el año:"));
                biblioteca.nuevoLibro(titulo, edicion, editorial, anio);
                JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.");
            }
            
            //2. Agregar Docente 
            else if (botonPresionado == btnAgregarDocente) {
                int dni = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese DNI docente:"));
                String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre docente:");
                if (nombre == null || nombre.trim().isEmpty()) return;
                String area = JOptionPane.showInputDialog(this, "Ingrese área docente:");
                if (area == null || area.trim().isEmpty()) return;
                biblioteca.nuevoSocioDocente(dni, nombre, area);
                JOptionPane.showMessageDialog(this, "Socio docente agregado exitosamente.");
            }

            //3. Agregar Estudiante
            else if (botonPresionado == btnAgregarEstudiante) {
                int dni = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese DNI estudiante:"));
                String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre estudiante:");
                if (nombre == null || nombre.trim().isEmpty()) return;
                String carrera = JOptionPane.showInputDialog(this, "Ingrese carrera estudiante:");
                if (carrera == null || carrera.trim().isEmpty()) return;
                biblioteca.nuevoSocioEstudiante(dni, nombre, carrera);
                JOptionPane.showMessageDialog(this, "Socio estudiante agregado exitosamente.");
            }

            //4. Prestar Libro
            else if (botonPresionado == btnPrestar) {
                Libro libroSel = elegirLibro("Prestar Libro - Seleccionar Libro");
                if (libroSel == null) return; // Se canceló o hubo error

                Socio socioSel = elegirSocio("Prestar Libro - Seleccionar Socio");
                if (socioSel == null) return; // Se canceló o hubo error
                
                // Usamos la fecha actual para el préstamo
                if (biblioteca.prestarLibro(Calendar.getInstance(), socioSel, libroSel)) {
                    JOptionPane.showMessageDialog(this, "Préstamo realizado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo realizar el préstamo.\n(El libro ya está prestado).", "Préstamo Fallido", JOptionPane.WARNING_MESSAGE);
                }
            }

            //5. Devolver Libro
            else if (botonPresionado == btnDevolver) {
                Libro libroSel = elegirLibro("Devolver Libro - Seleccionar Libro");
                if (libroSel == null) return; // Se canceló o hubo error
                
                //El método devolverLibro puede lanzar una excepción
                biblioteca.devolverLibro(libroSel);
                JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente.");
            }

            //6. Cantidad Socios por Tipo
            else if (botonPresionado == btnMostrarCantidad) {
                String[] tipos = {"Docente", "Estudiante"};
                String tipoSocio = (String) JOptionPane.showInputDialog(this, "Seleccione el tipo de socio:",
                        "Cantidad por Tipo", JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

                if (tipoSocio != null) {
                    int cantidad = biblioteca.cantidadDeSociosPorTipo(tipoSocio);
                    JOptionPane.showMessageDialog(this, "Cantidad de socios del tipo " + tipoSocio + ": " + cantidad);
                }
            }

            //7. Listar Vencidos
            else if (botonPresionado == btnListarVencidos) {
                ArrayList<Prestamo> vencidos = biblioteca.prestamosVencidos();
                if (vencidos.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay préstamos vencidos.");
                } else {
                    StringBuilder lista = new StringBuilder("--- Préstamos Vencidos ---\n\n");
                    for (Prestamo p : vencidos) {
                        lista.append(p.toString().replace("\\n", "\n"));
                        lista.append("\n------------------------------\n");
                    }
                    mostrarResultados(lista.toString(), "Préstamos Vencidos");
                }
            }
            
            //8. Listar Docentes Responsables
            else if (botonPresionado == btnResponsables) {
                String lista = biblioteca.listaDeDocentesResponsables();
                if(biblioteca.docentesResponsables().isEmpty()){
                   JOptionPane.showMessageDialog(this, "No hay docentes responsables (sin préstamos vencidos).");
                } else {
                   gestionResponsables(lista, "Docentes Responsables");
                }
            }

            //9. Consultar Quién Tiene Libro
            else if (botonPresionado == btnConsultarQuienTiene) {
                Libro libroSel = elegirLibro("Consultar Préstamo - Seleccionar Libro");
                if (libroSel == null) return; // Se canceló o hubo error
                
                // Este método también puede lanzar la excepción
                String infoSocio = biblioteca.quienTieneElLibro(libroSel);
                JOptionPane.showMessageDialog(this, "El libro está en posesión de:\n" + infoSocio);
            }

            //10. Listar Todos los Socios
            else if (botonPresionado == btnListarSocios) {
                String lista = biblioteca.listaDeSocios();
                mostrarResultados(lista, "Lista de Socios");
            }
            
            //11. Listar Todos los Libros
            else if (botonPresionado == btnListarLibros) {
                String lista = biblioteca.listaDeLibros();
                if (biblioteca.getArrayLibros().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay libros en la biblioteca.");
                } else {
                    mostrarResultados(lista, "Lista de Libros");
                }
            }
            
            //0. Salir
            else if (botonPresionado == btnSalir) {
                System.exit(0); //Cierra la aplicación
            }
            
        } catch (NumberFormatException ex) {
            // Captura de error si el usuario no ingresa un número (para DNI, año, etc.)
            JOptionPane.showMessageDialog(this, "Entrada inválida. Se esperaba un número.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        
        } catch (LibroNoPrestadoException ex) {
            // Captura de error para los casos 5 y 9
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Préstamo", JOptionPane.ERROR_MESSAGE);
        
        } catch (Exception ex) {
            // Captura genérica para cualquier otro error
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Muestra el error en la consola de desarrollo
        }
    }

    /**
     * Métodos para mostrar textos largos (como listas) 
     * @param texto El String largo a mostrar.
     * @param titulo El título de la ventana de diálogo.
     */
    private void mostrarResultados(String texto, String titulo) {
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);//mostrar la lista y pedir que se escriba algo
    }
    
    /**
     * Métodos para mostrar los docentes responsables, con la opción de cambiar
     * los días de préstamo de alguno de ellos.
     * @param texto El String largo a mostrar.
     * @param titulo El título de la ventana de diálogo.
     */
    private void gestionResponsables(String texto, String titulo) {
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        String[] opciones = {"Aceptar", "Cambiar días de préstamo"};
        int input = JOptionPane.showOptionDialog(this, scrollPane, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);//mostrar la lista y pedir que se escriba algo
        if (input == 1) {
            Docente docSel = (Docente) elegirResponsable("Cambiar días de préstamo");
            if (docSel == null) return; // Se canceló o hubo error
            
            int dias = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese días de préstamo nuevos:"));
            if (dias == 0) return;
            
            docSel.cambiarDiasDePrestamos(dias);
            JOptionPane.showMessageDialog(this, "Días de préstamo cambiados exitosamente.");
        }
    }
    
    /**
     * Muestra una lista de docentes y pide al usuario que ingrese un número.
     * @param tituloDialogo El título para la ventana de input.
     * @return El docente seleccionado, o null si hay error o se cancela.
     */
    private Socio elegirResponsable(String tituloDialogo) {
        ArrayList<Socio> responsables = biblioteca.docentesResponsables();
        
        // 1. Construir el String de la lista
        StringBuilder lista = new StringBuilder("-- Docentes Responsables --\n");
        for (int i = 0; i < responsables.size(); i++) {
            lista.append(i+1).append(". ").append(responsables.get(i).toString()).append("\n");
        }
        lista.append("\nIngrese el NÚMERO del docente:");

        // 2. Pedir el número
        String input = JOptionPane.showInputDialog(this, lista.toString(), tituloDialogo, JOptionPane.PLAIN_MESSAGE);
        
        if (input == null) return null; // Usuario presionó "Cancelar"

        // 3. Validar y devolver
        try {
            int indice = (Integer.parseInt(input)) - 1;
            if (indice >= 0 && indice < responsables.size()) {
                return responsables.get(indice);
            } else {
                JOptionPane.showMessageDialog(this, "Número fuera de rango.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Debe ingresar un NÚMERO.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Muestra una lista de libros y pide al usuario que ingrese un número.
     * @param tituloDialogo El título para la ventana de input.
     * @return El Libro seleccionado, o null si hay error o se cancela.
     */
    private Libro elegirLibro(String tituloDialogo) {
        ArrayList<Libro> libros = biblioteca.getArrayLibros();
        if (libros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay libros en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // 1. Construir el String de la lista
        StringBuilder lista = new StringBuilder("-- Lista de Libros --\n");
        for (int i = 0; i < libros.size(); i++) {
            lista.append(i+1).append(". ").append(libros.get(i).getTitulo()).append("\n");
        }
        lista.append("\nIngrese el NÚMERO del libro:");

        // 2. Pedir el número
        String input = JOptionPane.showInputDialog(this, lista.toString(), tituloDialogo, JOptionPane.PLAIN_MESSAGE);
        
        if (input == null) return null; // Usuario presionó "Cancelar"

        // 3. Validar y devolver
        try {
            int indice = (Integer.parseInt(input)) - 1;
            if (indice >= 0 && indice < libros.size()) {
                return libros.get(indice);
            } else {
                JOptionPane.showMessageDialog(this, "Número de libro inválido (fuera de rango).", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Debe ingresar un NÚMERO.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Muestra una lista de socios y pide al usuario que ingrese un número.
     * @param tituloDialogo El título para la ventana de input.
     * @return El Socio seleccionado, o null si hay error o se cancela.
     */
    private Socio elegirSocio(String tituloDialogo) {
        ArrayList<Socio> socios = biblioteca.getArraySocios();
        if (socios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay socios en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // 1. Construir el String de la lista
        StringBuilder lista = new StringBuilder("-- Lista de Socios --\n");
        for (int i = 0; i < socios.size(); i++) {
            //Se usa toString() de Socio que es muy descriptivo
            lista.append(i+1).append(". ").append(socios.get(i).toString()).append("\n");
        }
        lista.append("\nIngrese el NÚMERO del socio:");

        // 2. Pedir el número
        String input = JOptionPane.showInputDialog(this, lista.toString(), tituloDialogo, JOptionPane.PLAIN_MESSAGE);
        
        if (input == null) return null; // Usuario presionó "Cancelar"

        // 3. Validar y devolver
        try {
            int indice = (Integer.parseInt(input)) - 1;
            if (indice >= 0 && indice < socios.size()) {
                return socios.get(indice);
            } else {
                JOptionPane.showMessageDialog(this, "Número de socio inválido (fuera de rango).", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un NÚMERO.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Método main() para ejecutar la interfaz gráfica.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ventana();
            }
        });
    }
}
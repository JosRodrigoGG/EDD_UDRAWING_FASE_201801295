package rodrigo.garcia.menu;

import rodrigo.garcia.bin.Busqueda;
import rodrigo.garcia.bin.ClienteAtendido;
import rodrigo.garcia.bin.TIPO;

import java.io.IOException;

public class Menu {
    public Menu() {
    }

    public static void menuPrincipal() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|           SIMULADOR           |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("1. PARAMETROS INICIALES");
        System.out.println("2. EJECUTAR PASOS ");
        System.out.println("3. ESTADO EN MEMORIA DE LAS ESTRUCTURAS");
        System.out.println("4. REPORTES");
        System.out.println("5. ACERCA DE .....");
        System.out.println("6. BORRAR DATOS");
        System.out.println("7. SALIR");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void reporte() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|            REPORTE            |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("1. TOP 5 MAYOR CANTIDAD DE IMAGENES A COLOR");
        System.out.println("2. TOP 5 MENOR CANTIDAD DE IMAGENES A BLANCO Y NEGRO");
        System.out.println("3. CLIENTE CON MAS PASOS EN EL SISTEMA");
        System.out.println("4. BUSCAR CLIENTE");
        System.out.println("5. REGRESAR");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void buscarCliente() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|            BUSCAR             |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("NOMBRE DEL CLIENTE");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void mostrarCliente(ClienteAtendido clienteAtendido) {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|            CLIENTE            |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("NOMBRE DEL CLIENTE : " + clienteAtendido.getCliente().getNombre());
        System.out.println("PASOS : " + clienteAtendido.getPasos());
        System.out.println("IMAGENES : " + clienteAtendido.getCliente().getImagenes().tamanio());
        System.out.println("VENTANILLA : " + clienteAtendido.getVentanilla());
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void datoInvalido() {
        borrarLineas();
        System.out.println("");
        System.out.println("CLIENTE NO ENCONTRADO");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void clientePasos(Busqueda busqueda) {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|            CLIENTE            |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("NOMBRE : " + busqueda.getNombre());
        System.out.println("PASOS : " + busqueda.getCantidad());
        System.out.print(">/ ");
    }

    public static void estado(int pasos) {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|             ESTADO            |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("INGRESE EL PASO QUE QUIERE VISUALIZAR");
        System.out.println("RANGO (0 -> " + pasos + "):");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void errorPaso() {
        borrarLineas();
        System.out.println("");
        System.out.println("PASO NO ENCONTRADO");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void estructuraGenerada(String estructura) {
        System.out.println("LA ESTRUCTURA " + estructura + " FUE GENERADA");
    }

    public static void estructuraVacia(String estructura) {
        System.out.println("LA ESTRUCTURA " + estructura + " NO TIENE DATOS REGISTRADOS");
    }

    public static void menuCarga() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|         CARGA DE DATOS        |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("0. REGRESAR");
        System.out.println("1. CARGA MASIVA DE CLIENTES");
        System.out.println("2. CANTIDAD DE VENTANILLAS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void cargarClientes() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|           CLIENTES           |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("INGRESE LA RUTA DEL ARCHIVO (.JSON)");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void cargarVentanillas() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|          VENTANILLAS          |");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("INGRESE EL NUMERO DE VENTANILLAS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void datosEstudiante() {
        borrarLineas();
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|              DATOS            |");
        System.out.println("--------------------------------");
        System.out.println("JOSE RODRIGO GARCIA GODINEZ");
        System.out.println("201801295");
        System.out.println("ESTRUCTURA DE DATOS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void errorOpciones() {
        borrarLineas();
        System.out.println("");
        System.out.println("OPCION INVALIDA");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void errorArchivo() {
        borrarLineas();
        System.out.println("");
        System.out.println("NO EXISTE EL ARCCHIVO");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void errorExtension() {
        borrarLineas();
        System.out.println("");
        System.out.println("EXTENSION INVALIDA DEL ARCHIVO DE ENTRADA");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void errorVentanilla() {
        borrarLineas();
        System.out.println("");
        System.out.println("NUMERO INVALIDO DE VENTANILLAS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void ventanillasCargadas() {
        borrarLineas();
        System.out.println("");
        System.out.println("VENTANILLAS CARGADAS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void errorDesconocido() {
        borrarLineas();
        System.out.println("");
        System.out.println("SABER QUE PASO");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void salidaPrograma() {
        borrarLineas();
        System.out.println("");
        System.out.println("FIN DE LA SIMULACION");
        System.out.println("");
    }

    public static void simulacionTerminada() {
        System.out.println("FIN DE LA SIMULACION");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void clientesCargados() {
        borrarLineas();
        System.out.println("");
        System.out.println("CLIENTES CARGADOS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void asignacionCliente(String cliente, int ventanilla) {
        System.out.println("EL CLIENTE " + cliente + " INGRESO A LA VENTANILLA " + ventanilla);
    }

    public static void recibirImagen(int ventanilla, TIPO tipo, String cliente) {
        System.out.println("LA VENTANILLA " + ventanilla + " RECIBIO UNA IMAGEN TIPO " + tipo + " DEL CLIENTE " + cliente);
    }

    public static void entregarImagen(int ventanilla, String cliente, TIPO tipo) {
        System.out.println("LA VENTANILLA " + ventanilla + " ENTREGO UNA IMAGEN " + tipo + " AL CLIENTE " + cliente);
    }

    public static void impresoraEntrega(TIPO tipo, int ventanilla) {
        System.out.println("LA VENTANILLA " + ventanilla + " RECOGIO UNA PAGINA DE LA IMPRESORA DE " + tipo);
    }

    public static void asignarImpresora(int ventanilla, TIPO tipo) {
        System.out.println("LA IMPRESORA " + tipo + " A OBTENIDO UNA IMAGEN DE LA VENTANILLA " + ventanilla);
    }

    public static void imprimirImagen(TIPO tipo, int ventanilla) {
        System.out.println("LA IMPRESORA " + tipo + " ESTA SACANDO UNA IMAGEN DE LA VENTANILLA " + ventanilla);
    }

    public static void impresoraEspera() {
        System.out.println("LA IMPRESORA IMG_COLOR ESTA SACANDO UNA IMAGEN");
    }

    public static void desAsignacionCliente(String cliente, int ventanilla) {
        System.out.println("EL CLIENTE " + cliente + " SALIO DE LA VENTANILLA " + ventanilla);
    }

    public static void simulacionReiniciada() {
        borrarLineas();
        System.out.println("");
        System.out.println("DATOS BORRADOS");
        System.out.println("");
        System.out.print(">/ ");
    }

    public static void borrarLineas() {
        try {
            ProcessBuilder pbuilder = new ProcessBuilder("cmd", "/c", "cls");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
}

package rodrigo.garcia.controlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rodrigo.garcia.bin.*;
import rodrigo.garcia.estructura.lista.Cola;
import rodrigo.garcia.estructura.lista.DobleEnlazada;
import rodrigo.garcia.estructura.lista.Simple;
import rodrigo.garcia.estructura.nodo.NodoCola;
import rodrigo.garcia.estructura.nodo.NodoDoble;
import rodrigo.garcia.menu.Menu;

import java.io.*;
import java.util.Map;
import java.util.Random;

import static rodrigo.garcia.bin.TIPO.IMG_BW;
import static rodrigo.garcia.bin.TIPO.IMG_COLOR;
import static rodrigo.garcia.menu.Menu.asignacionCliente;
import static rodrigo.garcia.menu.Menu.recibirImagen;

public class ControladorMain {
    private DobleEnlazada<Estructura> estructuras;
    private Simple<Ventanilla> ventanillas;
    private Cola<Cliente> recepcion;
    private Cola<Cliente> cargados;
    private DobleEnlazada<ClienteAtendido> clientesFinales;
    private final Dato dato;
    private Impresora imColor;
    private Impresora imBw;
    private int idVentanilla;
    private int idCliente;
    private int idImagen;
    private int bPasos;
    private boolean datosCargados;
    private boolean ventanillaCargada;
    private boolean simulacion;
    private Random random;
    private String[] nombre;
    private String[] apellido;
    private final String RUTA_ESCRITORIO = "C:\\Users\\josro\\Desktop";

    public ControladorMain() {
        random = new Random();
        dato = new Dato();
        ventanillas = new Simple<>();
        recepcion = new Cola<>();
        clientesFinales = new DobleEnlazada<>();
        cargados = new Cola<>();
        estructuras = new DobleEnlazada<>();
        idVentanilla = 0;
        idCliente = 0;
        bPasos = 0;
        simulacion = false;
        ventanillaCargada = false;
        datosCargados = false;
        imBw = new Impresora(TIPO.IMG_BW, ESTADO.APAGADA);
        imColor = new Impresora(TIPO.IMG_COLOR, ESTADO.APAGADA);
        nombre = new String[]{
                "Hugo",
                "Sofia",
                "Lucas",
                "Valentina",
                "Leo",
                "Maria Jose",
                "Alejandro",
                "Ximena",
                "Manuel",
                "Renata"};
        apellido = new String[]{
                "Hernandez",
                "Garcia",
                "Martinez",
                "Lopez",
                "Gonzalez",
                "Perez",
                "Rodriguez",
                "Sanchez",
                "Ramirez",
                "Cruz"
        };
    }

    public void MenuInicial() {
        int opcion = 0;
        do {
            Menu.menuPrincipal();
            opcion = dato.dato_int();
            if (opcion > 0 && opcion <= 7) {
                switch (opcion) {
                    case 1 -> {
                        do {
                            Menu.menuCarga();
                            opcion = dato.dato_int();
                            if (opcion >= 0 && opcion <= 2) {
                                switch (opcion) {
                                    case 0 -> {
                                        opcion = 0;
                                    }
                                    case 1 -> {
                                        Menu.cargarClientes();
                                        String ruta = dato.dato_string();
                                        ruta = "C:\\Users\\josro\\Downloads\\generated.json";
                                        if (new File(ruta).exists()) {
                                            if (extensionArchivo(ruta).toLowerCase().equals("json")) {
                                                try {
                                                    FileReader reader = new FileReader(ruta);
                                                    JSONParser jsonParser = new JSONParser();
                                                    JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
                                                    boolean bandera = true;
                                                    int contador = 1;
                                                    while (bandera) {
                                                        bandera = false;
                                                        if (jsonObject.get("Cliente" + contador) != null) {
                                                            JSONObject jsonObject2 = new JSONObject((Map) jsonObject.get("Cliente" + contador));
                                                            idCliente = Integer.parseInt(jsonObject2.get("id_cliente").toString());
                                                            Cliente cliente = new Cliente(
                                                                    (Integer) Integer.parseInt(jsonObject2.get("id_cliente").toString()),
                                                                    (String) jsonObject2.get("nombre_cliente").toString()
                                                            );
                                                            for (int i = 0; i < Integer.parseInt(jsonObject2.get("img_color").toString()); i++) {
                                                                cliente.agregarImagen(new Imagen(TIPO.IMG_COLOR, idImagen++));
                                                            }
                                                            for (int i = 0; i < Integer.parseInt(jsonObject2.get("img_bw").toString()); i++) {
                                                                cliente.agregarImagen(new Imagen(IMG_BW, idImagen++));
                                                            }
                                                            if (cliente.getImagenes().tamanio() != 0) {
                                                                recepcion.push(cliente);
                                                            }
                                                            cargados.push(cliente);
                                                            bandera = true;
                                                            contador++;
                                                        }
                                                    }
                                                    for (int i = 0; i < random.nextInt(4); i++) {
                                                        Cliente cliente = new Cliente(
                                                                idCliente++,
                                                                nombre[random.nextInt(nombre.length)] + " " + apellido[random.nextInt(apellido.length)]
                                                        );
                                                        for (int j = 0; j < random.nextInt(5); j++) {
                                                            switch (random.nextInt(2)) {
                                                                case 0 -> {
                                                                    cliente.agregarImagen(new Imagen(
                                                                            IMG_BW,
                                                                            idCliente++
                                                                    ));
                                                                }
                                                                default -> {
                                                                    cliente.agregarImagen(new Imagen(
                                                                            TIPO.IMG_COLOR,
                                                                            idCliente++
                                                                    ));
                                                                }
                                                            }
                                                        }
                                                        recepcion.push(cliente);
                                                    }
                                                    Menu.clientesCargados();
                                                    datosCargados = true;
                                                } catch (FileNotFoundException e) {
                                                    e.printStackTrace();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (ParseException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                Menu.errorExtension();
                                            }
                                        } else {
                                            Menu.errorArchivo();
                                        }
                                        String temp = dato.dato_string();
                                    }
                                    case 2 -> {
                                        Menu.cargarVentanillas();
                                        int numeroVentanillas = dato.dato_int();
                                        if (numeroVentanillas > 0) {
                                            cargarVentanillas(numeroVentanillas);
                                            Menu.ventanillasCargadas();
                                            ventanillaCargada = true;
                                        } else {
                                            Menu.errorVentanilla();
                                        }
                                        String temp = dato.dato_string();
                                    }
                                    default -> {
                                        Menu.errorDesconocido();
                                        String temp = dato.dato_string();
                                    }
                                }
                            } else {
                                Menu.errorOpciones();
                                String temp = dato.dato_string();
                            }
                        } while (opcion != 0);
                    }
                    case 2 -> {
                        if (ventanillaCargada && datosCargados) {
                            int pasos = 0;
                            int espera = 0;
                            boolean bandera = true;
                            cargarEstructura(0);
                            while (bandera) {
                                pasos++;
                                bandera = false;
                                System.out.println("--------------------------------");
                                System.out.println("PASO " + pasos);
                                NodoCola<Ventanilla> aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getCliente() != null && aux.getDato().getTipo() == TIPO.OCUPADA) {
                                            aux.getDato().setTipo(TIPO.TRABAJANDO);
                                            bandera = true;
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getTipo() == TIPO.TRABAJANDO && aux.getDato().getCliente().getImagenes().vacia()) {
                                            aux.getDato().setTipo(TIPO.MANDANDO);
                                            bandera = true;
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getTipo() == TIPO.MANDANDO && aux.getDato().getImagenes().vacia()) {
                                            aux.getDato().setTipo(TIPO.ESPERA);
                                            bandera = true;
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                if (imBw.getEstado() == ESTADO.APAGADA && !imBw.getImagenes().vacia()) {
                                    imBw.setEstado(ESTADO.ENCENDIDA);
                                    bandera = true;
                                }
                                if (imColor.getEstado() == ESTADO.APAGADA && !imColor.getImagenes().vacia()) {
                                    imColor.setEstado(ESTADO.ENCENDIDA);
                                    bandera = true;
                                }
                                if (!imBw.getPaginas().vacia()) {
                                    imBw.getPaginas().front().getImagen().setEstado(ESTADO.PRODUCCION);
                                    bandera = true;
                                }
                                if (!imColor.getPaginas().vacia()) {
                                    imColor.getPaginas().front().getImagen().setEstado(ESTADO.PRODUCCION);
                                    bandera = true;
                                }
                                if (!recepcion.vacia()) {
                                    if (asignarCliente(recepcion.front())) {
                                        recepcion.pop();
                                    }
                                    bandera = true;
                                }
                                aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getNumeroPaginas() != 0 && aux.getDato().getNumeroImagenes() != 0) {
                                            if (aux.getDato().getNumeroPaginas() == aux.getDato().getNumeroImagenes() &&
                                                    aux.getDato().getPaginas().vacia() && aux.getDato().getImagenes().vacia()) {
                                                Menu.desAsignacionCliente(
                                                        aux.getDato().getCliente().getNombre(),
                                                        aux.getDato().getIdVentanilla()
                                                );
                                                clientesFinales.agregar(new ClienteAtendido(
                                                        aux.getDato().getCliente(),
                                                        pasos,
                                                        aux.getDato().getIdVentanilla()
                                                ));
                                                aux.getDato().setCliente(null);
                                                aux.getDato().setTipo(TIPO.DISPONIBLE);
                                                aux.getDato().setNumeroImagenes(0);
                                                aux.getDato().setNumeroPaginas(0);
                                                bandera = true;
                                            }
                                        } else {

                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getNumeroPaginas() == aux.getDato().getNumeroImagenes() &&
                                                !aux.getDato().getPaginas().vacia()) {
                                            Imagen temp = aux.getDato().getPaginas().font().getImagen();
                                            aux.getDato().getPaginas().pop();
                                            aux.getDato().getCliente().agregarImagen(temp);
                                            Menu.entregarImagen(
                                                    aux.getDato().getIdVentanilla(),
                                                    aux.getDato().getCliente().getNombre(),
                                                    temp.getTipo()
                                            );
                                            bandera = true;
                                            break;
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getTipo() == TIPO.TRABAJANDO) {
                                            if (!aux.getDato().getCliente().getImagenes().vacia()) {
                                                aux.getDato().agregarImagen(new ImagenVentanilla(
                                                        aux.getDato(),
                                                        aux.getDato().getCliente(),
                                                        aux.getDato().getCliente().getImagenes().front()
                                                ));
                                                aux.getDato().numeroImagenes();
                                                recibirImagen(
                                                        aux.getDato().getIdVentanilla(),
                                                        aux.getDato().getCliente().getImagenes().front().getTipo(),
                                                        aux.getDato().getCliente().getNombre()
                                                );
                                                aux.getDato().getCliente().getImagenes().pop();
                                                bandera = true;
                                            }
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                aux = ventanillas.getLista();
                                while (aux != null) {
                                    if (aux != null) {
                                        if (aux.getDato().getTipo() == TIPO.MANDANDO) {
                                            switch (aux.getDato().getImagenes().font().getImagen().getTipo()) {
                                                case IMG_BW -> {
                                                    imBw.agregarImagen(aux.getDato().getImagenes().font());
                                                    aux.getDato().getImagenes().pop();
                                                    Menu.asignarImpresora(
                                                            aux.getDato().getIdVentanilla(),
                                                            IMG_BW
                                                    );
                                                    asignarId(IMG_BW);
                                                    bandera = true;
                                                }
                                                case IMG_COLOR -> {
                                                    imColor.agregarImagen(aux.getDato().getImagenes().font());
                                                    aux.getDato().getImagenes().pop();
                                                    Menu.asignarImpresora(
                                                            aux.getDato().getIdVentanilla(),
                                                            IMG_COLOR
                                                    );
                                                    asignarId(IMG_COLOR);
                                                    bandera = true;
                                                }
                                            }
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                }
                                if (imBw.getEstado() == ESTADO.ENCENDIDA) {
                                    if (!imBw.getImagenes().vacia()) {
                                        ImagenVentanilla temp = imBw.getImagenes().front();
                                        imBw.getImagenes().pop();
                                        imBw.agregarPaginas(temp);
                                        Menu.imprimirImagen(IMG_BW, temp.getVentanilla().getIdVentanilla());
                                        bandera = true;
                                    }
                                }
                                if (imColor.getEstado() == ESTADO.ENCENDIDA) {
                                    if (!imColor.getImagenes().vacia()) {
                                        if (espera == 0) {
                                            ImagenVentanilla temp = imColor.getImagenes().front();
                                            imColor.getImagenes().pop();
                                            imColor.agregarPaginas(temp);
                                            Menu.imprimirImagen(IMG_COLOR, temp.getVentanilla().getIdVentanilla());
                                            bandera = true;
                                            espera = 1;
                                        } else {
                                            Menu.impresoraEspera();
                                            bandera = true;
                                            espera = 0;
                                        }
                                    }
                                }
                                if (!imBw.getPaginas().vacia()) {
                                    if (imBw.getPaginas().front().getImagen().getEstado() == ESTADO.PRODUCCION) {
                                        ImagenVentanilla temp = imBw.getPaginas().front();
                                        imBw.getPaginas().pop();
                                        temp.getImagen().setEstado(ESTADO.ENTREGADAS);
                                        aux = ventanillas.getLista();
                                        while (aux != null) {
                                            if (aux != null) {
                                                if (aux.getDato().getIdVentanilla() == temp.getVentanilla().getIdVentanilla()) {
                                                    aux.getDato().agregarPagina(temp);
                                                    bandera = true;
                                                    Menu.impresoraEntrega(
                                                            IMG_BW,
                                                            aux.getDato().getIdVentanilla()
                                                    );
                                                    aux.getDato().numeroPaginas();
                                                    break;
                                                }
                                                aux = aux.getSiguiente();
                                            }
                                        }
                                    }
                                }
                                if (!imColor.getPaginas().vacia()) {
                                    if (imColor.getPaginas().front().getImagen().getEstado() == ESTADO.PRODUCCION) {
                                        ImagenVentanilla temp = imColor.getPaginas().front();
                                        imColor.getPaginas().pop();
                                        temp.getImagen().setEstado(ESTADO.ENTREGADAS);
                                        aux = ventanillas.getLista();
                                        while (aux != null) {
                                            if (aux != null) {
                                                if (aux.getDato().getIdVentanilla() == temp.getVentanilla().getIdVentanilla()) {
                                                    aux.getDato().agregarPagina(temp);
                                                    bandera = true;
                                                    Menu.impresoraEntrega(
                                                            IMG_COLOR,
                                                            aux.getDato().getIdVentanilla()
                                                    );
                                                    aux.getDato().numeroPaginas();
                                                    break;
                                                }
                                                aux = aux.getSiguiente();
                                            }
                                        }
                                    }
                                }
                                System.out.println("--------------------------------");
                                cargarEstructura(pasos);
                            }
                            bPasos = pasos;
                            Menu.simulacionTerminada();
                            simulacion = true;
                            String temp = dato.dato_string();
                        }
                    }
                    case 3 -> {
                        if (simulacion) {
                            Menu.estado(bPasos);
                            int paso = dato.dato_int();
                            if (paso >0 && paso <= bPasos) {
                                Menu.borrarLineas();
                                System.out.println("");
                                System.out.println("DATOS GENERADOS");
                                System.out.println("");
                                try {
                                    NodoDoble<Estructura> aux = estructuras.getLista();
                                    while (aux.getSiguiente() != estructuras.getLista()) {
                                        if (aux.getDato().getPaso() == paso) {
                                            switch (aux.getDato().getNombre()) {
                                                case "COLA" -> {
                                                    switch (aux.getDato().getObjeto()) {
                                                        case "CLIENTE ATENDIDO" -> {
                                                            String contenido = graficarRecepcion((Cola<Cliente>) aux.getDato().getEstructura());
                                                            if (contenido != null) {
                                                                String nombre = "RECEPCION_COLA (" + aux.getDato().getPaso() + ")";
                                                                crearDOT(nombre, contenido);
                                                                graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                                                Menu.estructuraGenerada("RECEPCION (COLA)");
                                                            } else {
                                                                Menu.estructuraVacia("RECEPCION (COLA)");
                                                            }
                                                        }
                                                        case "IMAGEN VENTANILLA COLOR" -> {
                                                            String contenido = graficarCOLOR((Cola<ImagenVentanilla>) aux.getDato().getEstructura());
                                                            if (contenido != null) {
                                                                String nombre = "IMPRESORA COLOR (" + aux.getDato().getPaso() + ")";
                                                                crearDOT(nombre, contenido);
                                                                graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                                                Menu.estructuraGenerada("IMPRESORA COLOR (COLA)");
                                                            } else {
                                                                Menu.estructuraVacia("IMPRESORA COLOR (COLA)");
                                                            }
                                                        }
                                                        case "IMAGEN VENTANILLA BW" -> {
                                                            String contenido = graficarBW((Cola<ImagenVentanilla>) aux.getDato().getEstructura());
                                                            if (contenido != null) {
                                                                String nombre = "IMPRESORA BW (" + aux.getDato().getPaso() + ")";
                                                                crearDOT(nombre, contenido);
                                                                graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                                                Menu.estructuraGenerada("IMPRESORA BW (COLA)");
                                                            } else {
                                                                Menu.estructuraVacia("IMPRESORA BW (COLA)");
                                                            }
                                                        }
                                                        default -> {
                                                            //D
                                                        }
                                                    }
                                                }
                                                case "DOBLE_ENLAZADA" -> {
                                                    String contenido = graficarClienteFinal((DobleEnlazada<ClienteAtendido>) aux.getDato().getEstructura());
                                                    if (contenido != null) {
                                                        String nombre = "CLIENTES FINALES_DOBLE (" + aux.getDato().getPaso() + ")";
                                                        crearDOT(nombre, contenido);
                                                        graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                                        Menu.estructuraGenerada("CLIENTES FINALES (DOBLE ENLAZADA)");
                                                    } else {
                                                        Menu.estructuraVacia("CLIENTES FINALES (DOBLE ENLAZADA)");
                                                    }
                                                }
                                                case "SIMPLE" -> {
                                                    String contenido = graficarVentanilla((Simple<Ventanilla>) aux.getDato().getEstructura());
                                                    if (contenido != null) {
                                                        String nombre = "VENTANILLA_SIMPLE (" + aux.getDato().getPaso() + ")";
                                                        crearDOT(nombre, contenido);
                                                        graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                                        Menu.estructuraGenerada("VENTANILLAS (SIMPLE)");
                                                    } else {
                                                        Menu.estructuraVacia("VENTANILLAS (SIMPLE)");
                                                    }
                                                }
                                                default -> {
                                                    //NULL
                                                }
                                            }
                                        }
                                        aux = aux.getSiguiente();
                                    }
                                } catch (Exception error) {
                                    System.out.println(error);
                                }
                            } else {
                                Menu.errorPaso();
                            }
                            System.out.println("");
                            System.out.print(">/ ");
                            String temp = dato.dato_string();
                        }
                    }
                    case 4 -> {
                        if (simulacion) {
                            Menu.reporte();
                            int opcion4 = dato.dato_int();
                            if (opcion4 > 0 && opcion4 <= 5) {
                                DobleEnlazada<ClienteAtendido> listaOrdenada = new DobleEnlazada<>();
                                switch (opcion4) {
                                    case 1 -> {
                                        Simple<Busqueda> busqueda = ordenarClienteFinal(1);
                                        boolean bandera = true;
                                        while (bandera) {
                                            bandera = false;
                                            NodoCola<Busqueda> aux = busqueda.getLista();
                                            while (aux.getSiguiente() != null) {
                                                if (aux.getDato().getCantidad() < aux.getSiguiente().getDato().getCantidad()) {
                                                    String tNombre = aux.getDato().getNombre();
                                                    int tCantidad = aux.getDato().getCantidad();
                                                    aux.getDato().setNombre(aux.getSiguiente().getDato().getNombre());
                                                    aux.getDato().setCantidad(aux.getSiguiente().getDato().getCantidad());
                                                    aux.getSiguiente().getDato().setNombre(tNombre);
                                                    aux.getSiguiente().getDato().setCantidad(tCantidad);
                                                    bandera = true;
                                                    break;
                                                }
                                                aux = aux.getSiguiente();
                                            }
                                        }
                                        Simple<Busqueda> temp = null;
                                        if (busqueda.tamanio() <= 5) {
                                            temp = busqueda;
                                        } else {
                                            temp = new Simple<>();
                                            for (int i=0; i<5; i++) {
                                                temp.push(busqueda.front());
                                                busqueda.pop();
                                            }
                                        }
                                        try {
                                            String contenido = graficarBusqueda(temp);
                                            String nombre = "TOP 5 CON MAS COLOR";
                                            crearDOT(nombre, contenido);
                                            graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                            Menu.estructuraGenerada("TOP 5 COM MAS COLOR");
                                            System.out.println("");
                                            System.out.print(">/ ");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    case 2 -> {
                                        Simple<Busqueda> busqueda = ordenarClienteFinal(2);
                                        boolean bandera = true;
                                        while (bandera) {
                                            bandera = false;
                                            NodoCola<Busqueda> aux = busqueda.getLista();
                                            while (aux.getSiguiente() != null) {
                                                if (aux.getDato().getCantidad() > aux.getSiguiente().getDato().getCantidad()) {
                                                    String tNombre = aux.getDato().getNombre();
                                                    int tCantidad = aux.getDato().getCantidad();
                                                    aux.getDato().setNombre(aux.getSiguiente().getDato().getNombre());
                                                    aux.getDato().setCantidad(aux.getSiguiente().getDato().getCantidad());
                                                    aux.getSiguiente().getDato().setNombre(tNombre);
                                                    aux.getSiguiente().getDato().setCantidad(tCantidad);
                                                    bandera = true;
                                                    break;
                                                }
                                                aux = aux.getSiguiente();
                                            }
                                        }
                                        bandera = true;
                                        while (bandera) {
                                            bandera = false;
                                            if (busqueda.front().getCantidad() == 0) {
                                                if (busqueda.tamanio() > 5) {
                                                    bandera = true;
                                                    busqueda.pop();
                                                }
                                            }
                                        }
                                        Simple<Busqueda> temp = null;
                                        if (busqueda.tamanio() <= 5) {
                                            temp = busqueda;
                                        } else {
                                            temp = new Simple<>();
                                            for (int i=0; i<5; i++) {
                                                temp.push(busqueda.front());
                                                busqueda.pop();
                                            }
                                        }
                                        try {
                                            String contenido = graficarBusqueda(temp);
                                            String nombre = "TOP 5 CON MENOS BLANCO Y NEGRO";
                                            crearDOT(nombre, contenido);
                                            graphiz(RUTA_ESCRITORIO + "\\" + nombre + ".dot", nombre);
                                            Menu.estructuraGenerada("TOP 5 COM MENOS BLANCO Y NEGRO");
                                            System.out.println("");
                                            System.out.print(">/ ");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    case 3 -> {
                                        Simple<Busqueda> busqueda = ordenarClienteFinal(3);
                                        boolean bandera = true;
                                        while (bandera) {
                                            bandera = false;
                                            NodoCola<Busqueda> aux = busqueda.getLista();
                                            while (aux.getSiguiente() != null) {
                                                if (aux.getDato().getCantidad() < aux.getSiguiente().getDato().getCantidad()) {
                                                    String tNombre = aux.getDato().getNombre();
                                                    int tCantidad = aux.getDato().getCantidad();
                                                    aux.getDato().setNombre(aux.getSiguiente().getDato().getNombre());
                                                    aux.getDato().setCantidad(aux.getSiguiente().getDato().getCantidad());
                                                    aux.getSiguiente().getDato().setNombre(tNombre);
                                                    aux.getSiguiente().getDato().setCantidad(tCantidad);
                                                    bandera = true;
                                                    break;
                                                }
                                                aux = aux.getSiguiente();
                                            }
                                        }
                                        Menu.clientePasos(busqueda.front());
                                    }
                                    case 4 -> {
                                        Menu.buscarCliente();
                                        String nombreBuscar = dato.dato_string();
                                        NodoDoble<ClienteAtendido> aux = clientesFinales.getLista();
                                        boolean bandera = true;
                                        while (aux.getSiguiente() != clientesFinales.getLista()) {
                                            if (aux.getDato().getCliente().getNombre().toLowerCase().equals(nombreBuscar.toLowerCase())) {
                                                Menu.mostrarCliente(aux.getDato());
                                                bandera = false;
                                                break;
                                            }
                                            aux = aux.getSiguiente();
                                        }
                                        if (bandera) {
                                            Menu.datoInvalido();
                                        }
                                    }
                                    default -> {
                                        //D
                                    }
                                }
                            } else {
                                Menu.errorOpciones();
                            }
                            String temp = dato.dato_string();
                        }
                    }
                    case 5 -> {
                        Menu.datosEstudiante();
                        String temp = dato.dato_string();
                    }
                    case 6 -> {
                        ventanillas = new Simple<>();
                        recepcion = new Cola<>();
                        clientesFinales = new DobleEnlazada<>();
                        cargados = new Cola<>();
                        idVentanilla = 0;
                        idCliente = 0;
                        imBw = new Impresora(TIPO.IMG_BW, ESTADO.APAGADA);
                        imColor = new Impresora(TIPO.IMG_COLOR, ESTADO.APAGADA);
                        simulacion = false;
                        datosCargados = false;
                        ventanillaCargada = false;

                        Menu.simulacionReiniciada();
                        String temp = dato.dato_string();
                    }
                    case 7 -> {
                        Menu.salidaPrograma();
                        System.exit(0);
                    }
                    default -> {
                        Menu.errorDesconocido();
                        String temp = dato.dato_string();
                    }
                }
            } else {
                Menu.errorOpciones();
                String temp = dato.dato_string();
            }
        } while (true);
    }

    private boolean asignarCliente(Cliente cliente) {
        NodoCola<Ventanilla> aux = ventanillas.getLista();
        while (aux != null) {
            if (aux != null) {
                if (aux.getDato().getTipo() == TIPO.DISPONIBLE) {
                    aux.getDato().setCliente(cliente);
                    aux.getDato().setTipo(TIPO.OCUPADA);
                    asignacionCliente(cliente.getNombre(), aux.getDato().getIdVentanilla());
                    return true;
                }
                aux = aux.getSiguiente();
            }
        }
        return false;
    }

    private void asignarId(TIPO tipo) {
        switch (tipo) {
            case IMG_BW -> {
                if (imColor.getIdImpresora() == 0) {
                    imBw.setIdImpresora(1);
                    imColor.setIdImpresora(2);
                }
            }
            case IMG_COLOR -> {
                if (imBw.getIdImpresora() == 0) {
                    imColor.setIdImpresora(1);
                    imBw.setIdImpresora(2);
                }
            }
        }
    }

    private void cargarVentanillas(int numero) {
        for (int i = 0; i < numero; i++) {
            idVentanilla++;
            ventanillas.push(new Ventanilla(idVentanilla, "Ventanilla " + String.valueOf(i + 1)));
        }
    }

    private String extensionArchivo(String ruta) {
        return ruta.replaceAll("^.*\\.(.*)$", "$1");
    }

    private String graficarVentanilla(Simple<Ventanilla> datos) {
        if (!datos.vacia()) {
            int contador = 0;
            String lista = "digraph Cola {\n";
            lista += "\trankdir=LR;\n";
            lista += "\tsize=\"8,5\";\n";
            lista += "\tresolution=100;\n";
            NodoCola<Ventanilla> aux = datos.getLista();
            while (aux != null) {
                if (aux != null) {
                    lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getNombre() + "\"]; " + contador + ";\n";
                    contador++;
                    aux = aux.getSiguiente();
                }
            }
            lista += "\tnode [shape = doublecircle label=\"NULL\"]; " + contador + ";\n";
            for (int i = 0; i < contador; i++) {
                lista += "\t" + i + " -> " + (i + 1) + ";\n";
            }
            lista += "}";
            return lista;
        }
        return null;
    }

    private String graficarRecepcion(Cola<Cliente> datos) {
        if (!datos.vacia()) {
            int contador = 0;
            String lista = "digraph Cola {\n";
            lista += "\trankdir=LR;\n";
            lista += "\tsize=\"8,5\";\n";
            lista += "\tresolution=100;\n";
            NodoCola<Cliente> aux = datos.getLista();
            while (aux != null) {
                if (aux != null) {
                    lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getNombre() + "\"]; " + contador + ";\n";
                    contador++;
                    aux = aux.getSiguiente();
                }
            }
            lista += "\tnode [shape = doublecircle label=\"NULL\"]; " + contador + ";\n";
            for (int i = 0; i < contador; i++) {
                lista += "\t" + i + " -> " + (i + 1) + ";\n";
            }
            lista += "}";
            return lista;
        }
        return null;
    }

    private String graficarBW(Cola<ImagenVentanilla> datos) {
        if (!datos.vacia()) {
            int contador = 0;
            String lista = "digraph Cola {\n";
            lista += "\trankdir=LR;\n";
            lista += "\tsize=\"8,5\";\n";
            lista += "\tresolution=100;\n";
            NodoCola<ImagenVentanilla> aux = datos.getLista();
            while (aux != null) {
                if (aux != null) {
                    lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getCliente().getNombre() + " + (" + aux.getDato().getVentanilla().getIdVentanilla() + ")\"]; " + contador + ";\n";
                    contador++;
                    aux = aux.getSiguiente();
                }
            }
            lista += "\tnode [shape = doublecircle label=\"NULL\"]; " + contador + ";\n";
            for (int i = 0; i < contador; i++) {
                lista += "\t" + i + " -> " + (i + 1) + ";\n";
            }
            lista += "}";
            return lista;
        }
        return null;
    }

    private String graficarCOLOR(Cola<ImagenVentanilla> datos) {
        if (!datos.vacia()) {
            int contador = 0;
            String lista = "digraph Cola {\n";
            lista += "\trankdir=LR;\n";
            lista += "\tsize=\"8,5\";\n";
            lista += "\tresolution=100;\n";
            NodoCola<ImagenVentanilla> aux = datos.getLista();
            while (aux != null) {
                if (aux != null) {
                    lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getCliente().getNombre() + " + (" + aux.getDato().getVentanilla().getIdVentanilla() + ")\"]; " + contador + ";\n";
                    contador++;
                    aux = aux.getSiguiente();
                }
            }
            lista += "\tnode [shape = doublecircle label=\"NULL\"]; " + contador + ";\n";
            for (int i = 0; i < contador; i++) {
                lista += "\t" + i + " -> " + (i + 1) + ";\n";
            }
            lista += "}";
            return lista;
        }
        return null;
    }

    private String graficarClienteFinal(DobleEnlazada<ClienteAtendido> datos) {
        if (!datos.vacia()) {
            int contador = 0;
            String lista = "digraph Cola {\n";
            lista += "\trankdir=LR;\n";
            lista += "\tsize=\"8,5\";\n";
            lista += "\tresolution=100;\n";
            NodoDoble<ClienteAtendido> aux = datos.getLista();
            while (aux.getSiguiente() != datos.getLista()) {
                lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getCliente().getNombre() +  "\"]; " + contador + ";\n";
                contador+=1;
                for (int i=0; i<aux.getDato().getCliente().getImagenes().tamanio(); i++) {
                    lista += "\tnode [shape = doublecircle label=\"IMG" + (i+1) + "\"]; " + contador + ";\n";
                    contador += 1;
                }
                aux = aux.getSiguiente();
            }
            lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getCliente().getNombre() +  "\"]; " + contador + ";\n";
            contador+=1;
            for (int i=0; i<aux.getDato().getCliente().getImagenes().tamanio(); i++) {
                lista += "\tnode [shape = doublecircle label=\"IMG" + (i+1) + "\"]; " + contador + ";\n";
                contador += 1;
            }
            aux = datos.getLista();
            contador = 0;
            while (aux.getSiguiente() != datos.getLista()) {
                lista += "\t" + contador + "->" + (contador + aux.getDato().getCliente().getImagenes().tamanio() + 1) + ";\n";
                contador = (contador + aux.getDato().getCliente().getImagenes().tamanio() + 1);
                aux = aux.getSiguiente();
            }
            lista += "\t" + contador + "->0;\n";
            aux = datos.getLista();
            contador = 0;
            while (aux.getSiguiente() != datos.getLista()) {
                int limite = contador + aux.getDato().getCliente().getImagenes().tamanio();
                for (int i=contador; i<limite; i++) {
                    lista += "\t{rank=\"same\";" + i + "->" + (i + 1) + "};\n";
                }
                contador = (contador + aux.getDato().getCliente().getImagenes().tamanio() + 1);
                aux = aux.getSiguiente();
            }
            int limite = contador + aux.getDato().getCliente().getImagenes().tamanio();
            for (int i=contador; i<limite; i++) {
                lista += "\t{rank=\"same\";" + i + "->" + (i + 1) + "};\n";
            }
            lista += "}";
            return lista;
        }
        return null;
    }

    private void crearDOT(String nombre, String contenido) throws IOException {
        File temp = new File(RUTA_ESCRITORIO + "\\" + nombre + ".dot");
        if (temp.exists()) {
            temp.delete();
        }
        FileWriter file = new FileWriter(RUTA_ESCRITORIO + "\\" + nombre + ".dot");
        file.write(contenido);
        file.close();
    }

    private void graphiz(String dot, String nombre) throws IOException {
        File file = new File(RUTA_ESCRITORIO + "\\" + nombre + ".png");
        if (file.exists()) {
            file.delete();
        }
        ProcessBuilder pbuilder;
        pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", RUTA_ESCRITORIO + "\\" + nombre + ".png", dot);
        pbuilder.redirectErrorStream(true);
        pbuilder.start();
    }

    private void cargarEstructura(int paso) {
        Simple<Ventanilla> ventanillaSimple = ventanillas;
        Cola<Cliente> recepcionCola = new Cola<>();
        DobleEnlazada<ClienteAtendido> clienteAtendidoDobleEnlazada = new DobleEnlazada<>();
        Cola<ImagenVentanilla> bwCola = new Cola<>();
        Cola<ImagenVentanilla> colorCola = new Cola<>();

        NodoDoble<ClienteAtendido> aux = clientesFinales.getLista();
        if (aux != null) {
            while (aux.getSiguiente() != clientesFinales.getLista()) {
                clienteAtendidoDobleEnlazada.agregar(aux.getDato());
                aux = aux.getSiguiente();
            }
        }
        NodoCola<Cliente> aux2 = recepcion.getLista();
        if (aux2 != null) {
            while (aux2 != null) {
                if (aux2 != null) {
                    recepcionCola.push(aux2.getDato());
                    aux2 = aux2.getSiguiente();
                }
            }
        }
        NodoCola<ImagenVentanilla> aux3 = imBw.getImagenes().getLista();
        if (aux3 != null) {
            while (aux3 != null) {
                if (aux3 != null) {
                    bwCola.push(aux3.getDato());
                    aux3 = aux3.getSiguiente();
                }
            }
        }
        NodoCola<ImagenVentanilla> aux4 = imColor.getImagenes().getLista();
        if (aux4 != null) {
            while (aux4 != null) {
                if (aux4 != null) {
                    colorCola.push(aux4.getDato());
                    aux4 = aux4.getSiguiente();
                }
            }
        }

        estructuras.agregar(new Estructura(paso, ventanillaSimple, ventanillaSimple.getNombre(), ""));
        estructuras.agregar(new Estructura(paso, recepcionCola, recepcionCola.getNombre(), "CLIENTE ATENDIDO"));
        estructuras.agregar(new Estructura(paso, clienteAtendidoDobleEnlazada, clienteAtendidoDobleEnlazada.getNombre(), ""));
        estructuras.agregar(new Estructura(paso, colorCola, colorCola.getNombre(), "IMAGEN VENTANILLA COLOR"));
        estructuras.agregar(new Estructura(paso, bwCola, bwCola.getNombre(), "IMAGEN VENTANILLA BW"));

    }

    private Simple<Busqueda> ordenarClienteFinal(int estado) {
        Simple<Busqueda> busqueda = new Simple<>();
        NodoDoble<ClienteAtendido> aux = clientesFinales.getLista();
        while (aux.getSiguiente() != clientesFinales.getLista()) {
            int contador = 0;
            if (estado == 3) {
                contador = aux.getDato().getPasos();
            } else {
                NodoCola<Imagen> temp = aux.getDato().getCliente().getImagenes().getLista();
                while (temp != null) {
                    if (temp != null) {
                        switch (estado) {
                            case 1 -> {
                                if (temp.getDato().getTipo() == IMG_COLOR) {
                                    contador++;
                                }
                            }
                            default -> {
                                if (temp.getDato().getTipo() == IMG_BW) {
                                    contador++;
                                }
                            }
                        }

                        temp = temp.getSiguiente();
                    }
                }
            }
            if (busqueda.tamanio() == 0) {
                busqueda.push(new Busqueda(aux.getDato().getCliente().getNombre(), contador));
            } else {
                NodoCola<Busqueda> auxBusqueda = busqueda.getLista();
                boolean bBusqueda = true;
                while (auxBusqueda != null) {
                    if (auxBusqueda != null) {
                        if (auxBusqueda.getDato().getNombre().equals(aux.getDato().getCliente().getNombre())) {
                            int cantidad = contador + auxBusqueda.getDato().getCantidad();
                            auxBusqueda.getDato().setCantidad(cantidad);
                            bBusqueda = false;
                            break;
                        }
                        auxBusqueda = auxBusqueda.getSiguiente();
                    }
                }
                if (bBusqueda) {
                    busqueda.push(new Busqueda(aux.getDato().getCliente().getNombre(), contador));
                }
            }
            aux = aux.getSiguiente();
        }
        return busqueda;
    }

    private String graficarBusqueda(Simple<Busqueda> datos) {
        if (!datos.vacia()) {
            int contador = 0;
            String lista = "digraph Cola {\n";
            lista += "\trankdir=LR;\n";
            lista += "\tsize=\"8,5\";\n";
            lista += "\tresolution=100;\n";
            NodoCola<Busqueda> aux = datos.getLista();
            while (aux != null) {
                if (aux != null) {
                    lista += "\tnode [shape = doublecircle label=\"" + aux.getDato().getNombre() + " (" + aux.getDato().getCantidad() + ")\"]; " + contador + ";\n";
                    contador++;
                    aux = aux.getSiguiente();
                }
            }
            lista += "\tnode [shape = doublecircle label=\"NULL\"]; " + contador + ";\n";
            for (int i = 0; i < contador; i++) {
                lista += "\t" + i + " -> " + (i + 1) + ";\n";
            }
            lista += "}";
            return lista;
        }
        return null;
    }
}

package rodrigo.garcia.bin;

import rodrigo.garcia.estructura.lista.Pila;

import java.util.Objects;

public class Ventanilla {
    private int idVentanilla;
    private String nombre;
    private Cliente cliente;
    private Pila<ImagenVentanilla> imagenes;
    private int numeroImagenes;
    private Pila<ImagenVentanilla> paginas;
    private int numeroPaginas;
    private int pasos;
    private TIPO tipo;

    public Ventanilla(int idVentanilla, String nombre) {
        this.idVentanilla = idVentanilla;
        this.nombre = nombre;
        imagenes = new Pila<>();
        paginas = new Pila<>();
        pasos = 0;
        numeroImagenes = 0;
        numeroPaginas = 0;
        cliente = null;
        tipo = TIPO.DISPONIBLE;
    }

    public void setNumeroImagenes(int numeroImagenes) {
        this.numeroImagenes = numeroImagenes;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void numeroImagenes() {
        numeroImagenes++;
    }

    public void numeroPaginas() {
        numeroPaginas++;
    }

    public int getNumeroImagenes() {
        return numeroImagenes;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public int getPasos() {
        return pasos;
    }

    public int getIdVentanilla() {
        return idVentanilla;
    }

    public String getNombre() {
        return nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pila<ImagenVentanilla> getImagenes() {
        return imagenes;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarImagen(ImagenVentanilla imagen) {
        imagenes.push(imagen);
    }

    public void agregarPagina(ImagenVentanilla imagen) {
        paginas.push(imagen);
    }

        public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public Pila<ImagenVentanilla> getPaginas() {
        return paginas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ventanilla)) return false;
        Ventanilla that = (Ventanilla) o;
        return idVentanilla == that.idVentanilla && pasos == that.pasos && Objects.equals(nombre, that.nombre) && Objects.equals(cliente, that.cliente) && Objects.equals(imagenes, that.imagenes) && Objects.equals(paginas, that.paginas) && tipo == that.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVentanilla, nombre, cliente, imagenes, paginas, pasos, tipo);
    }
}

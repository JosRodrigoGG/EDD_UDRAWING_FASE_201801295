package rodrigo.garcia.bin;

import rodrigo.garcia.estructura.lista.Simple;

import java.util.Objects;

public class Cliente {
    private int idCliente;
    private String nombre;
    private Simple<Imagen> imagenes;

    public Cliente(int idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        imagenes = new Simple<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Simple<Imagen> getImagenes() {
        return imagenes;
    }

    public void agregarImagen(Imagen imagen) {
        imagenes.push(imagen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return idCliente == cliente.idCliente && Objects.equals(nombre, cliente.nombre) && Objects.equals(imagenes, cliente.imagenes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, imagenes);
    }
}

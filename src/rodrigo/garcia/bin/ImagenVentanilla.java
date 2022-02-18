package rodrigo.garcia.bin;

import java.util.Objects;

public class ImagenVentanilla {
    private Ventanilla ventanilla;
    private Cliente cliente;
    private Imagen imagen;

    public ImagenVentanilla(Ventanilla ventanilla, Cliente cliente, Imagen imagen) {
        this.ventanilla = ventanilla;
        this.cliente = cliente;
        this.imagen = imagen;
    }

    public Ventanilla getVentanilla() {
        return ventanilla;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Imagen getImagen() {
        return imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagenVentanilla)) return false;
        ImagenVentanilla that = (ImagenVentanilla) o;
        return Objects.equals(ventanilla, that.ventanilla) && Objects.equals(cliente, that.cliente) && Objects.equals(imagen, that.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ventanilla, cliente, imagen);
    }
}

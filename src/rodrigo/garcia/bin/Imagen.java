package rodrigo.garcia.bin;

import java.util.Objects;

public class Imagen {
    private TIPO tipo;
    private int id;
    private ESTADO estado;

    public Imagen(TIPO tipo, int id) {
        this.tipo = tipo;
        this.id = id;
        estado = ESTADO.PENDIENTE;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagen)) return false;
        Imagen imagen = (Imagen) o;
        return id == imagen.id && tipo == imagen.tipo && estado == imagen.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, id, estado);
    }
}

package rodrigo.garcia.estructura.nodo;

import java.util.Objects;

public class NodoCola<T> {
    private T dato;
    private NodoCola<T> siguiente;

    public NodoCola() {
        dato = null;
        siguiente = null;
    }

    public NodoCola(T dato) {
        this.dato = dato;
        siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoCola<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodoCola)) return false;
        NodoCola<?> nodoCola = (NodoCola<?>) o;
        return Objects.equals(dato, nodoCola.dato) && Objects.equals(siguiente, nodoCola.siguiente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dato, siguiente);
    }
}

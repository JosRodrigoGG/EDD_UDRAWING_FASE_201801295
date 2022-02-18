package rodrigo.garcia.estructura.nodo;

import java.util.Objects;

public class NodoDoble<T> {
    private NodoDoble<T> anterior;
    private T dato;
    private NodoDoble<T> siguiente;

    public NodoDoble(T dato) {
        anterior = null;
        this.dato = dato;
        siguiente = null;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodoDoble)) return false;
        NodoDoble<?> nodoDoble = (NodoDoble<?>) o;
        return Objects.equals(anterior, nodoDoble.anterior) && Objects.equals(dato, nodoDoble.dato) && Objects.equals(siguiente, nodoDoble.siguiente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anterior, dato, siguiente);
    }
}

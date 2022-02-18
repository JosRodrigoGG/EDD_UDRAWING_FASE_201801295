package rodrigo.garcia.estructura.lista;

import rodrigo.garcia.estructura.nodo.NodoCola;

import java.util.Objects;

public class Cola<T>{
    protected NodoCola<T> lista;
    protected int contador;
    private final String nombre = "COLA";

    public Cola() {
        lista = null;
        contador = 0;
    }

    public void push(T dato) {
        if (dato != null) {
            if (lista == null) {
                lista = new NodoCola<>(dato);
            } else {
                NodoCola<T> aux = lista;
                while (aux.getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(new NodoCola<>(dato));
            }
            contador++;
        }
    }

    public boolean vacia() {
        return lista == null;
    }

    public void pop() {
        if (lista != null) {
            if (lista.getSiguiente() != null) {
                lista = lista.getSiguiente();
                contador--;
            } else {
                lista = null;
                contador = 0;
            }
        }
    }

    public T front() {
        if (lista != null) {
            return lista.getDato();
        } else {
            return null;
        }
    }

    public NodoCola<T> getLista() {
        return lista;
    }

    public int tamanio() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cola)) return false;
        Cola<?> cola = (Cola<?>) o;
        return contador == cola.contador && Objects.equals(lista, cola.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lista, contador);
    }
}

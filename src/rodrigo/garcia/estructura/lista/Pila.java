package rodrigo.garcia.estructura.lista;

import rodrigo.garcia.estructura.nodo.NodoCola;

import java.util.Objects;

public class Pila<T> {
    private NodoCola<T> lista;
    private int contador;
    private final String nombre = "PILA";

    public Pila() {
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

    public void pop() {
        if (lista != null) {
            if (lista.getSiguiente() != null) {
                NodoCola<T> aux = lista;
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
            } else {
                lista = null;
                contador = 0;
            }
        }
    }

    public T font() {
        if (lista != null) {
            NodoCola<T> aux = lista;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            return aux.getDato();
        } else {
            return null;
        }
    }

    public boolean vacia() {
        return lista == null;
    }

    public NodoCola<T> getLista() {
        return lista;
    }

    public String getNombre() {
        return nombre;
    }

    public int tamanio() {
        return contador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pila)) return false;
        Pila<?> pila = (Pila<?>) o;
        return contador == pila.contador && Objects.equals(lista, pila.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lista, contador);
    }
}

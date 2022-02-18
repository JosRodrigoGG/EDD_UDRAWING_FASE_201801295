package rodrigo.garcia.estructura.lista;

import rodrigo.garcia.estructura.nodo.NodoDoble;

import java.util.Objects;

public class DobleEnlazada<T> {
    private NodoDoble<T> lista;
    private int contador;
    private final String nombre = "DOBLE_ENLAZADA";

    public DobleEnlazada() {
        lista = null;
        contador = 0;
    }

    public void agregar(T dato) {
        if (dato != null) {
            if (lista == null) {
                lista = new NodoDoble<>(dato);
                lista.setSiguiente(lista);
                lista.setAnterior(lista);
            } else {
                NodoDoble<T> aux = lista;
                while (aux.getSiguiente() != lista) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(new NodoDoble<>(dato));
                aux.getSiguiente().setAnterior(aux);
                aux.getSiguiente().setSiguiente(lista);
            }
            contador++;
        }
    }

    public void eliminar(T dato) {
        if (dato != null && lista != null) {
            if (lista.getSiguiente().equals(lista)) {
                if (lista.getDato().equals(dato)) {
                    lista = null;
                    contador = 0;
                }
            } else {
                NodoDoble<T> aux = lista;
                while (aux.getSiguiente() != lista) {
                    if (aux.getDato().equals(dato)) {
                        NodoDoble<T> tempAnterior = aux.getAnterior();
                        NodoDoble<T> tempSiguiente = aux.getSiguiente();
                        tempAnterior.setSiguiente(tempSiguiente);
                        tempSiguiente.setAnterior(tempAnterior);
                        contador--;
                        break;
                    }
                    aux = aux.getSiguiente();
                }
                if (aux.getDato().equals(dato)) {
                    NodoDoble<T> tempAnterior = aux.getAnterior();
                    NodoDoble<T> tempSiguiente = aux.getSiguiente();
                    tempAnterior.setSiguiente(tempSiguiente);
                    tempSiguiente.setAnterior(tempAnterior);
                    contador--;
                }
            }
        }
    }

    public boolean vacia() {
        return lista == null;
    }

    public NodoDoble<T> getLista() {
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
        if (!(o instanceof DobleEnlazada)) return false;
        DobleEnlazada<?> that = (DobleEnlazada<?>) o;
        return contador == that.contador && Objects.equals(lista, that.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lista, contador);
    }
}

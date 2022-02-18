package rodrigo.garcia.estructura.lista;

import rodrigo.garcia.estructura.nodo.NodoCola;

public class Simple<T> extends Cola<T> {
    private final String nombre = "SIMPLE";

    @Override
    public String getNombre() {
        return nombre;
    }

    public void eliminar(T dato) {
        if (dato != null) {
            if (this.lista != null) {
                if (this.lista.getSiguiente() == null) {
                    if (this.lista.getDato().equals(dato)) {
                        this.lista = null;
                        this.contador = 0;
                    }
                } else {
                    if (this.lista.getDato().equals(dato)) {
                        this.lista = this.lista.getSiguiente();
                        this.contador--;
                    } else {
                        NodoCola<T> aux = this.lista;
                        while (aux.getSiguiente().getSiguiente() != null) {
                            if (aux.getSiguiente().getDato().equals(dato)) {
                                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                                contador--;
                                break;
                            }
                            aux = aux.getSiguiente();
                        }
                        if (aux.getSiguiente() != null) {
                            if (aux.getSiguiente().getDato().equals(dato)) {
                                aux.setSiguiente(null);
                                contador--;
                            }
                        }
                    }
                }
            }
        }
    }
}

package rodrigo.garcia.bin;

public class Busqueda {
    private String nombre;
    private int cantidad;

    public Busqueda(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Busqueda() {
        nombre = null;
        cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

package rodrigo.garcia.bin;

public class Estructura {
    private int paso;
    private Object estructura;
    private String nombre;
    private String objeto;

    public Estructura(int paso, Object estructura, String nombre, String objeto) {
        this.paso = paso;
        this.estructura = estructura;
        this.nombre = nombre;
        this.objeto = objeto;
    }

    public int getPaso() {
        return paso;
    }

    public Object getEstructura() {
        return estructura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }
}

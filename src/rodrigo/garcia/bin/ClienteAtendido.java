package rodrigo.garcia.bin;

import java.util.Objects;

public class ClienteAtendido {
    private Cliente cliente;
    private int pasos;
    private int ventanilla;

    public ClienteAtendido(Cliente cliente, int pasos, int ventanilla) {
        this.cliente = cliente;
        this.pasos = pasos;
        this.ventanilla = ventanilla;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVentanilla() {
        return ventanilla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteAtendido)) return false;
        ClienteAtendido that = (ClienteAtendido) o;
        return pasos == that.pasos && ventanilla == that.ventanilla && Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, pasos, ventanilla);
    }
}

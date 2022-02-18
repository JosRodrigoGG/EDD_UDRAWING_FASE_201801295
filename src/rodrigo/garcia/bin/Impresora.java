package rodrigo.garcia.bin;

import rodrigo.garcia.estructura.lista.Cola;

import java.util.Objects;

public class Impresora {
    private int idImpresora;
    private TIPO tipo;
    private ESTADO estado;
    private Cola<ImagenVentanilla> imagenes;
    private Cola<ImagenVentanilla> paginas;

    public Impresora(TIPO tipo, ESTADO estado) {
        this.idImpresora = 0;
        this.tipo = tipo;
        this.estado = estado;
        imagenes = new Cola<>();
        paginas = new Cola<>();
    }

    public void setIdImpresora(int idImpresora) {
        this.idImpresora = idImpresora;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    public int getIdImpresora() {
        return idImpresora;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public Cola<ImagenVentanilla> getImagenes() {
        return imagenes;
    }

    public void agregarImagen(ImagenVentanilla imagen){
        imagenes.push(imagen);
    }

    public void agregarPaginas(ImagenVentanilla imagen) {
        paginas.push(imagen);
    }

    public Cola<ImagenVentanilla> getPaginas() {
        return paginas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Impresora)) return false;
        Impresora impresora = (Impresora) o;
        return idImpresora == impresora.idImpresora && tipo == impresora.tipo && estado == impresora.estado && Objects.equals(imagenes, impresora.imagenes) && Objects.equals(paginas, impresora.paginas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImpresora, tipo, estado, imagenes, paginas);
    }
}

package warpature;

import java.awt.image.BufferedImage;
import warpature.adaptadores.AdaptadorRaton;
import warpature.procesadores.Procesador;
import warpature.procesadores.warpeadores.Warpeador;

/**
 * Clase central del programa que encapsula todas las demas.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public class Warpature {
    private BufferedImage imagen;
    private AdaptadorRaton raton;
    private Procesador procesador;

    /**
     * @return imagen que se esta procesando.
     */
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * @param imagen que se esta procesando.
     */
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    /**
     * @return raton que observa los eventos del mouse.
     */
    public AdaptadorRaton getRaton() {
        return raton;
    }

    /**
     * @param raton que observa los eventos del mouse.
     */
    public void setRaton(AdaptadorRaton raton) {
        this.raton = raton;
    }

    /**
     * @return procesador que modifica la imagen.
     */
    public Procesador getProcesador() {
        return procesador;
    }

    /**
     * @param procesador que modifica la imagen.
     */
    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    /**
     * Constructor de Warpature.
     * Instancia nuevo procesador y raton.
     */
    public Warpature(){
        setRaton(new AdaptadorRaton(this));
        setProcesador(new Warpeador(this));
    }

    /**
     * Para setear una imagen nueva.
     * Le avisa al procesador que la imagen es otra.
     * @param imagen nueva.
     */
    public void setNuevaImagen(BufferedImage imagen) {
        this.imagen = imagen;
        getProcesador().setImagenEscalada(imagen);
    }

    /**
     * Para obtener la imagen a ser guardada.
     * Obtiene del procesador la imagen con todos los cambios.
     * @return imagen con todos los cambios.
     */
    public BufferedImage getImagenFinal(){
        setImagen(getProcesador().getImagenEscalada());
        return getImagen();
    }
}
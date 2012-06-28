package warpature.procesadores;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import warpature.Warpature;

/**
 * Clase abstracta de la que se debe extender para poder crear procesadores para la aplicacion.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public abstract class Procesador extends Observable{
    public static final int ANCHO=640;
    public static final int ALTO=480;

    private Warpature warpature;
    private BufferedImage imagen;
    private BufferedImage imagenOriginal;
    private int dx;
    private int dy;
    private double radio;

    /**
     * @return warpature a la que pertenece.
     */
    public Warpature getWarpature() {
        return warpature;
    }

    /**
     * @param warpature a la que pertenece.
     */
    public void setWarpature(Warpature warpature) {
        this.warpature = warpature;
    }

    /**
     * @return imagen procesada.
     */
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * @param imagen procesada.
     */
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    /**
     * @return imagenOriginal a ser procesada.
     */
    public BufferedImage getImagenOriginal() {
        return imagenOriginal;
    }

    /**
     * @param imagenOriginal a ser procesada.
     */
    public void setImagenOriginal(BufferedImage imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }

    /**
     * @return dx diferencia en x entre la imagen y el panel.
     */
    public int getDx() {
        return dx;
    }

    /**
     * @param dx diferencia en x entre la imagen y el panel.
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * @return dy diferencia en y entre la imagen y el panel.
     */
    public int getDy() {
        return dy;
    }

    /**
     * @param dy diferencia en y entre la imagen y el panel.
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * @return radio del procesador.
     */
    public double getRadio() {
        return radio;
    }

    /**
     * @param radio del procesador.
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * Constructor de Procesador.
     * Setea el radio por defecto en 10.
     * @param warpature a la que pertenece..
     */
    public Procesador(Warpature warpature){
        setWarpature(warpature);
        setRadio(10.0);
    }

    /**
     * Setea una nueva imagen escalada para que quepa en el label.
     * @param imagen de tamaño real.
     */
    public void setImagenEscalada(BufferedImage imagen){
        double sc1=((double)ALTO)/((double)imagen.getHeight());
        double sc2=((double)ANCHO)/((double)imagen.getWidth());
        double sc=(sc1>sc2)?sc2:sc1;
        BufferedImage bi=new BufferedImage((int)(((double)imagen.getWidth())*sc),(int)(((double)imagen.getHeight())*sc),BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(imagen.getScaledInstance((int)(((double)imagen.getWidth())*sc),(int)(((double)imagen.getHeight())*sc),Image.SCALE_SMOOTH),0,0,null);
        setImagen(bi);
        setImagenOriginal(new BufferedImage(bi.getColorModel(),bi.copyData(null),bi.isAlphaPremultiplied(),null));
        setDx(ANCHO-getImagen().getWidth());
        setDy(ALTO-getImagen().getHeight());
    }

    /**
     * Obtiene la imagen del tamaño real.
     * @return imagen del tamaño real.
     */
    public BufferedImage getImagenEscalada(){
        BufferedImage bi=new BufferedImage(getWarpature().getImagen().getWidth(),getWarpature().getImagen().getHeight(),BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(getImagen().getScaledInstance(getWarpature().getImagen().getWidth(),getWarpature().getImagen().getHeight(),Image.SCALE_SMOOTH),0,0,null);
        return bi;
    }

    /**
     * Metodo abstracto.
     * Vuelve un paso en el procesamiento.
     */
    abstract public void volver();

    /**
     * Metodo abstracto.
     * Procesa la imagen.
     * @param xi coordenada x inicial.
     * @param yi coordenada y inicial.
     * @param xf coordenada x final.
     * @param yf coordenada y final.
     */
    abstract public void procesar(int xi,int yi,int xf,int yf);
}
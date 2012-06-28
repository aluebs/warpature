package warpature.adaptadores;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import warpature.Warpature;

/**
 * Clase que observa los eventos del mouse.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public class AdaptadorRaton extends MouseAdapter{
    private Warpature warpature;
    private int xi;
    private int yi;
    private int xf;
    private int yf;
    private boolean valido;

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
     * @return xi coordenada x inicial.
     */
    public int getXi() {
        return xi;
    }

    /**
     * @param xi coordenada x inicial.
     */
    public void setXi(int xi) {
        this.xi = xi;
    }

    /**
     * @return yi coordenada y inicial.
     */
    public int getYi() {
        return yi;
    }

    /**
     * @param yi coordenada y inicial.
     */
    public void setYi(int yi) {
        this.yi = yi;
    }

    /**
     * @return xf coordenada x final.
     */
    public int getXf() {
        return xf;
    }

    /**
     * @param xf coordenada x final.
     */
    public void setXf(int xf) {
        this.xf = xf;
    }

    /**
     * @return yf coordenada y final.
     */
    public int getYf() {
        return yf;
    }

    /**
     * @param yf coordenada y final.
     */
    public void setYf(int yf) {
        this.yf = yf;
    }

    /**
     * @return valido si las coordenadas son correctas.
     */
    public boolean isValido() {
        return valido;
    }

    /**
     * @param valido si las coordenadas son correctas
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }

    /**
     * Constructor de AdaptadorRaton.
     * @param warpature a la que pertenece.
     */
    public AdaptadorRaton(Warpature warpature){
        setWarpature(warpature);
        setValido(false);
    }

    /**
     * Se ejecuta si se presiona el mouse.
     * Guarda las coordenadas iniciales y lo marca como valido.
     * @param e evento del mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        setXi(e.getX());
        setYi(e.getY());
        setValido(true);
    }

    /**
     * Se ejecuta si se suelta el mouse.
     * Guarda las coordenadas iniciales, lo marca como invalido y le envia las coordenadas al procesador.
     * @param e evento del mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(isValido()){
            setXf(e.getX());
            setYf(e.getY());
            setValido(false);
            try{
                getWarpature().getProcesador().procesar(getXi()-getWarpature().getProcesador().getDx()/2, getYi()-getWarpature().getProcesador().getDy()/2, getXf()-getWarpature().getProcesador().getDx()/2, getYf()-getWarpature().getProcesador().getDy()/2);
            }catch(Exception ex){
            }
        }
    }
}
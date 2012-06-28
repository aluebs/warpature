package warpature.procesadores.warpeadores;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import warpature.procesadores.Procesador;
import warpature.Warpature;
import warpature.procesadores.warpeadores.matrices.Matrix;

/**
 * Clase que warpea la imagen.
 * Extiene de Procesador.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public class Warpeador extends Procesador{
    private LinkedList<Matrix> matrices;

    /**
     * @return matrices de warpeo de todos los pasos del procesamiento.
     */
    public LinkedList<Matrix> getMatrices() {
        return matrices;
    }

    /**
     * @param matrices de warpeo de todos los pasos del procesamiento.
     */
    public void setMatrices(LinkedList<Matrix> matrices) {
        this.matrices = matrices;
    }

    /**
     * Constructor de Warpeador.
     * Crea un arreglo de matrices vacio.
     * @param warpature a la que pertenece.
     */
    public Warpeador(Warpature warpature){
        super(warpature);
        setMatrices(new LinkedList<Matrix>());
    }

    @Override
    public void setImagenEscalada(BufferedImage imagen){
        super.setImagenEscalada(imagen);
        getMatrices().clear();
        Matrix m=new Matrix(getImagen().getTileWidth(),getImagen().getHeight());
        m.init();
        getMatrices().addFirst(m);
    }

    @Override
    public void volver(){
        if(getMatrices().size()>1){
            getMatrices().removeLast();
            Matrix m=getMatrices().getLast();
            int xint;
            int yint;
            double px;
            double py;
            for(int i=0;i<getImagen().getWidth();i++){
                for(int j=0;j<getImagen().getHeight();j++){
                    xint=(int)m.getX(i,j);
                    yint=(int)m.getY(i,j);
                    px=m.getX(i,j)-((double)xint);
                    py=m.getY(i,j)-((double)yint);
                    int rgb=0;
                    try{
                        rgb=((((int)((1.0-px)*(1.0-py)*((double)((getImagenOriginal().getRGB(xint,yint)>>0)&0xFF))+px*(1.0-py)*((double)((getImagenOriginal().getRGB(xint+1,yint)>>0)&0xFF))+(1.0-px)*py*((double)((getImagenOriginal().getRGB(xint,yint+1)>>0)&0xFF))+px*py*((double)((getImagenOriginal().getRGB(xint+1,yint+1)>>0)&0xFF))))&0xFF)<<0)|((((int)((1.0-px)*(1.0-py)*((double)((getImagenOriginal().getRGB(xint,yint)>>8)&0xFF))+px*(1.0-py)*((double)((getImagenOriginal().getRGB(xint+1,yint)>>8)&0xFF))+(1.0-px)*py*((double)((getImagenOriginal().getRGB(xint,yint+1)>>8)&0xFF))+px*py*((double)((getImagenOriginal().getRGB(xint+1,yint+1)>>8)&0xFF))))&0xFF)<<8)|((((int)((1.0-px)*(1.0-py)*((double)((getImagenOriginal().getRGB(xint,yint)>>16)&0xFF))+px*(1.0-py)*((double)((getImagenOriginal().getRGB(xint+1,yint)>>16)&0xFF))+(1.0-px)*py*((double)((getImagenOriginal().getRGB(xint,yint+1)>>16)&0xFF))+px*py*((double)((getImagenOriginal().getRGB(xint+1,yint+1)>>16)&0xFF))))&0xFF)<<16)|0xFF000000;
                    }catch(Exception ex){
                    }
                    getImagen().setRGB(i,j,rgb);
                }
            }
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public synchronized void procesar(int xi,int yi,int xf,int yf){
        Matrix ma=getMatrices().getLast();
        Matrix mb=new Matrix(getImagen().getWidth(),getImagen().getHeight());
        double peso;
        double xd;
        double yd;
        int xint;
        int yint;
        double px;
        double py;
        double d=Math.sqrt(Math.pow((double)(xi-xf),2.0)+Math.pow((double)(yi-yf),2.0));
        for(int i=0;i<getImagen().getWidth();i++){
            for(int j=0;j<getImagen().getHeight();j++){
                peso=Math.exp(-((4.0*getRadio()+d)*Math.sqrt(Math.pow((double)(i-xf),2.0)+Math.pow((double)(j-yf),2.0))-((double)(i-xf))*((double)(xi-xf))-((double)(j-yf))*((double)(yi-yf)))/(2.0*getRadio()*(d+2.0*getRadio())));
                xd=ma.getX(i,j)+peso*(ma.getX(xi,yi)-ma.getX(xf,yf));
                yd=ma.getY(i,j)+peso*(ma.getY(xi,yi)-ma.getY(xf,yf));
                xd=(xd<0.0)?0.0:((xd>=((double)(getImagen().getWidth()-1)))?(((double)(getImagen().getWidth()-1))-0.000001):xd);
                yd=(yd<0.0)?0.0:((yd>=((double)(getImagen().getHeight()-1)))?(((double)(getImagen().getHeight()-1))-0.000001):yd);
                mb.setX(i,j,xd);
                mb.setY(i,j,yd);
                xint=(int)xd;
                yint=(int)yd;
                px=xd-((double)xint);
                py=yd-((double)yint);
                int rgb=0;
                try{
                    rgb=((((int)((1.0-px)*(1.0-py)*((double)((getImagenOriginal().getRGB(xint,yint)>>0)&0xFF))+px*(1.0-py)*((double)((getImagenOriginal().getRGB(xint+1,yint)>>0)&0xFF))+(1.0-px)*py*((double)((getImagenOriginal().getRGB(xint,yint+1)>>0)&0xFF))+px*py*((double)((getImagenOriginal().getRGB(xint+1,yint+1)>>0)&0xFF))))&0xFF)<<0)|((((int)((1.0-px)*(1.0-py)*((double)((getImagenOriginal().getRGB(xint,yint)>>8)&0xFF))+px*(1.0-py)*((double)((getImagenOriginal().getRGB(xint+1,yint)>>8)&0xFF))+(1.0-px)*py*((double)((getImagenOriginal().getRGB(xint,yint+1)>>8)&0xFF))+px*py*((double)((getImagenOriginal().getRGB(xint+1,yint+1)>>8)&0xFF))))&0xFF)<<8)|((((int)((1.0-px)*(1.0-py)*((double)((getImagenOriginal().getRGB(xint,yint)>>16)&0xFF))+px*(1.0-py)*((double)((getImagenOriginal().getRGB(xint+1,yint)>>16)&0xFF))+(1.0-px)*py*((double)((getImagenOriginal().getRGB(xint,yint+1)>>16)&0xFF))+px*py*((double)((getImagenOriginal().getRGB(xint+1,yint+1)>>16)&0xFF))))&0xFF)<<16)|0xFF000000;
                }catch(Exception ex){
                }
                getImagen().setRGB(i,j,rgb);
            }
        }
        getMatrices().addLast(mb);
        if(getMatrices().size()>10){
            getMatrices().removeFirst();
        }
        setChanged();
        notifyObservers();
    }
}
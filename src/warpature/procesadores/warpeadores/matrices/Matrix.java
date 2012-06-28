package warpature.procesadores.warpeadores.matrices;

/**
 * Clase auxiliar para Warpeador.
 * Encapsula una matriz de 3 dimensiones.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public class Matrix {
    private double[][][] data;

    /**
     * Constructor de Matrix.
     * Crea una nueva matriz.
     * @param x cantidad de filas.
     * @param y cantidad de columnas.
     */
    public Matrix(int x,int y){
        data=new double[x][y][2];
    }

    /**
     * Inicializa la matriz con los valores de la fila y la columna de cada celda.
     */
    public void init(){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[0].length;j++){
                data[i][j][0]=(double)i;
                data[i][j][1]=(double)j;
            }
        }
    }

    /**
     * @param i fila.
     * @param j columna.
     * @return d posicion en x.
     */
    public double getX(int i,int j){
        return data[i][j][0];
    }

    /**
     * @param i fila.
     * @param j columna.
     * @return d posicion en y.
     */
    public double getY(int i,int j){
        return data[i][j][1];
    }

    /**
     * @param i fila.
     * @param j columna.
     * @param d posicion en x.
     */
    public void setX(int i,int j,double d){
        data[i][j][0]=d;
    }

    /**
     * @param i fila.
     * @param j columna.
     * @param d posicion en y.
     */
    public void setY(int i,int j,double d){
        data[i][j][1]=d;
    }
}
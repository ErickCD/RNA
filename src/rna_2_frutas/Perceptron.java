package rna_2_frutas;

public class Perceptron {

    private double pesos[];
    private double metas[];
    private double entradas[][];
    private int numEntradas;
    private static final double TASA_APRENDIZAJE = 0.5D;

    public void setPesos(double[] pesos) {
        this.pesos = pesos;
    }

    public void setMetas(double[] metas) {
        this.metas = metas;
    }

    public void setEntradas(double[][] entradas) {
        this.entradas = entradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public double[] getPesos() {
        return pesos;
    }

    public double[] getMetas() {
        return metas;
    }

    public double[][] getEntradas() {
        return entradas;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public static double getTASA_APRENDIZAJE() {
        return TASA_APRENDIZAJE;
    }

    public void inicializarPesos() {
        int indice = 0;
        pesos = new double[getNumEntradas()];

        pesos[0] = 0.5;
        pesos[1] = -1;
        pesos[2] = -0.5;
        pesos[3] = 0.5;

    }

    //suma de los pesos snapticos
    public double sumaPodenrada(int indice) {
        double suma = 0;
        for (int i = 0; i < getNumEntradas(); i++) {
            suma += (pesos[i] * entradas[indice][i]);
        }
        return suma;
    }

    public double funcionActivacion(double suma) {
        return (suma > 0 ? 1D : 0D);
    }
    //actualizacion de los pesos sinapticos

    public void actualizarPesos(int posicionEntrada, double y) {
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = pesos[i] + TASA_APRENDIZAJE * (metas[posicionEntrada] - y) * entradas[posicionEntrada][i];
        }
    }
    //metodo para el entrenamiento

    public String entrenarRNA() {
        String answer = "";
        int indice = 0;
        double yi = 0;
        while (indice < entradas.length) {
            double suma = 0;
            //metodo suma ponderada
            for (int i = 0; i < numEntradas; i++) {
                suma += (pesos[i] * entradas[indice][i]);
            }
            //metodo funcion signo
            yi = (suma > 0 ? 1 : -1);
            if (yi == metas[indice]) {
                //System.out.println("corecto");
                answer = answer + "Corecto \n";
            } else {
                //System.out.println("incorrecto");
                answer = answer + "Incorrecto \n";
                actualizarPesos(indice, yi);
                indice = -1;
            }
            indice++;
        }
        return answer;
    }
    //metodo para probar la RNA

    public double probarPerceptron(double x1, double x2, double x3, double bias) {
        return funcionActivacion((pesos[0] * x1 + pesos[0] * x2 + pesos[0] * x3 + pesos[2] * bias));

    }
    //imprime los pesos sinpticos

    public String imprimePesos() {
        String answer = "";
        for (int i = 0; i < numEntradas; i++) {
            answer = answer + ("W[" + i + "] = " + pesos[i] + "\n");
            //System.out.println("W["+i+"] = "+pesos[i]);
        }
        return answer;
    }

}

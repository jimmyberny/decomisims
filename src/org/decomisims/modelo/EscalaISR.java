/*
 */
package org.decomisims.modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class EscalaISR {
    
    public static final EscalaISR ESCALA = new EscalaISR();
    static {
        ESCALA.add(new RangoISR(1d, 496.07, 0d, 0.0192));
        ESCALA.add(new RangoISR(496.08, 4210.41, 9.52, 0.0640));
        ESCALA.add(new RangoISR(4210.42, 7399.42, 247.24, 0.1088));
        ESCALA.add(new RangoISR(7399.43, 8601.50, 594.21, 0.16d));
        ESCALA.add(new RangoISR(8601.51, 10298.35, 786.54, 0.1792));
        ESCALA.add(new RangoISR(10298.36, 20770.29, 1090.61, 0.2146));
        ESCALA.add(new RangoISR(20770.30, 32736.83, 3327.42, 0.2352));
        ESCALA.add(new RangoISR(32736.84, 62500d, 6141.42, 0.30d));
        ESCALA.add(new RangoISR(62500.01, 83333.33, 15070.90, 0.32d));
        ESCALA.add(new RangoISR(83333.34, 250000d, 21737.57, 0.34d));
        ESCALA.add(new RangoISR(250000.01, Double.MAX_VALUE, 78404.23, 0.35d));
        
    }
    
    private List<RangoISR> rangos;

    public EscalaISR() {
        rangos = new LinkedList<>();
    }
    
    private void add(RangoISR rango) {
        rangos.add(rango);
    }
    
    public int getNumRangos() {
        return rangos.size();
    }
    
    public RangoISR getRango(int i) {
        return rangos.get(i);
    }
    
    public Resultado evaluar(Double cantidad) {
        Resultado res = null;
        int i = 0;
        do  {
            RangoISR tmp = rangos.get(i);
            if (tmp.dentro(cantidad)) {
                res = new Resultado(tmp, cantidad);
            }
            i++;
        } while (res == null || i > rangos.size());
        return res;
    }
    
    public static class Resultado {
        private Double cantidad;
        private RangoISR rango;

        public Resultado(RangoISR rango, Double cantidad) {
            this.rango = rango;
            this.cantidad = cantidad;
        }
        
        public Double getCuotaFija() {
            return rango.getCuota();
        }
        
        public Double getPorcentajeExcedente() {
            return rango.getExcedente();
        }
        
        public Double getExcedente() {
            return cantidad - rango.getMin();
        }
        
        public Double getExcedenteRelativo() {
            return getExcedente() * rango.getExcedente();
        }
        
        public Double getTotal() {
            return getCuotaFija() + getExcedenteRelativo();
        }
    }
}

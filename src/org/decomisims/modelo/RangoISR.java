/*
 */
package org.decomisims.modelo;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class RangoISR {

    private Double min;
    private Double max;
    private Double cuota;
    private Double excedente;

    public RangoISR() {
    }

    public RangoISR(Double min, Double max, Double cuota, Double excedente) {
        this.min = min;
        this.max = max;
        this.cuota = cuota;
        this.excedente = excedente;
    }
    

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
    
    

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public Double getExcedente() {
        return excedente;
    }

    public void setExcedente(Double excedente) {
        this.excedente = excedente;
    }

    public boolean dentro(Double cantidad) {
        return cantidad.compareTo(min) >= 0
                && cantidad.compareTo(max) <= 0;
    }
}

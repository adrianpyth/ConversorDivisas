package com.aluracursos.conversorchallenge.modelos;

public class Moneda {

    private final String codigo;
    private final double tasa;

    public Moneda(String codigo, double tasa) {
        this.codigo = codigo;
        this.tasa = tasa;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getTasa() {
        return tasa;
    }

    public double convertir(double monto, Moneda destino) {
        return monto * (destino.getTasa() / this.tasa);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "codigo='" + codigo + '\'' +
                ", tasa=" + tasa +
                '}';
    }
}

package com.aluracursos.conversorchallenge.modelos;

/**
 * Representa una tasa de cambio entre dos monedas.
 * Permite realizar conversiones basadas en esa tasa.
 */
public class TasaCambio {

    private final Moneda monedaOrigen;
    private final Moneda monedaDestino;
    private final double tasa;

    /**
     * Constructor que recibe las monedas de origen y destino, y la tasa de cambio.
     *
     * @param origen   Moneda de origen (ej. USD)
     * @param destino  Moneda de destino (ej. EUR)
     * @param tasa     Tasa de cambio (ej. 0.89)
     */
    public TasaCambio(Moneda origen, Moneda destino, double tasa) {
        this.monedaOrigen = origen;
        this.monedaDestino = destino;
        this.tasa = tasa;
    }

    /**
     * Convierte un monto de la moneda de origen a la moneda destino.
     *
     * @param monto Monto a convertir
     * @return Resultado de la conversión
     */
    public double convertir(double monto) {
        if (monedaOrigen == null || monedaDestino == null) {
            throw new IllegalArgumentException("Las monedas no pueden ser nulas.");
        }
        return monto * (monedaDestino.getTasa() / monedaOrigen.getTasa());
    }

        /**
         * Devuelve la tasa de cambio almacenada.
         */
    public double getTasa() {
        return tasa;
    }

    /**
     * Devuelve la moneda de origen.
     */
    public Moneda getMonedaOrigen() {
        return monedaOrigen;
    }

    /**
     * Devuelve la moneda de destino.
     */
    public Moneda getMonedaDestino() {
        return monedaDestino;
    }

    /**
     * Representación textual de la tasa de cambio.
     */
    @Override
    public String toString() {
        return "1 " + monedaOrigen.getCodigo() + " = " + tasa + " " + monedaDestino.getCodigo();
    }
}
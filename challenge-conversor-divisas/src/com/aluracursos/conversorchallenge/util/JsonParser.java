package com.aluracursos.conversorchallenge.util;

// Importamos las librerías necesarias
import com.aluracursos.conversorchallenge.modelos.Moneda;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de parsear (convertir) el JSON obtenido desde la API
 * en objetos Java manipulables, como Mapas o cadenas.
 */
public class JsonParser {

    // Creamos una instancia de Gson, que nos ayudará a convertir texto JSON
    // en objetos Java manipulables (como JsonObject)
    Gson gson = new Gson();

    /**
     * Método principal que convierte un String con formato JSON
     * en un mapa donde las claves son códigos de monedas (ej. "EUR")
     * y los valores son sus tasas de cambio respecto a la moneda base.
     *
     * @param json Cadena de texto con formato JSON recibida desde la API
     * @return Mapa con todas las tasas de cambio
     */
    public Map<String, Double> parse(String json) {
        // Paso 1: Convertimos el texto JSON en un objeto JsonObject
        JsonObject objetoJson = convertirAJsonObject(json);

        // Paso 2: Obtenemos el objeto "conversion_rates" del JSON,
        // que contiene todas las tasas de cambio
        JsonObject tasas = obtenerTasasDeCambio(objetoJson);

        // Paso 3: Convertimos ese objeto en un Map<String, Double>
        return convertirTasasAMapa(tasas);
    }

    /**
     * Obtiene la fecha y hora de última actualización desde el campo:
     * "time_last_update_utc": "Fri, 27 Mar 2020 00:00:00 +0000"
     *
     * @param json Cadena de texto con formato JSON recibida desde la API
     * @return Fecha y hora de última actualización como cadena de texto
     */
    public String obtenerFechaUltimaActualizacion(String json) {
        // Convertimos el JSON a objeto manipulable
        JsonObject objetoJson = convertirAJsonObject(json);

        // Extraemos el valor del campo "time_last_update_utc"
        return objetoJson.get("time_last_update_utc").getAsString();
    }

    /**
     * Obtiene la moneda base desde el campo "base": "USD".
     *
     * @param json Cadena de texto con formato JSON recibida desde la API
     * @return Código de la moneda base (por ejemplo: "USD")
     */
    public String obtenerMonedaBase(String json) {
        JsonObject objetoJson = convertirAJsonObject(json);
        return objetoJson.get("base").getAsString();
    }

    /**
     * Verifica si la solicitud fue exitosa según el campo "success": true.
     *
     * @param json Cadena de texto con formato JSON recibida desde la API
     * @return true si la solicitud fue exitosa, false en caso contrario
     */
    public boolean esSolicitudExitosa(String json) {
        JsonObject objetoJson = convertirAJsonObject(json);
        return objetoJson.get("success").getAsBoolean();
    }

    // --- Métodos privados auxiliares para no repetir código ---

    /**
     * Convierte una cadena de texto JSON en un objeto JsonObject manipulable.
     *
     * @param json Cadena de texto con formato JSON
     * @return Objeto JsonObject listo para ser procesado
     */
    private JsonObject convertirAJsonObject(String json) {
        return gson.fromJson(json, JsonObject.class);
    }

    /**
     * Obtiene el objeto "conversion_rates" que contiene todas las tasas de cambio.
     *
     * @param objetoJson El objeto JSON ya convertido
     * @return Objeto JSON que contiene solo las tasas de cambio
     */
    private JsonObject obtenerTasasDeCambio(JsonObject objetoJson) {
        return objetoJson.getAsJsonObject("conversion_rates");
    }

    /**
     * Convierte el objeto JSON de tasas de cambio en un mapa Java (Map<String, Double>).
     *
     * @param tasas Objeto JSON con las tasas de cambio
     * @return Mapa con las tasas de cambio (clave: moneda, valor: tasa)
     */
    private Map<String, Double> convertirTasasAMapa(JsonObject tasas) {
        // Creamos un mapa vacío donde guardaremos las tasas de cambio
        Map<String, Double> resultado = new HashMap<>();



        // Recorremos cada entrada (clave-valor) del objeto JSON
        for (Map.Entry<String, JsonElement> entrada : tasas.entrySet()) {
            // Extraemos la clave (ej. "EUR") y el valor (ej. 0.89)
            // Convertimos el valor a double y lo guardamos en el mapa
            resultado.put(
                    entrada.getKey(),                  // Nombre de la moneda
                    entrada.getValue().getAsDouble()   // Tasa de cambio
            );
        }

        // Devolvemos el mapa con todas las tasas ya procesadas
        return resultado;
    }

    /**
     * Convierte las tasas de cambio en una lista de objetos Moneda.
     *
     * @param json Cadena JSON con los datos de la API
     * @return Lista de objetos Moneda
     */
    public List<Moneda> parsearMonedas(String json) {
        JsonObject objetoJson = convertirAJsonObject(json);
        JsonObject tasas = obtenerTasasDeCambio(objetoJson);

        Map<String, Double> mapaTasas = convertirTasasAMapa(tasas);
        List<Moneda> resultado = new ArrayList<>();

        for (Map.Entry<String, Double> entrada : mapaTasas.entrySet()) {
            String codigo = entrada.getKey();
            double tasa = entrada.getValue();

            // Aquí creamos objetos Moneda usando el constructor
            resultado.add(new Moneda(codigo, tasa)); // Puedes agregarle nombre si lo tienes
        }

        return resultado;
    }



}
package com.aluracursos.conversorchallenge.cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {

    //CLAVE DADA POR LA PAGINA PARA PODER UTILIZAR EL API
    String clave = "ba197f4f14fb07b428b1ea16";


    public String obtenerTasasCambio() throws IOException, InterruptedException {

        //SE CONTRUYE LA URL PARA HACER CONSULTAS BASANDOSE EN LA MONEDA DOLAR
        String direccion = "https://v6.exchangerate-api.com/v6/" + clave + "/latest/USD";

        try{

            //CREO UN CLIENTE PARA REALIZAR SOLICITUDES POR INTERNET
            HttpClient client = HttpClient.newHttpClient();

            //CONSTRUYO LA SOLICITUD HTTP CON LA DIRECCION CREADA
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();

            //ENVIA LA SOLICITUD AL SERVIDOR Y ESPERA LA RESPUESTA COMO TEXTO STRING
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Error en la API: Código " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al conectarse a la API de tasas de cambio.", e);
        }

    }

}

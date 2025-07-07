package com.aluracursos.conversorchallenge;

import com.aluracursos.conversorchallenge.modelos.Moneda;
import com.aluracursos.conversorchallenge.modelos.TasaCambio;
import com.aluracursos.conversorchallenge.util.JsonParser;
import com.aluracursos.conversorchallenge.cliente.ConexionApi;

import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try {
            // Paso 1: Obtener el JSON desde la API
            ConexionApi cliente = new ConexionApi();
            String json = cliente.obtenerTasasCambio();

            // Paso 2: Parsear el JSON a una lista de objetos Moneda
            JsonParser parser = new JsonParser();
            List<Moneda> monedas = parser.parsearMonedas(json);

            // Paso 3: Mostrar todas las monedas disponibles
            System.out.println("Monedas disponibles:");
            for (int i = 0; i < monedas.size(); i++) {
                System.out.println((i + 1) + ". " + monedas.get(i).getCodigo());
            }

            // Paso 4: Pedir datos al usuario
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nElige la moneda de origen (número): ");
            int origenIndex = scanner.nextInt() - 1;

            System.out.print("Elige la moneda de destino (número): ");
            int destinoIndex = scanner.nextInt() - 1;

            System.out.print("Ingresa el monto a convertir: ");
            double monto = scanner.nextDouble();

            // Paso 5: Obtener las monedas seleccionadas
            Moneda origen = monedas.get(origenIndex);
            Moneda destino = monedas.get(destinoIndex);

            // Paso 6: Crear una TasaCambio y hacer la conversión
            TasaCambio tasaCambio = new TasaCambio(origen, destino, destino.getTasa());

            double resultado = tasaCambio.convertir(monto);

            // Paso 7: Mostrar el resultado
            System.out.println("\nResultado:");
            System.out.printf("%.2f %s = %.2f %s%n", monto, origen.getCodigo(), resultado, destino.getCodigo());

        } catch (Exception e) {
            System.err.println("Ocurrió un error al procesar la solicitud.");
            e.printStackTrace();
        }
    }
}
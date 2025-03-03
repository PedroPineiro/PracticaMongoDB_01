package com.pedro.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pedro.model.Adestrador;
import com.pedro.model.Pokemon;
import com.pedro.service.AdestradorService;
import com.pedro.service.PokemonService;
import org.bson.Document;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collectionAdestradores;
    private final MongoCollection<Document> collectionPokemons;

    public Controller() {
        this.mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017");
        this.database = mongoClient.getDatabase("pokemon");
        this.collectionAdestradores = database.getCollection("adestradores");
        this.collectionPokemons = database.getCollection("pokemons");
    }

    public void iniciarApp() {
        System.out.println("Creando entrenador...");
        System.out.print("Introduce el nombre del entrenador: ");
        String name = scanner.nextLine();

        System.out.print("Introduce la edad del entrenador: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Evita problemas con scanner

        System.out.print("Introduce la ciudad del entrenador: ");
        String city = scanner.nextLine();

        // Crear entrenador y guardarlo en la base de datos
        Adestrador adestrador = new Adestrador(name, age, city);
        AdestradorService.insertAdestrador(adestrador, collectionAdestradores);
        System.out.println("Entrenador guardado en la base de datos.\n");

        // Insertar Pokémon
        System.out.println("Insertando Pokémon demodata...");
        meterDatos();
        System.out.println("Pokémon insertados correctamente.\n");

        // Menú de opciones
        while (true) {
            System.out.println("1. Insertar más Pokémon");
            System.out.println("2. Eliminar Pokémon");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer

            switch (opcion) {
                case 1:
                    meterDatos();
                    break;
                case 2:
                    sacarDatos();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    mongoClient.close(); // Cerrar la conexión solo al salir
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void meterDatos() {
        Pokemon pikachu = new Pokemon("Pikachu", new String[]{"Electric"}, 5, new String[]{"Thunderbolt"}, 1);
        PokemonService.insertPokemon(pikachu, collectionPokemons);

        Pokemon charmander = new Pokemon("Charmander", new String[]{"Fire"}, 5, new String[]{"Ember"}, 1);
        PokemonService.insertPokemon(charmander, collectionPokemons);
    }

    private void sacarDatos() {
        PokemonService.deletePokemon("Pikachu", collectionPokemons);
        PokemonService.deletePokemon("Charmander", collectionPokemons);
    }
}

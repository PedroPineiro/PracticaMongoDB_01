package com.pedro.service;

import com.mongodb.client.MongoCollection;
import com.pedro.model.Pokemon;
import org.bson.Document;

import java.util.Arrays;

public class PokemonService {
    public static void insertPokemon(Pokemon pokemon, MongoCollection<Document> collection) {
        Document insert = new Document("nome", pokemon.getNome())
                .append("tipos", Arrays.asList(pokemon.getTipo()))
                .append("nivel", pokemon.getNivel())
                .append("habilidades", Arrays.asList(pokemon.getHabilidades()))
                .append("id_adestrador", pokemon.getId_adestrador());
        collection.insertOne(insert);
    }

    public static void deletePokemon(String nome, MongoCollection<Document> collection) {
        Document delete = new Document("nome", nome);
        collection.deleteOne(delete);
    }
}
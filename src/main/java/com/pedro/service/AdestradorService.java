package com.pedro.service;

import com.mongodb.client.MongoCollection;
import com.pedro.model.Adestrador;
import org.bson.Document;

import java.util.Arrays;

public class AdestradorService {
    public static void insertAdestrador(Adestrador adestrador, MongoCollection<Document> collection) {
        Document insert = new Document("nome", adestrador.getNome())
                .append("idade", adestrador.getIdade())
                .append("cidade", adestrador.getCidade());
        collection.insertOne(insert);
    }

    public static void deleteAdestrador(String nome, MongoCollection<Document> collection) {
        Document delete = new Document("nome", nome);
        collection.deleteOne(delete);
    }
}
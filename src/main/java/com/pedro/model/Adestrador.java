package com.pedro.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Adestrador {
    private String nome;
    private int idade;
    private String cidade;

    public Adestrador() {
    }

    public Adestrador(String name, int age, String city) {
        this.nome = name;
        this.idade = age;
        this.cidade = city;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // Metodo para convertir a JSON
    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println("Error al convertir a JSON" + e.getMessage());
            return null;
        }
    }

    public static void saveAdestradorToMongoDB(String json, MongoClient mongoClient, MongoDatabase database, MongoCollection<Document> collection) {
        try {
            // Deserializar el JSON a un objeto Adestrador
            ObjectMapper objectMapper = new ObjectMapper();
            com.pedro.model.Adestrador adestrador = objectMapper.readValue(json, com.pedro.model.Adestrador.class);

            collection = database.getCollection("adestradores"); // Nombre de la colección

            // Convertir el objeto Trainer a un Document de MongoDB
            Document doc = new Document("nome", adestrador.getNome())
                    .append("idade", adestrador.getIdade())
                    .append("cidade", adestrador.getCidade());

            // Insertar el documento en la colección
            collection.insertOne(doc);
            System.out.println("Adestrador guardado exitosamente en MongoDB.");

            // Cerrar la conexión
            mongoClient.close();

        } catch (Exception e) {
            System.out.println("Error al guardar el Adestrador en MongoDB: " + e.getMessage());
        }
    }

}
package cl.ipla.discografia.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artistas")
public class Artista {
    
    @Id
    public String id;
    
    public String nombre;
    public String nacionalidad;
    public int anioDebut;

    public Artista() {
    }

    public Artista(String nombre, String nacionalidad, int anioDebut) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.anioDebut = anioDebut;
    }
}

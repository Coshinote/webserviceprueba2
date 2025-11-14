package cl.ipla.discografia.discografia.discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discos")
public class Disco {
    
    @Id
    public String id;
    
    public String titulo;
    public int anioLanzamiento;
    public String genero;
    public String idArtista;

    public Disco() {
    }

    public Disco(String titulo, int anioLanzamiento, String genero, String idArtista) {
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.genero = genero;
        this.idArtista = idArtista;
    }
}

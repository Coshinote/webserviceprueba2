package cl.ipla.discografia.discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IArtistaRepository extends MongoRepository<Artista, String> {
    
}

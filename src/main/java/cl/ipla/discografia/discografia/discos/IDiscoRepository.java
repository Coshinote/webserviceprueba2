package cl.ipla.discografia.discografia.discos;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface IDiscoRepository extends MongoRepository<Disco, String> {
    List<Disco> findDiscosByIdArtista(String idArtista);
}

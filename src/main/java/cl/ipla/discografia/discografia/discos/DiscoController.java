package cl.ipla.discografia.discografia.discos;

import cl.ipla.discografia.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandlePostDiscoRequest(@RequestBody Disco disco) {
        try {
            if (!artistaRepository.existsById(disco.idArtista)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El artista con id " + disco.idArtista + " no existe");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            Disco discoGuardado = discoRepository.save(disco);
            return ResponseEntity.status(HttpStatus.CREATED).body(discoGuardado);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear el disco: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        try {
            List<Disco> discos = discoRepository.findAll();
            return ResponseEntity.ok(discos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandleGetDiscoRequest(@PathVariable("id") String id) {
        try {
            Optional<Disco> disco = discoRepository.findById(id);
            if (disco.isPresent()) {
                return ResponseEntity.ok(disco.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Disco con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar el disco: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id) {
        try {
            List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
            return ResponseEntity.ok(discos);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar los discos del artista: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    
    @DeleteMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandleDeleteDiscoRequest(@PathVariable("id") String id) {
        try {
            if (!discoRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Disco con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            discoRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Disco eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar el disco: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

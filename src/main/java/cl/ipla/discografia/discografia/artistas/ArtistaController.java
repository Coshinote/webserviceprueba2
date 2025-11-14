package cl.ipla.discografia.discografia.artistas;

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
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    
    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        try {
            Artista artistaGuardado = artistaRepository.save(artista);
            return ResponseEntity.status(HttpStatus.CREATED).body(artistaGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> HandleGetAristasRequest() {
        try {
            List<Artista> artistas = artistaRepository.findAll();
            return ResponseEntity.ok(artistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandleGetArtistaRequest(@PathVariable("id") String id) {
        try {
            Optional<Artista> artista = artistaRepository.findById(id);
            if (artista.isPresent()) {
                return ResponseEntity.ok(artista.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Artista con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar el artista: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    
    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandleUpdateArtistaRequest(@PathVariable("id") String id, @RequestBody Artista artista) {
        try {
            if (!artistaRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Artista con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            artista.id = id;
            Artista artistaActualizado = artistaRepository.save(artista);
            return ResponseEntity.ok(artistaActualizado);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar el artista: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    
    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        try {
            if (!artistaRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Artista con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            artistaRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Artista eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar el artista: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

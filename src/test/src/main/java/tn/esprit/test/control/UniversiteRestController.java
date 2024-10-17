package tn.esprit.test.control;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.entity.Universite;
import tn.esprit.test.service.IUniversiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;
@Tag(name = "Gestion universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
    IUniversiteService universiteService;
    // http://localhost:8089/tpfoyer/universite/retrieve-all-universites
    @Operation(description = "récupérer toutes les universites de la base de données")
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getuniversites() {
        List<Universite> listuniversites = universiteService.retrieveAllUniversites();
        return listuniversites;
    }
    // http://localhost:8089/tpfoyer/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveuniversite(@PathVariable("universite-id") Long chId) {
        Universite universite = universiteService.retrieveUniversite(chId);
        return universite;
    }
    // http://localhost:8089/tpfoyer/universite/add-universite
    @PostMapping("/add-universite")
    public Universite adduniversite(@RequestBody Universite c) {
        Universite universite = universiteService.addUniversite(c);
        return universite;
    }
    // http://localhost:8089/tpfoyer/universite/remove-universite/{universite-id}
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeuniversite(@PathVariable("universite-id") Long chId) {
        universiteService.removeUniversite(chId);
    }
    // http://localhost:8089/tpfoyer/universite/modify-universite
    @PutMapping("/modify-universite")
    public Universite modifyuniversite(@RequestBody Universite c) {
        Universite universite = universiteService.modifyUniversite(c);
        return universite;
    }
}
package tn.esprit.test.control;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.entity.Bloc;
import tn.esprit.test.service.IBlocService;

import java.util.List;
@Tag(name = "Gestion Bloc")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {
    IBlocService blocService;
    // http://localhost:8089/tpfoyer/bloc/retrieve-all-blocs
   @Operation(description = "récupérer toutes les bloc de la base de données")
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getblocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }
    // http://localhost:8089/tpfoyer/bloc/retrieve-bloc/8
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrievebloc(@PathVariable("bloc-id") Long chId) {
        Bloc bloc = blocService.retrieveBloc(chId);
        return bloc;
    }
    // http://localhost:8089/tpfoyer/bloc/add-bloc
    @PostMapping("/add-bloc")
    public Bloc addbloc(@RequestBody Bloc c) {
        Bloc bloc = blocService.addBloc(c);
        return bloc;
    }
    // http://localhost:8089/tpfoyer/bloc/remove-bloc/{bloc-id}
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removebloc(@PathVariable("bloc-id") Long chId) {
        blocService.removeBloc(chId);
    }
    // http://localhost:8089/tpfoyer/bloc/modify-bloc
    @PutMapping("/modify-bloc")
    public Bloc modifybloc(@RequestBody Bloc c) {
        Bloc bloc = blocService.modifyBloc(c);
        return bloc;
    }




    @PutMapping("/affecter-bloc-a-foyer/{bloc-id}/{foyer-id}")
    public void affecterBlocFoyer(@PathVariable("bloc-id") Long blocID,
                                            @PathVariable("foyer-id") Long foyerID) {
        blocService.affecterBlocFoyer(blocID, foyerID);
    }


    @PutMapping("/desaffecter-foyer/{bloc-id}")
    public void DesaffecterBlocFoyer(@PathVariable("bloc-id") Long blocID)
    {
        blocService.DesaffecterBlocFoyer(blocID);
    }

    @GetMapping("/non-affectes")
    public List<Bloc> getUnassignedBlocs() {
        return blocService.findAllUnassignedBlocs();
    }
}
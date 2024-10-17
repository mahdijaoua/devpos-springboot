package tn.esprit.test.control;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.entity.Bloc;
import tn.esprit.test.entity.Foyer;
import tn.esprit.test.repository.FoyerRepository;
import tn.esprit.test.service.IFoyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@Tag(name = "Gestion foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {
    IFoyerService FoyerService;
    // http://localhost:8089/tpfoyer/foyer/retrieve-all-foyers
    @Operation(description = "récupérer toutes les foyers de la base de données")
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getfoyers() {
        List<Foyer> listfoyers = FoyerService.retrieveAllFoyers();
        return listfoyers;
    }
    // http://localhost:8089/tpfoyer/foyer/retrieve-foyer/8
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrievefoyer(@PathVariable("foyer-id") Long chId) {
        Foyer foyer = FoyerService.retrieveFoyer(chId);
        return foyer;
    }
    // http://localhost:8089/tpfoyer/foyer/add-foyer
    @PostMapping("/add-foyer")
    public Foyer addfoyer(@RequestBody Foyer c) {
        Foyer foyer = FoyerService.addFoyer(c);
        return foyer;
    }
    // http://localhost:8089/tpfoyer/foyer/remove-foyer/{foyer-id}
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removefoyer(@PathVariable("foyer-id") Long chId) {
        FoyerService.removeFoyer(chId);
    }
    // http://localhost:8089/tpfoyer/foyer/modify-foyer
    @PutMapping("/modify-foyer")
    public Foyer modifyfoyer(@RequestBody Foyer c) {
        Foyer foyer = FoyerService.modifyFoyer(c);
        return foyer;
    }


    @PostMapping("/ajouter-bloc-et-foyer")
    public Foyer addBlocAndFoyer(@RequestBody Foyer foyer) {
        Bloc bloc = new Bloc();
        bloc.setNomBloc(foyer.getBlocs().iterator().next().getNomBloc());
        bloc.setCapaciteBloc(foyer.getBlocs().iterator().next().getCapaciteBloc());
        bloc.setFoyer(foyer);
        foyer.getBlocs().clear();
        foyer.getBlocs().add(bloc);
        return FoyerService.addBlocAndFoyer(foyer);

    }

}
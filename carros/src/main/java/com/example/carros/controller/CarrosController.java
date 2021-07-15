package com.example.carros.controller;

import com.example.carros.model.Carro;
import com.example.carros.model.CarroService;
import com.example.carros.model.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<CarroDTO>> getCarroAll() {
        return ResponseEntity.ok(service.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarroByID(@PathVariable("id") Long id) {
        CarroDTO carros = service.getCarrosById(id);

        return ResponseEntity.ok(carros);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carro = service.getCarroByTipo(tipo);

        return carro.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(carro);
    }

    @GetMapping("/alltipo/{tipo}")
    public List<Carro> allTipoCustom(@PathVariable("tipo") String tipo) {
        return service.getAllTipo(tipo);
    }

    @GetMapping("/filter/custom")
    public List<Carro> allCustom(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "nome", required = false) String nome,
                                 @RequestParam(value = "tipo", required = false) String tipo) {


        return service.getAllCustom(id, nome, tipo);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity saveCarro(@RequestBody Carro carro) {

        CarroDTO c = service.insert(carro);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).build();

    }

    //PEGAR A URI
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("{id}")
    public ResponseEntity updateCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {
        CarroDTO c = service.update(carro, id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCarro(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.ok().build();
    }

}

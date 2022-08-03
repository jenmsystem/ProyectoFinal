package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.AffiliatePassive;
import pe.com.project.bank.model.Movement;
import pe.com.project.bank.service.IMovementService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Movement")
public class MovementController {
    @Autowired
    private IMovementService iMovementService;

    @GetMapping
    public Flux<Movement> list(){ return iMovementService.findAll(); }

    @GetMapping("/{id}")
    public Flux<Movement> findProductById(@PathVariable("id") Long id){
        return  iMovementService.findMovementById(id);
    }

    @GetMapping("/LastMovementsReport/{id}")
    public Flux<Movement> LastMovementsReport(@PathVariable("id") Long id){
        return iMovementService.findAll().filter(x -> x.getClientId().equals(id)).take(10);
    }

    @PostMapping
    public Mono<Movement> save(@RequestBody Movement movement){ return iMovementService.save(movement); }

    @PutMapping
    public Mono<Movement> update(@RequestBody Movement movement){
        return iMovementService.update(movement);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iMovementService.delete(id); }

    @GetMapping("/{clientId}/{productId}")
    //@RequestMapping(value = "/{clientId}/{productId}", method = GET)
    //@ResponseBody
    public Flux<Movement> findByClientIdAndProductId(@PathVariable Long clientId,@PathVariable Long productId){
        return  iMovementService.findByClientIdAndProductId(clientId,productId);
    }
}

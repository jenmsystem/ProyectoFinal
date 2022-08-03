package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.Consumption;
import pe.com.project.bank.model.Movement;
import pe.com.project.bank.service.IConsumptionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Consumption")
public class ConsumptionController {
    @Autowired
    private IConsumptionService iConsumptionService;

    @GetMapping
    public Flux<Consumption> list(){ return iConsumptionService.findAll(); }

    @GetMapping("/{id}")
    public Flux<Consumption> findProductById(@PathVariable("id") Long id){
        return  iConsumptionService.findConsumptionById(id);
    }

    @GetMapping("/LastMovementsReport/{id}")
    public Flux<Consumption> LastMovementsReport(@PathVariable("id") Long id){
        return iConsumptionService.findAll().filter(x -> x.getClientId().equals(id)).take(10);
    }
    @PostMapping
    public Mono<Consumption> save(@RequestBody Consumption consumption){ return iConsumptionService.save(consumption); }

    @PutMapping
    public Mono<Consumption> update(@RequestBody Consumption consumption){
        return iConsumptionService.update(consumption);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iConsumptionService.delete(id); }
}

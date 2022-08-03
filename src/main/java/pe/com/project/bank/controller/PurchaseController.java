package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.Purchase;
import pe.com.project.bank.service.IPurchaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Purchase")
public class PurchaseController {
    @Autowired
    private IPurchaseService iPurchaseService;
    @GetMapping
    public Flux<Purchase> findAll(){ return iPurchaseService.findAll(); }

    @GetMapping("/{id}")
    public Flux<Purchase> findPurchaseById(@PathVariable("id") Long id){ return  iPurchaseService.findPurchaseById(id);}

    @PostMapping
    public Mono<Purchase> save(@RequestBody Purchase purchase){ return iPurchaseService.save(purchase); }

    @PutMapping
    public Mono<Purchase> update(@RequestBody Purchase purchase){
        return iPurchaseService.update(purchase);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iPurchaseService.delete(id); }
}

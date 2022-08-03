package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.Sale;
import pe.com.project.bank.service.ISaleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Sale")
public class SaleController {
    @Autowired
    private ISaleService iSaleService;
    @GetMapping
    public Flux<Sale> findAll(){ return iSaleService.findAll(); }

    @GetMapping("/{id}")
    public Flux<Sale> findSaleById(@PathVariable("id") Long id){ return  iSaleService.findSaleById(id);}

    @PostMapping
    public Mono<Sale> save(@RequestBody Sale sale){ return iSaleService.save(sale); }

    @PutMapping
    public Mono<Sale> update(@RequestBody Sale sale){
        return iSaleService.update(sale);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iSaleService.delete(id); }
}

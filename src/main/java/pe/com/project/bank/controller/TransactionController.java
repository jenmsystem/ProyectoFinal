package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.Transaction;
import pe.com.project.bank.service.ITransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    private ITransactionService iTransactionService;
    @GetMapping
    public Flux<Transaction> findAll(){ return iTransactionService.findAll(); }

    @GetMapping("/{id}")
    public Flux<Transaction> findTransactionById(@PathVariable("id") Long id){ return  iTransactionService.findTransactionById(id);}

    @PostMapping
    public Mono<Transaction> save(@RequestBody Transaction transaction){ return iTransactionService.save(transaction); }

    @PutMapping
    public Mono<Transaction> update(@RequestBody Transaction transaction){
        return iTransactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iTransactionService.delete(id); }
}

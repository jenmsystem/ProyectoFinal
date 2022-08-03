package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.Payment;
import pe.com.project.bank.service.IPaymentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Payment")
public class PaymentController {
    @Autowired
    private IPaymentService iPaymentService;

    @GetMapping
    public Flux<Payment> list(){ return iPaymentService.findAll(); }

    @GetMapping("/{id}")
    public Flux<Payment> findActiveById(@PathVariable("id") Long id){ return  iPaymentService.findPaymentById(id);}

    @PostMapping
    public Mono<Payment> save(@RequestBody Payment payment){ return iPaymentService.save(payment); }

    @PutMapping
    public Mono<Payment> update(@RequestBody Payment payment){
        return iPaymentService.update(payment);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){ return iPaymentService.delete(id); }
}

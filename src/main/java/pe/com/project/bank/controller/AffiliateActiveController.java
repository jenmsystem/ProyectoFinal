package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.AffiliateActive;
import pe.com.project.bank.model.AffiliatePassive;
import pe.com.project.bank.model.Payment;
import pe.com.project.bank.service.IAffiliateActiveService;
import pe.com.project.bank.service.IPaymentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@RestController
@RequestMapping("/AffiliateActive")
@Slf4j
public class AffiliateActiveController {
    @Autowired
    private IAffiliateActiveService iAffiliateActiveService;

    @Autowired
    private IPaymentService iPaymentService;

    @GetMapping
    public Flux<AffiliateActive> findAll(){
        log.info("findAll" + "OK");
        return iAffiliateActiveService.findAll();
    }

    @GetMapping("/{id}")
    public Flux<AffiliateActive> findActiveById(@PathVariable("id") Long id){
        log.info("findActiveById" + "OK");
        return  iAffiliateActiveService.findActiveById(id);
    }

    @PostMapping
    public Mono<AffiliateActive> create(@RequestBody AffiliateActive active){
        Date date = new Date();
        Flux<Payment> payment = iPaymentService.findAll();

        payment
                .filter(x -> x.getDate().compareTo(date) >= 0)
                .count()
                .flatMap(p -> {
                    if ( p > 0){
                        return iAffiliateActiveService.save(active);

                    }else {
                        return null;
                    }
                });
        return null;

    }



    @PutMapping
    public Mono<AffiliateActive> update(@RequestBody AffiliateActive active){
        log.info("update" + "OK");
        return iAffiliateActiveService.update(active);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){
        log.info("delete" + "OK");
        return iAffiliateActiveService.delete(id);
    }


    @GetMapping("/{date1}/{date2}")
    public Flux<AffiliateActive> fullReport(@PathVariable("date1") Date date1,@PathVariable("date2") Date date2){
        return  iAffiliateActiveService.findAll().filter(x-> x.getDate().compareTo(date1) >= 0).filter(y->y.getDate().compareTo(date2)<=0);
    }
}

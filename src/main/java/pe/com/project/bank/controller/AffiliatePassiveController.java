package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.AffiliatePassive;
import pe.com.project.bank.service.IAffiliatePassiveService;
import pe.com.project.bank.service.IClientService;
import pe.com.project.bank.service.IPaymentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/AffiliatePassive")
public class AffiliatePassiveController {
    @Autowired
    private IAffiliatePassiveService iAffiliatePassiveService;

    @Autowired
    private IClientService iClientService;

    @Autowired
    private IPaymentService iPaymentService;

    @GetMapping
    public Flux<AffiliatePassive> list(){ return iAffiliatePassiveService.findAll(); }

    @GetMapping("/{id}")
    public Flux<AffiliatePassive> findActiveById(@PathVariable("id") Long id){ return  iAffiliatePassiveService.findPassiveById(id);}

    @GetMapping("/MainAccountBalance/{id}")
    public Flux<AffiliatePassive> MainAccountBalance(@PathVariable("id") Long id){
        Flux<AffiliatePassive> affiliatePassiveFlux =  iAffiliatePassiveService.findAll();
          return affiliatePassiveFlux
                  .filter(x -> x.getClientId().equals(id) && x.getMainAccount().equals("true"));
    }

    @GetMapping("/{date1}/{date2}")
    public Flux<AffiliatePassive> fullReport(@PathVariable("date1") Date date1, @PathVariable("date2") Date date2){
        return  iAffiliatePassiveService.findAll().filter(x-> x.getDate().compareTo(date1) >= 0).filter(y->y.getDate().compareTo(date2)<=0);
    }


    @PostMapping("/AssociateAllAccounts/{id}/{debitCard}")
    public Flux<AffiliatePassive> AssociateAllAccounts(@PathVariable("id") Long id, @PathVariable("debitCard") String debitCard){
        Flux<AffiliatePassive> affiliatePassiveFlux =  iAffiliatePassiveService.findAll().filter(x -> x.getClientId().equals(id));
        return affiliatePassiveFlux
                .flatMap(
                       x -> {
                           x.setDebitCard(debitCard);
                           return iAffiliatePassiveService.save(x);
                       }
                );

    }

    @PostMapping
    public Mono<AffiliatePassive> save(@RequestBody AffiliatePassive passive) {
        Flux<AffiliatePassive> affiliatePassiveFlux = iAffiliatePassiveService.findAll();
        if(passive.getClientType().equals("persona")){
            if(!passive.getAccountType().equals("plazoFijo")){
                return affiliatePassiveFlux
                        .filter(x->x.getClientId().equals(passive.getClientId()))
                        .distinct(AffiliatePassive::getAccountType) // ninguna, corriente, ahorro o plazo fijo
                        .filter(x -> x.getAccountType().equals(passive.getAccountType()))
                        .count()
                        .flatMap(p -> {
                            if ( p >= 1){
                                System.out.println("No se afilio, porque solo puede tener una sola cuenta " + passive.getAccountType());
                                return Mono.empty();
                            }else {
                                System.out.println("Afilición exitosa de cuenta " + passive.getAccountType());
                                return iAffiliatePassiveService.save(passive);
                            }
                        });
            } else {
                System.out.println("Afilición de cuenta Plazo Fijo exitosa");
                return iAffiliatePassiveService.save(passive);
            }
        } else {
            if (passive.getAccountType().equals("corriente")) {
                System.out.println("Empresa: Afilición exitosa de cuenta " + passive.getAccountType());
                return iAffiliatePassiveService.save(passive);
            } else {
                System.out.println("Empresa: No se afilio, porque no puede tener una cuenta " + passive.getAccountType());
                return  null;
            }
        }
    }

    @PutMapping
    public Mono<AffiliatePassive> update(@RequestBody AffiliatePassive passive){
        return iAffiliatePassiveService.update(passive);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){
        return iAffiliatePassiveService.delete(id);
    }
}
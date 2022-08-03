package pe.com.project.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.project.bank.model.AffiliateActive;
import pe.com.project.bank.model.AffiliatePassive;
import pe.com.project.bank.model.Client;
import pe.com.project.bank.service.IAffiliateActiveService;
import pe.com.project.bank.service.IAffiliatePassiveService;
import pe.com.project.bank.service.IClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/Client")
@Slf4j
public class ClientController {
    @Autowired
    private IClientService clientService;
    @Autowired
    private IAffiliatePassiveService iAffiliatePassiveService;

    @Autowired
    private IAffiliateActiveService iAffiliateActiveService;

    @GetMapping
    public Flux<Client> findAll()
    {
        log.info("findAll" + "OK");
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Flux<Client> findClientById(@PathVariable("id") Long id){
        log.info("findClientById" + "OK");
        return  clientService.findClientById(id);
    }

    @PostMapping
    public Mono<Client> create(@RequestBody Client client)
    {
        log.info("create" + "OK");
        return clientService.save(client);
    }

    @PutMapping
    public Mono<Client> update(@RequestBody Client client){
        log.info("update" + "OK");
        return clientService.update(client);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id){
        log.info("delete" + "OK");
        return clientService.delete(id);
    }

    @GetMapping("/document")
    public Flux<Client> searchByDocument(@PathVariable String document)
    {
        log.info("searchByDocument" + "OK");
        return clientService.searchByDocument(document);
    }

    @GetMapping("/id")
    public Mono<Double> availableBalance(@PathVariable("id") Long id)
    {
        log.info("availableBalance" + "OK");
        return iAffiliatePassiveService.findAll().filter(x -> x.getClientId().equals(id)).collect(Collectors.summingDouble(AffiliatePassive::getBalance));
    }

    @GetMapping("/ProductPassive/idClient")
    public Flux<AffiliatePassive> clientProductPassive(@PathVariable("idClient") Long idClient)
    {
        log.info("clientProductPassive" + "OK");
        return iAffiliatePassiveService.findAll().filter(x -> x.getClientId().equals(idClient));
    }

    @GetMapping("/ProductActive/idClient")
    public Flux<AffiliateActive> clientProductActive(@PathVariable("idClient") Long idClient)
    {
        log.info("clientProductActive" + "OK");
        return iAffiliateActiveService.findAll().filter(x -> x.getClientId().equals(idClient));
    }
}

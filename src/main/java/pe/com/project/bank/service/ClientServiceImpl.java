package pe.com.project.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.com.project.bank.model.Client;
import pe.com.project.bank.model.CustomerCreatedEvent;
import pe.com.project.bank.model.Event;
import pe.com.project.bank.model.EventType;
import pe.com.project.bank.repository.IClientRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    IClientRepository clientRepository;

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.client.name:clients}")
    private String topicClient;

    public void publish(Client client) {

        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setData(client);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send(topicClient, created);
    }

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> save(Client client) {
        publish(client);
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> update(Client client) {
        return clientRepository.findById(client.getId().intValue())
                .map(c->{
                    c.setDocument(client.getDocument());
                    c.setDocumentType(client.getDocumentType());
                    c.setName(client.getName());
                    c.setStatus(client.isStatus());
                    return c;
                })
                .flatMap(x -> clientRepository.save(x));
    }

    @Override
    public Flux<Client> findClientById(Long id) {
        return clientRepository.findAll().filter(x-> x.getId().equals(id));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return clientRepository.deleteById(id);
    }

    @Override
    public Flux<Client> searchByDocument(String document) {
        return clientRepository.searchByDocument(document);
    }
}

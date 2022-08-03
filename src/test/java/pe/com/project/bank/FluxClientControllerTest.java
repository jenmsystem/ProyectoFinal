package pe.com.project.bank;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.project.bank.model.Client;
import pe.com.project.bank.service.IClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest
public class FluxClientControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    IClientService clientService;

    @Test
    public void testfindAll(){
        Flux<Client> clientFlux =  Flux.just(new Client(10l,"Gian","DNI","45376063","985632587","gian@mail.com",true),
                new Client(11l,"Gian","DNI","45376063","985632587","gian@mail.com",true));
        Mockito.when(clientService.findAll()).thenReturn(clientFlux);

        webTestClient.get().uri("/Client")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo(10l)
                .jsonPath("$[0].name").isEqualTo("Gian");

        StepVerifier.create(clientFlux)
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    public void testGetClient500(){

        Mockito.when(clientService.findAll()).thenThrow(new RuntimeException());

        webTestClient.get()
                .uri("/Client")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError();

    }

    @Test
    public void testPostClient(){
        Client c = new Client(12l,"Gian","DNI","45376063","985632587","gian@mail.com",true);
        Mono<Client> monoC = Mono.just(c);

        Mockito.when(clientService.save(c)).thenReturn(monoC);

        webTestClient.post()
                .uri("/Client")
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoC, Client.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class).isEqualTo(c);

    }

    @Test
    public void testPutClient(){
        Client c = new Client(12l,"Gian","DNI","45376063","985632587","gian@mail.com",true);
        Mono<Client> monoC = Mono.just(c);

        Mockito.when(clientService.update(c)).thenReturn(monoC);

        webTestClient.post()
                .uri("/Client")
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoC, Client.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class).isEqualTo(c);

    }

    @Test
    public void testDeleteClient(){
        int id = 10;
        Mono<Integer> monoC = Mono.just(id);

        Mockito.when(clientService.delete(id));

        webTestClient.post()
                .uri("/Client")
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoC, Client.class)
                .exchange()
                .expectStatus().isOk();
        //.expectBody(Client.class).isEqualTo(c);

    }
}

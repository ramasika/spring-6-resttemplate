package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6resttemplate.RestTemplateBuilderConfig;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(BeerClientImpl.class)
public class BeerClientMockTest {

    BeerClient beerClient;

    MockRestServiceServer server;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    RestTemplateBuilder mockRestTemplateBuilder = new RestTemplateBuilder(new MockServerRestTemplateCustomizer());

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        server = MockRestServiceServer.bindTo(restTemplate).build();
        when(mockRestTemplateBuilder.build()).thenReturn(restTemplate);
        beerClient = new BeerClientImpl(mockRestTemplateBuilder);
    }

    @Test
    void getBeerById() throws JsonProcessingException {
        UUID id = UUID.randomUUID();
        String beerJson = objectMapper.writeValueAsString(getBeerDto(id));

        server.expect(method(HttpMethod.GET))
                .andExpect(requestTo("/api/v1/beer/" + id))
                .andRespond(withSuccess(beerJson, MediaType.APPLICATION_JSON));
        BeerDTO beerDTO = beerClient.getBeer(id);
        assertThat(beerDTO.getId()).isEqualTo(id);
    }

    BeerDTO getBeerDto(UUID id){
        Random random = new Random();
        return BeerDTO.builder()
                .id(id)
                .beerName("Castle Lager")
                .upc("AA")
                .price(BigDecimal.valueOf(15))
                .version(1)
                .beerStyle(BeerStyle.values()[random.nextInt(BeerStyle.values().length-1)])
                .quantityOnHand(random.nextInt(10000))
                .build();
    }
}

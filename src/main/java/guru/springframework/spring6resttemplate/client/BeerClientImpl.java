package guru.springframework.spring6resttemplate.client;

import ch.qos.logback.classic.spi.LoggingEventVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${beer.api.base.url}")
    private String beerApiBaseUrl;

    private UriComponentsBuilder createUriComponentsBuilder() {
        return UriComponentsBuilder
                .fromUriString( "/api/v1/beer/");
    }

    private Page<BeerDTO> doGetBeers(UriComponentsBuilder uriComponentsBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<BeerDTOPageImpl> responseEntity = restTemplate.getForEntity(
                uriComponentsBuilder.toUriString(),
                BeerDTOPageImpl.class
        );

        System.out.println(responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public Page<BeerDTO> getBeers() {
        UriComponentsBuilder uriComponentsBuilder = createUriComponentsBuilder();
        return doGetBeers(uriComponentsBuilder);
    }

    @Override
    public Page<BeerDTO> getBeers(String beerName) {
        UriComponentsBuilder uriComponentsBuilder = createUriComponentsBuilder();

        if(StringUtils.hasText(beerName)) {
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        return doGetBeers(uriComponentsBuilder);
    }

    @Override
    public Page<BeerDTO> getBeers(String beerName, Integer pageNumber, Integer pageSize) {
        UriComponentsBuilder uriComponentsBuilder = createUriComponentsBuilder();

        if(StringUtils.hasText(beerName)) {
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        if(pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }

        if(pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        return doGetBeers(uriComponentsBuilder);
    }

    @Override
    public Page<BeerDTO> getBeers(BeerStyle beerStyle) {
        UriComponentsBuilder uriComponentsBuilder = createUriComponentsBuilder();

        if(beerStyle != null) {
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }

        return doGetBeers(uriComponentsBuilder);
    }

    @Override
    public Page<BeerDTO> getBeers(BeerStyle beerStyle, Integer pageNumber, Integer pageSize) {
        UriComponentsBuilder uriComponentsBuilder = createUriComponentsBuilder();

        if(beerStyle != null) {
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }

        if(pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }

        if(pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        return doGetBeers(uriComponentsBuilder);
    }

    @Override
    public BeerDTO getBeer(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        BeerDTO beerDTO = restTemplate.getForObject(
                "/api/v1/beer/{id}",
                BeerDTO.class,
                id
        );
        System.out.println(beerDTO);
        return beerDTO;
    }

}

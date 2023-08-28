package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {

    Page<BeerDTO> getBeers();
    Page<BeerDTO> getBeers(String beerName);
    Page<BeerDTO> getBeers(String beerName, Integer pageNumber, Integer pageSize);
    Page<BeerDTO> getBeers(BeerStyle beerStyle);
    Page<BeerDTO> getBeers(BeerStyle beerStyle, Integer pageNumber, Integer pageSize);
    BeerDTO getBeer(UUID id);
}

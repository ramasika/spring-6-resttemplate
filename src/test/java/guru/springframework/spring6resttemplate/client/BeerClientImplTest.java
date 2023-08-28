package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl client;

    @Test
    void getBeers_noName() {
        Page<BeerDTO> beerDTOPage = client.getBeers();
        assertThat(beerDTOPage).isNotNull();
        assertThat(beerDTOPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void getBeersByName() {
        Page<BeerDTO> beerDTOPage = client.getBeers("Foreman");
        assertThat(beerDTOPage).isNotNull();
        assertThat(beerDTOPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void getBeersByStyle() {
        Page<BeerDTO> beerDTOPage = client.getBeers(BeerStyle.LAGER);
        assertThat(beerDTOPage).isNotNull();
        assertThat(beerDTOPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void getBeerById() {
        UUID id = UUID.fromString("57c9abc2-b76a-457f-9f24-03ab2ab81c17");
        BeerDTO beerDTO = client.getBeer(id);
        assertThat(beerDTO).isNotNull();
        assertThat(beerDTO.getId()).isEqualTo(id);
    }
}
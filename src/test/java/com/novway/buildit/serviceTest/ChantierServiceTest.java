package com.novway.buildit.serviceTest;

import com.novway.buildit.data.ChantierData;
import com.novway.buildit.entity.Chantier;
import com.novway.buildit.repository.ChantierRepository;
import com.novway.buildit.service.implementations.ChantierServiceImpl;
import com.novway.buildit.service.interfaces.ChantierServiceInterface;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ChantierServiceTest {
  @InjectMocks
  private ChantierServiceImpl chantierService;

  @Mock
  private ChantierRepository ChantierRepository;

  @Autowired
  private TestEntityManager entityManager;

  private List<Chantier> chantiers;

  @Test
  public void getAllChantier(){
    chantiers = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      Chantier chantier = new Chantier();
      chantier.setCode("code "+i);
      entityManager.persistAndFlush(chantier);
    }
    entityManager.flush();

    Mockito.when(ChantierRepository.findAll()).thenReturn(chantiers);

    assertThat(chantierService.getAllChantier().size()).isEqualTo(chantiers.size());
  }
}

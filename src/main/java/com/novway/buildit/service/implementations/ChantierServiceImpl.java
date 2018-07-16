package com.novway.buildit.service.implementations;

import com.novway.buildit.entity.Chantier;
import com.novway.buildit.repository.ChantierRepository;
import com.novway.buildit.service.interfaces.ChantierServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChantierServiceImpl implements ChantierServiceInterface {

  @Autowired
  private ChantierRepository ChantierRepository;
  @Override
  public List<Chantier> getAllChantier() {
    int i = 0;
    List<Chantier> chantierList = new ArrayList<>();

    for (i=0;i<10;i++){
      Chantier chantier = new Chantier();
      chantier.setCode("code "+i);
      chantierList.add(chantier);
    }
    return ChantierRepository.save(chantierList);
  }

  @Override
  public Chantier getChantier(Long id) {
    return ChantierRepository.findOne(id);
  }
}

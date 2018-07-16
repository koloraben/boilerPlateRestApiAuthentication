package com.novway.buildit.data;

import com.novway.buildit.entity.Chantier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum  ChantierData {
  INSTANCE;

  private List<Chantier> chantiers;
  private Chantier chantier;

  public void initData() {
    chantiers = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      Chantier chantier = new Chantier();
      chantier.setCode("code "+i);
      chantiers.add( chantier);
    }
  }

  public Chantier getChantier() {
    return chantier;
  }

  public List<Chantier> getChantiers(){
    return chantiers;
  }
}

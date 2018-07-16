package com.novway.buildit.service.interfaces;

import com.novway.buildit.entity.Chantier;
import java.util.List;
import org.springframework.stereotype.Service;


public interface ChantierServiceInterface {
  /**
   *
   * @return all chantier in the database
   */
  public List<Chantier> getAllChantier();

  /**
   *
   * @param id
   * @return chantier with that id
   */
  public Chantier getChantier(Long id);
}

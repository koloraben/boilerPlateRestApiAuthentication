package com.novway.buildit.bootstrap;

import com.novway.buildit.repository.ChantierRepository;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Setter
public class Loader implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(Loader.class);

  @Autowired
  ChantierRepository chantierRepository;
  @Override
  public void run(String... strings) throws Exception {
    logger.info("////////////////////////// loading DATA ////////////////////////");
  }
}

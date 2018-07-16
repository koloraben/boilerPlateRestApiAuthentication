package com.novway.buildit.controllerTest;
import com.novway.buildit.controller.ChantierController;
import com.novway.buildit.entity.Chantier;
import com.novway.buildit.service.interfaces.ChantierServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.engine.spi.CachedNaturalIdValueSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ExampleTestController {

  private MockMvc mockMvc;

  @Mock
  private ChantierServiceInterface chantierServiceInterface ;

  @InjectMocks
  private ChantierController chantierController;

  @Before
  public void setUp() throws Exception{
    mockMvc = MockMvcBuilders.standaloneSetup(chantierController).build();
  }
  @Test
  public void findAllChantierTest() throws Exception {
    String expectedResultJson = "[{\"id\":null,\"created\":0,\"modified\":0,\"created_by\":null,\"modified_by\":null,\"code\":\"CODE1\"}]";
    List<Chantier> list = new ArrayList<>();
    Chantier chanier = new Chantier();
    chanier.setCode("CODE1");
    list.add(chanier );
    Mockito.when(
        chantierServiceInterface.getAllChantier()).thenReturn(list);

    RequestBuilder requestBuilder = get(
        "/").accept(
        MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].code").value("CODE1"))
        .andExpect(jsonPath("$").isArray())
        .andReturn();
    assertEquals(expectedResultJson,result.getResponse().getContentAsString());
  }
  @Test
  public void findChantier() throws Exception {
    Chantier chantier = new Chantier();
    chantier.setCode("CODE1");
    chantier.setId(1l);
    Mockito.when(chantierServiceInterface.getChantier(1l)).thenReturn(chantier);
    RequestBuilder requestBuilder = get(
        "/chantier/1").accept(
        MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("CODE1"))
        .andExpect(jsonPath("$.id").value(1))
        .andReturn();
  }
}

package br.com.letscode.starwarsnetwork.infraestructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class SpringIntegrationTest {

    @Autowired protected MockMvc mvc;

    @Autowired protected ObjectMapper mapper;

    @Autowired protected ModelMapper modelMapper;
}

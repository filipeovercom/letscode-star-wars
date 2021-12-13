package br.com.letscode.starwarsnetwork.infraestructure;

import io.cucumber.java8.Pt;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
@ActiveProfiles({"test"})
@ContextConfiguration
@AutoConfigureMockMvc
@Slf4j
public class SpringContextConfiguration implements Pt {}

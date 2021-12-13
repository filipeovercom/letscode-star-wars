package br.com.letscode.starwarsnetwork;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
            "br.com.letscode.starwarsnetwork.infraestructure",
            "br.com.letscode.starwarsnetwork.features"
        })
public class IntegrationTests {}

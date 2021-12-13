package br.com.letscode.starwarsnetwork;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ApplicationStartTest {

    @Test
    public void applicationStarts() {
        StarWarsResistenceSocialNetworkApplication.main("--spring.profiles.active=test");
    }
}

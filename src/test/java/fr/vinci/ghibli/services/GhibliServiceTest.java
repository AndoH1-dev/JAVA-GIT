
package fr.vinci.ghibli.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GhibliServiceTest {

    @Test
    void rechercheSansResultatRetourneListeVide() {

        GhibliService service = new GhibliService();

        var films = service.rechercherFilms(
            "film-inexistant-xyz-12345"
        );

        assertNotNull(films);
        assertTrue(films.isEmpty());
    }
}

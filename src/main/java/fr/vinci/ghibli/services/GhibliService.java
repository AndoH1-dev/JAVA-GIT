
package fr.vinci.ghibli.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import fr.vinci.ghibli.models.Film;

@Service
public class GhibliService {

    private final RestClient client = RestClient.create(
        "https://ghibliapi.dev"
    );

    // Récupérer tous les films
    public List<Film> getFilms() {

        Film[] films = client.get()
                .uri("/films")
                .retrieve()
                .body(Film[].class);

        if (films == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(films));
    }

    // Récupérer un film à partir de son identifiant
    public Film getFilmById(String id) {

        return client.get()
                .uri("/films/{id}", id)
                .retrieve()
                .body(Film.class);
    }

    // Chercher les films dont le titre contient un mot
    public List<Film> rechercherFilms(String recherche) {

        List<Film> films = getFilms();
        List<Film> resultats = new ArrayList<>();

        for (Film film : films) {

            if (film.getTitle() != null
                    && film.getTitle().toLowerCase()
                       .contains(recherche.toLowerCase())) {

                resultats.add(film);
            }
        }

        return resultats;
    }
}

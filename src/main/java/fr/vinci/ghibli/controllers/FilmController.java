
package fr.vinci.ghibli.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import fr.vinci.ghibli.models.Film;
import fr.vinci.ghibli.services.GhibliService;

@Controller
public class FilmController {

    private final GhibliService ghibliService;

    public FilmController(GhibliService ghibliService) {
        this.ghibliService = ghibliService;
    }

    // Page catalogue + recherche
    @GetMapping("/films")
    public String afficherFilms(
            @RequestParam(defaultValue = "") String recherche,
            Model model) {

        try {
            List<Film> films;

            if (recherche.isBlank()) {
                films = ghibliService.getFilms();
            } else {
                films = ghibliService.rechercherFilms(recherche);
            }

            model.addAttribute("films", films);

        } catch (RestClientException e) {
            model.addAttribute("films", List.of());
            model.addAttribute("erreur",
                    "Impossible de charger les films.");
        }

        model.addAttribute("recherche", recherche);

        return "films";
    }

    // Page détail d'un film
    @GetMapping("/films/{id}")
    public String afficherDetail(
            @PathVariable String id,
            Model model) {

        try {
            Film film = ghibliService.getFilmById(id);

            if (film == null) {
                model.addAttribute("erreur", "Film introuvable.");
            }

            model.addAttribute("film", film);

        } catch (RestClientException e) {
            model.addAttribute("erreur",
                    "Impossible de charger ce film.");
        }

        return "film-detail";
    }
}


package fr.vinci.ghibli.controllers;

import fr.vinci.ghibli.models.Film;
import fr.vinci.ghibli.services.GhibliService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GhibliService ghibliService;

    @Test
    void afficherCatalogue() throws Exception {

        when(ghibliService.getFilms())
                .thenReturn(List.of());

        mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(view().name("films"))
                .andExpect(model().attributeExists("films"));
    }

    @Test
    void rechercherUnFilm() throws Exception {

        Film film = new Film();
        film.setTitle("My Neighbor Totoro");

        when(ghibliService.rechercherFilms("totoro"))
                .thenReturn(List.of(film));

        mockMvc.perform(get("/films")
                .param("recherche", "totoro"))
                .andExpect(status().isOk())
                .andExpect(view().name("films"))
                .andExpect(model().attributeExists("films"));
    }
}

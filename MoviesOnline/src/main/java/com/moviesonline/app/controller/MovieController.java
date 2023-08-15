package com.moviesonline.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.moviesonline.app.model.Movie;
import com.moviesonline.app.repository.IMovie;

@Controller
public class MovieController {

	@Autowired
	IMovie movieService;
	private final List<String> pelisDestacadasID = Arrays.asList("tt3581920", "tt13443470", "tt1520211", "tt2442560", "tt4154796");
	
	@GetMapping("/")
	public String home(Model model) {
		ArrayList<Movie> pelisDestacadas = new ArrayList<>();
		for (String id : pelisDestacadasID) {
			pelisDestacadas.add(movieService.getMovieById(id));
		}
		model.addAttribute("title", "MoviesOnline");
		model.addAttribute("pelisDestacadas", pelisDestacadas);
		
		return "index";
	}

	@GetMapping("/movie/{id}")
	public String show(@PathVariable String id, Model model){
		Movie movie = movieService.getMovieById(id);
		ArrayList<String> directores = new ArrayList<>();
		if (StringUtils.isNotEmpty(movie.getWriter())) {
		 	directores = new ArrayList<>(Arrays.asList(movie.getWriter().split(", ")));
		} else {
		 	directores =new ArrayList<>(Arrays.asList("Desconocido"));
		}
		model.addAttribute("movie", movie);
		model.addAttribute("directores", directores);
		model.addAttribute("title", "MoviesOnline | " + movie.getTitle());
		return "showMovie";
	}
	
	@GetMapping("/filterMovie")
	public ResponseEntity<List<Movie>> filter(@RequestParam("titulo") String titulo, @RequestParam("tipo") String tipo, Model model) {
		List<Movie> movies = movieService.getMoviesByNameAndType(titulo, tipo);
		return ResponseEntity.ok(movies);
	}
	
}

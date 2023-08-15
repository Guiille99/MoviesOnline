package com.moviesonline.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	// Los atributos deberán llamarse igual y deben ser del mismo tipo que viene en la API
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Rated")
	private String rated;
	@JsonProperty("Released")
	private String released;
	@JsonProperty("Runtime")
	private String runtime;
	@JsonProperty("Genre")
	private String genre;
	@JsonProperty("Director")
	private String director;
	@JsonProperty("Writer")
	private String writer;
	@JsonProperty("Actors")
	private String actors;
	@JsonProperty("Plot")
	private String plot;
	@JsonProperty("Language")
	private String language;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Awards")
	private String awards;
	@JsonProperty("Poster")
	private String poster;
	@JsonProperty("Metascore")
	private String metascore;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Production")
	private String production;
	@JsonProperty("imdbID")
	private String imdbID;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("imdbRating")
	private String imdbRating;
	@JsonProperty("imdbVotes")
	private String imdbVotes;
	@JsonProperty("Ratings")
	private List<Rating> ratings;
	@JsonProperty("totalSeasons")
	private String totalSeasons;

}

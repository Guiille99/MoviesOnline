package com.moviesonline.app.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.moviesonline.app.model.Movie;

@Repository
public interface IMovie {
	Movie getMovieByTitle(String title);
	Movie getMovieById(String id);
	ArrayList<Movie> getMoviesByNameAndType(String title, String type);
	Movie getData(String params);
	ArrayList<Movie> getDataList(String params);
}

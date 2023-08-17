package com.moviesonline.app.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesonline.app.model.Movie;
import com.moviesonline.app.repository.IMovie;

@Service
@Component
public class MovieImpl implements IMovie {
	public static String filter;
	@Value("${API_KEY}")
	private String apiKey;
	private String apiURL = "http://www.omdbapi.com/?apikey=";
	
	public Movie getMovieByTitle(String title) {
		filter = "&t="+title;
		return getData(filter);
	}
	
	public Movie getMovieById(String id) {
		filter = "&i="+id;
		return getData(filter);
	}
	
	@Override
	public ArrayList<Movie> getMoviesByNameAndType(String title, String type) {
		filter = "&s=" + title + "&type=" + type;
		return getDataList(filter);
	}
	
	
	public Movie getData(String params) {
		StringBuilder dataStr = new StringBuilder();
		try {
			URL url = new URL(apiURL + apiKey + params);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    dataStr.append(inputLine);
                }
                in.close();

                System.out.println("Respuesta de la API:");
                System.out.println(dataStr.toString());
			} else {
				System.out.println("Error en la llamada a la API");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Pasamos los datos a JSON
		ObjectMapper objectMapper = new ObjectMapper();
		Movie movie = new Movie();
        try {
			movie = objectMapper.readValue(dataStr.toString(), Movie.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return movie;
	}
	
	public ArrayList<Movie> getDataList(String params) {
		StringBuilder dataStr = new StringBuilder();
		try {
			URL url = new URL(apiURL + apiKey + params);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    dataStr.append(inputLine);
                }
                in.close();

                System.out.println("Respuesta de la API:");
                System.out.println(dataStr.toString());
			} else {
				System.out.println("Error en la llamada a la API");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Pasamos los datos a JSON
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Movie> movies = new ArrayList<>();
		JsonNode movieList = null;
		try {
			movieList = objectMapper.readTree(dataStr.toString()).get("Search");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			for (JsonNode m : movieList) {
				Movie movie = objectMapper.readValue(m.toString(), Movie.class);
				movies.add(movie);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return movies;
	}


	
	
}

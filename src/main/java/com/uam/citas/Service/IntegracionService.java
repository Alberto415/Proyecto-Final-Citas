package com.uam.citas.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uam.citas.DTO.TableroDTO;
import com.uam.citas.Mapper.IntegracionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class IntegracionService {

    private final WebClient clienteHttp;
    private final IntegracionMapper mapeador;
    private final ObjectMapper objectMapper;

    @Value("${tmdb.api.key}")
    private String llaveTmdb;

    public IntegracionService(WebClient clienteHttp, IntegracionMapper mapeador) {
        this.clienteHttp = clienteHttp;
        this.mapeador = mapeador;
        this.objectMapper = new ObjectMapper();
    }

    public TableroDTO obtenerTablero(String pais) {
        try {
            // API 1 - RestCountries: buscar país dinámico
            String urlPais = "https://restcountries.com/v3.1/name/" + pais;
            String jsonPais = clienteHttp.get()
                    .uri(urlPais)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            JsonNode nodoPais = objectMapper.readTree(jsonPais);

            // Obtener latitud y longitud del país para el clima
            double lat = nodoPais.get(0).path("latlng").get(0).asDouble();
            double lon = nodoPais.get(0).path("latlng").get(1).asDouble();

            // API 2 - Open-Meteo: pronóstico 7 días con lat/lon del país
            String urlClima = "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=" + lat
                    + "&longitude=" + lon
                    + "&daily=temperature_2m_max,temperature_2m_min,weather_code,precipitation_probability_max"
                    + "&timezone=auto";
            String jsonClima = clienteHttp.get()
                    .uri(urlClima)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // API 3 - TheMovieDB: 10 películas populares
            String urlPeliculas = "https://api.themoviedb.org/3/movie/popular"
                    + "?api_key=" + llaveTmdb
                    + "&language=es-MX&page=1";
            String jsonPeliculas = clienteHttp.get()
                    .uri(urlPeliculas)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return mapeador.armarTablero(
                    mapeador.convertirPronostico(objectMapper.readTree(jsonClima)),
                    mapeador.convertirPais(nodoPais),
                    mapeador.convertirPeliculas(objectMapper.readTree(jsonPeliculas))
            );

        } catch (Exception e) {
            throw new RuntimeException("Error al consultar APIs: " + e.getMessage());
        }
    }
}
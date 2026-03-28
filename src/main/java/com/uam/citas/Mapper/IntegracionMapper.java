package com.uam.citas.Mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.uam.citas.DTO.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IntegracionMapper {

    public List<ClimaDTO> convertirPronostico(JsonNode json) {
        JsonNode fechas       = json.path("daily").path("time");
        JsonNode tempsMax     = json.path("daily").path("temperature_2m_max");
        JsonNode tempsMin     = json.path("daily").path("temperature_2m_min");
        JsonNode codigos      = json.path("daily").path("weather_code");
        JsonNode humedades    = json.path("daily").path("precipitation_probability_max");

        List<ClimaDTO> lista = new ArrayList<>();
        for (int i = 0; i < fechas.size(); i++) {
            double max = tempsMax.get(i).asDouble();
            double min = tempsMin.get(i).asDouble();
            int codigo = codigos.get(i).asInt();
            int humedad = humedades.get(i).asInt();

            String desc;
            if (codigo == 0)       desc = "Despejado";
            else if (codigo <= 3)  desc = "Parcialmente nublado";
            else if (codigo <= 67) desc = "Lluvioso";
            else                   desc = "Condiciones especiales";

            lista.add(new ClimaDTO(
                    fechas.get(i).asText(),
                    Math.round(((max + min) / 2) * 10.0) / 10.0,
                    humedad,
                    desc
            ));
        }
        return lista;
    }

    public PaisDTO convertirPais(JsonNode json) {
        JsonNode pais = json.isArray() ? json.get(0) : json;
        PaisDTO dto = new PaisDTO();
        dto.setNombre(pais.path("name").path("common").asText());
        dto.setCapital(pais.path("capital").isArray()
                ? pais.path("capital").get(0).asText() : "N/A");
        dto.setRegion(pais.path("region").asText());
        dto.setArea(pais.path("area").asDouble());
        dto.setUrlBandera(pais.path("flags").path("png").asText());
        return dto;
    }

    public List<PeliculaDTO> convertirPeliculas(JsonNode json) {
        JsonNode resultados = json.path("results");
        List<PeliculaDTO> lista = new ArrayList<>();
        int limite = Math.min(10, resultados.size());
        for (int i = 0; i < limite; i++) {
            JsonNode p = resultados.get(i);
            PeliculaDTO dto = new PeliculaDTO();
            dto.setTitulo(p.path("title").asText());
            dto.setSinopsis(p.path("overview").asText());
            dto.setFechaEstreno(p.path("release_date").asText());
            dto.setPopularidad(p.path("popularity").asDouble());
            dto.setUrlCartel("https://image.tmdb.org/t/p/w500"
                    + p.path("poster_path").asText());
            lista.add(dto);
        }
        return lista;
    }

    public TableroDTO armarTablero(List<ClimaDTO> pronostico, PaisDTO pais, List<PeliculaDTO> peliculas) {
        TableroDTO tablero = new TableroDTO();
        tablero.setMensaje("Tablero UAM Citas - datos en tiempo real");
        tablero.setPronostico(pronostico);
        tablero.setPais(pais);
        tablero.setPeliculas(peliculas);
        return tablero;
    }
}
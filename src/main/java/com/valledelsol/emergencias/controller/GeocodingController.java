package com.valledelsol.emergencias.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/geocoding")
public class GeocodingController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/reverse")
    public ResponseEntity<String> obtenerDireccion(
            @RequestParam Double lat,
            @RequestParam Double lon
    ) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/reverse")
                .queryParam("format", "jsonv2")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("zoom", 18)
                .queryParam("addressdetails", 1)
                .queryParam("accept-language", "es")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "ValleDelSolApp/1.0");
        headers.set("Accept", "application/json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return ResponseEntity
                .status(response.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.getBody());
    }
}

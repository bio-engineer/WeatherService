package ru.bioengineer.weatherservice.domain.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public final class RestService {

    private final RestTemplate restTemplate = new RestTemplate();

    public final <T> T sendGet(String url, Class<T> responseType) throws RestClientException {
        return restTemplate.getForObject(URI.create(url), responseType);
    }

    public final <T> T sendPost(String url, Object request, Class<T> responseType) throws RestClientException {
        return restTemplate.postForEntity(URI.create(url), request, responseType).getBody();
    }
}

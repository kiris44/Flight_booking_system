package com.flightBookingSys.central_api.connector;

import com.flightBookingSys.central_api.model.Contents;
import com.flightBookingSys.central_api.model.GeminiApiRequest;
import com.flightBookingSys.central_api.model.GeminiApiResponse;
import com.flightBookingSys.central_api.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GeminiConnector {

    RestTemplate restTemplate;
    @Autowired
    public GeminiConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${gemini.genai.url}")
    String geminiUrl;

    @Value("${gemini.token}")
    String token;

    String geminiTokenHeader = "X-goog-api-key";

    public GeminiApiResponse callGeminiGenAIApiEndpoint(String prompt){
        Part part = new Part(prompt);
        List<Part> parts = new ArrayList();
        parts.add(part);
        Contents contents = new Contents();
        contents.setParts(parts);
        List<Contents> contentsList = new ArrayList<>();
        contentsList.add(contents);
        GeminiApiRequest geminiApiReqBody = new GeminiApiRequest();
        geminiApiReqBody.setContents(contentsList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(geminiTokenHeader, token);
        httpHeaders.add("Content-Type","application/json");

        HttpEntity<GeminiApiRequest> httpEntity = new HttpEntity<>(geminiApiReqBody, httpHeaders);
        ResponseEntity<GeminiApiResponse> response = restTemplate.exchange(geminiUrl, HttpMethod.POST, httpEntity, GeminiApiResponse.class);
        return response.getBody();
    }
}

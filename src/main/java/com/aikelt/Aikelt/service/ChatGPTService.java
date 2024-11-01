package com.aikelt.Aikelt.service;

import com.aikelt.Aikelt.dto.GameSolvedResponse;
import com.aikelt.Aikelt.dto.InitialSentenceResponse;
import com.aikelt.Aikelt.model.EstonianWord;
import com.aikelt.Aikelt.model.Game;
import com.aikelt.Aikelt.model.Word;
import com.aikelt.Aikelt.repository.JdbcWordRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGPTService {

    private final WebClient webClient;
    private final String apiKey;
    private final String model;
    private final String apiUrl;

    private final JdbcWordRepository wordRepository;

    public ChatGPTService(WebClient.Builder webClientBuilder,
                          @Value("${openai.api.key}") String apiKey,
                          @Value("${openai.model}") String model,
                          @Value("${openai.api.url}") String apiUrl, JdbcWordRepository wordRepository) {
        this.wordRepository = wordRepository;
        this.webClient = webClientBuilder.baseUrl(apiUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        this.apiKey = apiKey;
        this.model = model;
        this.apiUrl = apiUrl;
    }

    public JSONObject completion(String content) {

        JSONObject promptObject = new JSONObject();
        promptObject.put("role", "system");
        promptObject.put("content", content);

        JSONArray messagesArray = new JSONArray();
        messagesArray.put(promptObject);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        requestBody.put("messages", messagesArray);
        requestBody.put("temperature", 1.0);

        Mono<String> responseMono = webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .body(Mono.just(requestBody.toString()), String.class)
                .retrieve()
                .onStatus(status -> status.isError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("API call failed: " + errorBody))))
                .bodyToMono(String.class);

        String response = responseMono.block(); // Consider handling this asynchronously

        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            throw new RuntimeException("Error parsing response from API", e);
        }
    }


    public InitialSentenceResponse getSentenceOld(Integer length, Game.GameType  type, String[] seeds) {


        if (length < 3 || length > 10) {
            throw new IllegalArgumentException("Length must be between 3 and 10 characters.");
        }

        String seedString = String.join(", ", seeds);

        // Construct the content string using the given parameters
        String content = type.name() + " sentence " + length + " words, using meaning: " + seedString;

        // Call the completion method with the prepared content
        JSONObject jsonResponse = this.completion(content);

        // Extract the generated sentence and total tokens from the JSON response
        try {
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");
            if (choicesArray.length() > 0) {
                JSONObject firstChoice = choicesArray.getJSONObject(0);
                String messageContent = firstChoice.getJSONObject("message").getString("content");
                int totalTokens = jsonResponse.getJSONObject("usage").getInt("total_tokens");
                return new InitialSentenceResponse (messageContent.trim().replaceAll("[\"\\.]$", ""), totalTokens); // Return both content and total tokens
            }
        } catch (JSONException e) {
            throw new RuntimeException("Error processing the response from the completion method", e);
        }

        throw new RuntimeException("No valid sentence was generated.");
    }

    public InitialSentenceResponse getSentence(Integer length, Game.GameType  type) {



        if (length < 3 || length > 10) {
            throw new IllegalArgumentException("Length must be between 3 and 10 characters.");
        }

        //String seedString = wordRepository.findSample(length,10);

        String seedString = "una, dos, tres";

        // Construct the content string using the given parameters
        //String content = type.name() + " sentence " + length + " words, using meaning: " + seedString;
        String content = "You can use these Estonian meanings: " + seedString + " to make a sentence " + length + " words long in " + type.name() + " language. Check twice that the final message is just a sentence in " + type.name() + " language.";



        // Call the completion method with the prepared content
        JSONObject jsonResponse = this.completion(content);

        // Extract the generated sentence and total tokens from the JSON response
        try {
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");
            if (choicesArray.length() > 0) {
                JSONObject firstChoice = choicesArray.getJSONObject(0);
                String messageContent = firstChoice.getJSONObject("message").getString("content");
                int totalTokens = jsonResponse.getJSONObject("usage").getInt("total_tokens");
                return new InitialSentenceResponse (messageContent.trim().replaceAll("[\"\\.]$", ""), totalTokens); // Return both content and total tokens
            }
        } catch (JSONException e) {
            throw new RuntimeException("Error processing the response from the completion method", e);
        }

        throw new RuntimeException("No valid sentence was generated.");
    }


    public GameSolvedResponse resolve(String original, String human_response, Game.GameType type) {

        String content = "";

        if (type == Game.GameType.ESTONIAN) {

            content = "\n" +
                    "Original: " + original + "\n" +
                    "Human_response: " + human_response + "\n" +
                    "\n" +
                    "Translate original to English in Translation.\n" +
                    "For each word in original, include its basic form and rate how closely the meaning matches any word in the Human_response on a scale from 0 to 10, where 10 indicates a direct or close conceptual match.\n" +
                    "Return just formated JSON\n" +
                    "\n" +
                    "{\n" +
                    "\t\"Translation\": \"\",\n" +
                    "\t\"Estonian_words\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"Word\": \"\",\n" +
                    "\t\t\t\"Basic_Form\": \"\",\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}";
        }

        if (type == Game.GameType.ENGLISH) {

            content = "\n" +
                    "Original: " + original + "\n" +
                    "Human_response: " + human_response + "\n" +
                    "\n" +
                    "Translate original to Estonian in Translation.\n" +
                    "For each word in Translation, include its basic form and rate how closely the meaning matches any word in the Human_response on a scale from 0 to 10, where 10 indicates a direct or close conceptual match.\n" +
                    "Return just formated JSON\n" +
                    "\n" +
                    "{\n" +
                    "\t\"Translation\": \"\",\n" +
                    "\t\"Estonian_words\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"Word\": \"\",\n" +
                    "\t\t\t\"Basic_Form\": \"\",\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}";
        }

        JSONObject jsonResponse = this.completion(content);

        JSONObject jsonObject = new JSONObject(jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content"));

        JSONArray estonianWordsArray = jsonObject.getJSONArray("Estonian_words");

        List<EstonianWord> estonianWordsList = new ArrayList<>();

        for (int i = 0; i < estonianWordsArray.length(); i++) {
            JSONObject wordData = estonianWordsArray.getJSONObject(i);

            // Create a new JSONObject for each word and add required fields
            EstonianWord estonianWord = new EstonianWord();
            estonianWord.setWord(wordData.getString("Word"));
            estonianWord.setBasicForm(wordData.getString("Basic_Form"));
            estonianWord.setAchieved(wordData.getInt("Achieved"));

            // Add to the list
            estonianWordsList.add(estonianWord);
        }

        GameSolvedResponse response = new GameSolvedResponse();
        response.setAlmostAllData(jsonObject.getString("Translation"), estonianWordsList);

        response.setTokens(jsonResponse.getJSONObject("usage").getInt("total_tokens"));

        return response;

    }
}








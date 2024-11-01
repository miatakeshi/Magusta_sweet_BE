package com.aikelt.Aikelt.controller;

import com.aikelt.Aikelt.dto.SentenceRequest;
import com.aikelt.Aikelt.dto.TranslationRequest;
import com.aikelt.Aikelt.model.Game;
import com.aikelt.Aikelt.service.GameService;
import com.aikelt.Aikelt.service.OpenAIService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService){
        this.openAIService = openAIService;
    }

    @PostMapping("/translateSentence")
    public  void   translateSentence(@RequestBody TranslationRequest request) {
        String language = request.getLanguage();
        String sentence = request.getSentence();

        System.out.println("Translate: " + openAIService.translateSentence(language,sentence));
    }


    @PostMapping("/wordAnalysis")
    public Mono<String> wordAnalysis(@RequestBody Map<String, String> body) {
        // Extract the "word" from the request body
        String word = body.get("word");

        // This is how you open choices
        return Mono.fromCallable(() -> {
            JSONObject analysis = openAIService.wordAnalysis(word);

            String content = analysis.getJSONArray("choices")
                    .getJSONObject(0) // Get the first choice
                    .getJSONObject("message") // Access the "message" object
                    .getString("content"); // Get the content as a string

            JSONObject contentJson = new JSONObject(content);



            return analysis.toString();
        });
    }


    @PostMapping("/scoreResponse")
    public Mono<String> scoreResponse(@RequestBody Map<String, String> body) {
        String language = body.get("language");
        String translation = body.get("translation");
        String humanResponse = body.get("humanResponse");

        // Assuming scoreResponse returns a JSONObject, you can convert it to a string for the response.
        JSONObject response = openAIService.scoreResponse(language, translation, humanResponse);

        return Mono.just(response.toString());
    }

}


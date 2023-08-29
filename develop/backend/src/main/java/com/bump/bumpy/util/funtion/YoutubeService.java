package com.bump.bumpy.util.funtion;

import com.bump.bumpy.util.dto.youtube.SearchListKeywordDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class YoutubeService {
    private static final String API_KEY = "AIzaSyDkzCviTKEBN-xue3AUWJBOesyq59UQq3k";

    public SearchListKeywordDto searchListKeyword(String keyword){
        /**
         * GET https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=surfing&key=[YOUR_API_KEY] HTTP/1.1
         * Accept: application/json
         */
        // HTTP GET request

        WebClient webClient = WebClient.builder()
                .baseUrl("https://youtube.googleapis.com/youtube/v3/search")
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("maxResults", "25");
        params.put("q", keyword);
        params.put("type", "video");
        params.put("key", API_KEY);

        SearchListKeywordDto searchListKeywordDto = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .query("part={part}&maxResults={maxResults}&q={q}&key={key}")
                        .build(params))
                .retrieve()
                .bodyToMono(SearchListKeywordDto.class)
                .block();



        return searchListKeywordDto;
    }

}

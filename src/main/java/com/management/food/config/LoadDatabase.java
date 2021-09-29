package com.management.food.config;

import com.management.food.entity.Food;
import com.management.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(FoodRepository foodRepository) throws ParseException {

        long startTime = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String key = "4514d4ff87db4cf8b776";
        int start = 1;
        int end = 5;
        HttpEntity entity = new HttpEntity(headers);
        URI uri = URI.create("http://openapi.foodsafetykorea.go.kr/api/sample/COOKRCP01/json/" + start + "/" + end);

        ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());

        JSONObject value = (JSONObject) jsonObject.get("COOKRCP01");
        JSONArray row = (JSONArray) value.get("row");

        JSONObject data;


        for (int i = 0; i < end; i++) {
            data = (JSONObject) row.get(i);
            foodRepository.save(
                    new Food(null, data.get("RCP_NM").toString().trim(), data.get("RCP_PARTS_DTLS").toString().replace("\n", " "), data.get("ATT_FILE_NO_MK").toString(), 10000)
            );
        }

        long endTime = System.currentTimeMillis();

        return args -> {
            log.info("PreLoading Time : " + (endTime - startTime) + "ms");
        };
    }
}

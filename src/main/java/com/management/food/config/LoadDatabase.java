package com.management.food.config;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Application;
import com.management.food.entity.Food;
import com.management.food.entity.Lecture;
import com.management.food.entity.User;
import com.management.food.repository.ApplicationRepository;
import com.management.food.repository.FoodRepository;
import com.management.food.repository.LectureRepository;
import com.management.food.repository.UserRepository;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LectureRepository lectureRepository, ApplicationRepository applicationRepository, FoodRepository foodRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) throws ParseException {

        //Fixme 관리자 권한 계정 삭제
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        User user = User.builder().email("admin").password(passwordEncoder.encode("admin")).name("김현중")
//                .roles(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .roles(list)
                .build();

        userRepository.save(user);

        long startTime = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String key = "4514d4ff87db4cf8b776";
        int start = 1;
        int end = 999; // total_count = 1318
        HttpEntity entity = new HttpEntity(headers);
        URI uri = URI.create("http://openapi.foodsafetykorea.go.kr/api/4514d4ff87db4cf8b776/COOKRCP01/json/" + start + "/" + end);

        ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());

        JSONObject value = (JSONObject) jsonObject.get("COOKRCP01");
        JSONArray row = (JSONArray) value.get("row");

        JSONObject data;


        for (int i = 0; i < end; i++) {
            data = (JSONObject) row.get(i);
            foodRepository.save(
                    new Food(data.get("RCP_NM").toString().trim(), data.get("RCP_PARTS_DTLS").toString().replace("\n", " "), data.get("ATT_FILE_NO_MK").toString(), 10000, data.get("RCP_PAT2").toString())
            );
        }

        Lecture lecture = new Lecture(null, "첫번째 강의", "집", LocalDate.now(), LocalTime.now(), 2, user, foodRepository.findById(1L).orElseThrow(NotFoundResourceException::new));

        lectureRepository.save(lecture);

        Application application = new Application(user, lecture);
        applicationRepository.save(application);


        long endTime = System.currentTimeMillis();

        return args -> {
            log.info("PreLoading Time : " + (endTime - startTime) + "ms");
        };
    }
}

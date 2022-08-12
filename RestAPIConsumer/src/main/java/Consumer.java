import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws JsonProcessingException {

        //Get request
        RestTemplate restTemplate = new RestTemplate();

        String getUrl = "https://reqres.in/api/users/2";

        RegresSingleUser user = restTemplate.getForObject(getUrl, RegresSingleUser.class);
        System.out.println("Email: " + user.getData().getEmail());
        System.out.println("Text: " + user.getSupport().getText());



//        //Post request
//        RestTemplate restTemplate1 = new RestTemplate();
//
//        Map<String, String> jsonToSend = new HashMap<>();
//        jsonToSend.put("name", "cristi");
//        jsonToSend.put("job", "it");
//
//        HttpEntity<Map<String, String>> request2 = new HttpEntity<>(jsonToSend);
//
//        String postUrl = "https://reqres.in/api/users";
//        String response = restTemplate1.postForObject(postUrl, request2, String.class);
//        System.out.println(response);
    }
}

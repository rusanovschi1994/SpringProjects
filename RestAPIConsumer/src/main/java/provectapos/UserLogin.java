package provectapos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import provectapos.measure.Piece;

import java.util.*;

public class UserLogin {

    private static Piece Piece;

    public static void main(String[] args) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("username", "rusanovschi1994@mail.ru");
        jsonToSend.put("password", "Micr0invest");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        String url = "http://zamolxis.cloudapp.net:60034/api/v1/framework/common/login";

        Login response = restTemplate.postForObject(url, request, Login.class);
        System.out.println(response);


        //###############################################################################
        String theUrl = "http://zamolxis.cloudapp.net:60034/api/v1/stock/article/select";
        RestTemplate restTemplate2 = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + response.getToken());
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response2 = restTemplate2.exchange(theUrl, HttpMethod.GET, entity, String.class);

            String responseEntity = response2.getBody();
            System.out.println(responseEntity);

            //Parsam JSON-ul primit
            ObjectMapper mapper = new ObjectMapper();
            JsonNode nodes = mapper.readTree(responseEntity);

             for (JsonNode node: nodes) {

                    StringBuilder builder = new StringBuilder();
                     builder
                             .append(node.findValues("Name"))
                             .append(" - ")
                             .append(node.findValues("Price"))
                             .append(" - ")
                             .append(node.findValues("ArticleMeasureType")) ;
                     System.out.println(builder);

//                 String st = node.findValues("Name").toString();
//                 System.out.println(st);
            }
    }
}

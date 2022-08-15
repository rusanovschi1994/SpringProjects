package provectapos;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import provectapos.measure.Piece;

import java.util.HashMap;
import java.util.Map;

public class UserLogin {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("username", "rusanovschi1994@mail.ru");
        jsonToSend.put("password", "Micr0invest");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        String url = "http://zamolxis.cloudapp.net:60034/api/v1/framework/common/login";

        Login response = restTemplate.postForObject(url, request, Login.class);
        System.out.println(response);

//        //getMeasure
//        String getMeasureUrl = "http://zamolxis.cloudapp.net:60034/api/v1/stock/measure/select";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + response.getToken());
//
//
//        restTemplate.exchange(getMeasureUrl, headers, Piece.class);

        String theUrl = "http://zamolxis.cloudapp.net:60034/api/v1/stock/measure/select";
        RestTemplate restTemplate2 = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + response.getToken());
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response2 = restTemplate2.exchange(theUrl, HttpMethod.GET, entity, String.class);
            System.out.println(response2);
        }
        catch (Exception eek) {
            System.out.println("** Exception: "+ eek.getMessage());
        }


    }
}

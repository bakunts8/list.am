package API;

import ApiPages.Pojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiDataCheckingTest {


    private Pojo createPojoObject(int id, String email, String first_name, String last_name) {
        Pojo pojo = new Pojo();
        pojo.setId(id);
        pojo.setEmail(email);
        pojo.setFirst_name(first_name);
        pojo.setLast_name(last_name);
        return pojo;
    }

    @Test
    public void serializationDeserialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String pojoJson = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(createPojoObject(2, "janet.weaver@reqres.in", "Janet", "Weaver"));
        System.out.println("Serialization...");
        System.out.println(pojoJson);

        System.out.println("DeSerialization...");
        Pojo pojo = objectMapper.readValue(pojoJson, Pojo.class);
        System.out.println("id : " + pojo.getId());
        System.out.println("email : " + pojo.getEmail());
        System.out.println("first name : " + pojo.getFirst_name());
        System.out.println("last name : " + pojo.getLast_name());

        RequestSpecification request = given();
        request.baseUri("https://reqres.in");
    }
}

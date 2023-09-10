package ApiPages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pojo {


    private String email;
    private String name;
    private String gender;
    private String status;

    ObjectMapper objectMapper = new ObjectMapper();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String serializationPojo(String email, String name, String gender, String status) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        setEmail(email);
        setGender(gender);
        setName(name);
        setStatus(status);
        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }

    public String serializationPojo(String email, String name) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        setEmail(email);
        setName(name);
        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }

    public void deserialization(String email, String name, String gender, String status) throws JsonProcessingException {

        System.out.println("DeSerialization...");
        Pojo pojo = objectMapper.readValue(serializationPojo("lenox_lewis@wbc.com", "Lenox Lewis", "male", "active"), Pojo.class);
        System.out.println("name : " + pojo.getName());
        System.out.println("email : " + pojo.getEmail());
        System.out.println("gender : " + pojo.getGender());
        System.out.println("status : " + pojo.getStatus());
    }
}

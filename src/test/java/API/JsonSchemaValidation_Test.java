package API;

import ApiPages.Reqres;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV3;
import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;

public class JsonSchemaValidation_Test {

    @Test
    public void checkJsonSchemaValidation() {
        Reqres page = new Reqres();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder()
                        .setDefaultVersion(DRAFTV4).freeze()).freeze();

        given().when().get(page.getBaseURL() + page.getListUsersEndpoint()).then().assertThat()
                .body(matchesJsonSchemaInClasspath("json-validation.json").using(jsonSchemaFactory));
    }

    @Test
    public void checkJsonStaticSchemaValidation() {
        Reqres page = new Reqres();
        JsonSchemaValidator.settings = settings().with()
                .jsonSchemaFactory(JsonSchemaFactory.newBuilder()
                        .setValidationConfiguration(ValidationConfiguration.newBuilder()
                                .setDefaultVersion(DRAFTV3).freeze()).freeze()).and()
                .with().checkedValidation(false);

        given().when().get(page.getBaseURL() + page.getSingleUserEndpoint()).then().assertThat()
                .body(matchesJsonSchemaInClasspath("json-validation.json"));
    }
}

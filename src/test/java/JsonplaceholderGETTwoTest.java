import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderGETTwoTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
            private final String USERS = "users";

    @Test
    public void jsonplaceholderReadAllUsers(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();

        List<String> names = json.getList("name");
//
//        names.stream()
//                .forEach(System.out::println);

        assertEquals(10, names.size());
    }

    @Test
    public void jsonplaceholderReadOneUser(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();


//        Assertions.assertEquals(200,response.statusCode());
//
        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));

 //       System.out.println(response.asString());

    }

    //Path Variables

    @Test
    public void jsonplaceholderReadOneUserWithPathVariable(){
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(BASE_URL + "/" + USERS + "/{userId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));

    }

    //Query param
    @Test
    public void jsonplaceholderReadOneUserWithQueryParams(){
        Response response = given()
                .queryParam("username","Bret")
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();
        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));

    }

}

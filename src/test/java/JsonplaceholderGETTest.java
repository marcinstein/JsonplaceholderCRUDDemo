import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderGETTest {

    @Test
    public void jsonplaceholderReadAllUsers(){

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        List<String> names = json.getList("name");

        names.stream()
                .forEach(System.out::println);

        Assertions.assertEquals(10, names.size());
    }

    @Test
    public void jsonplaceholderReadOneUser(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1");

        Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));

 //       System.out.println(response.asString());

    }

    //Path Variables

    @Test
    public void jsonplaceholderReadOneUserWithPathVariable(){
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get("https://jsonplaceholder.typicode.com/users/{userId}");
        Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));

    }

    //Query param
    @Test
    public void jsonplaceholderReadOneUserWithQueryParams(){
        Response response = given()
                .queryParam("username","Bret")
                .when()
                .get("https://jsonplaceholder.typicode.com/users");
        Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("Leanne Graham", json.getList("name").get(0));
        Assertions.assertEquals("Bret", json.getList("username").get(0));
        Assertions.assertEquals("Sincere@april.biz", json.getList("email").get(0));

    }

}

package api;

import dto.response.UserResponse;
import dto.User;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class ApiUserSteps {
    @Step("Создать уникального пользователя с логином {user.email}, паролем {user.password} и именем {user.name}")
    public static UserResponse createUser(User user, int statusCode) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(statusCode)
                .extract().response()
                .getBody().as(UserResponse.class);
    }

    @Step("Удалить пользователя")
    public static UserResponse deleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202)
                .extract().response()
                .getBody().as(UserResponse.class);
    }
}
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    public static final String COURIER_CREATE_PATH = "api/v1/courier";
    public static final String COURIER_DELETE_PATH = "api/v1/courier/";
    public static final String COURIER_LOGIN_PATH = "api/v1/courier/login";

    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(COURIER_CREATE_PATH)
                .then();
    }
    public ValidatableResponse delete(String id){
        return given()
                .spec(getSpec())
                .when()
                .delete(COURIER_DELETE_PATH+id)
                .then();
    }

    public ValidatableResponse login(CourierCredentials courierCredentials){
        return given()
                .spec(getSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }


}

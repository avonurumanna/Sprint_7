import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client{

    public static final String ORDER_PATH = "api/v1/orders";
    public static final String CANCEL_ORDER_PATH ="api/v1/orders/cancel";

    public ValidatableResponse create(Order order){
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }
    public ValidatableResponse getAll(){
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
    public ValidatableResponse delete(int track){
        return given()
                .spec(getSpec())
                .queryParam("track",track)
                .when()
                .put(CANCEL_ORDER_PATH)
                .then();
    }






}

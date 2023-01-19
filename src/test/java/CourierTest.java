import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierTest {

    private Courier courier;
    private CourierClient courierClient;
    private String id;

    @Before
    public void setUp(){
        courier = CourierGenerator.getValidCourier();
        courierClient = new CourierClient();
        id=null;
    }

    @Test
    @Description("Create Courier and check response code")
    public void courierCanBeCreated(){
        ValidatableResponse response = courierClient.create(courier);
        response.statusCode(SC_CREATED)
                .and()
                .assertThat().body("ok", equalTo(true));
       ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id").toString();

    }

    @Test
    @Description("Create Courier twice with the same parameters and check that the error in response is received")
    public void courierCanNotBeCreatedTwiceWithTheSameLogin(){
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id").toString();
        ValidatableResponse secondCreateResponse = courierClient.create(courier);
        secondCreateResponse.statusCode(SC_CONFLICT)
                      .and()
                      .assertThat().body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @Description("Create Courier by request which doesn't contain password and check that the error in response is received")
    public void courierCanNotBeCreatedWithoutPassword(){ //поменять
        courier.setPassword("");
        ValidatableResponse response = courierClient.create(courier);
        response.statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @Description("Login with valid credentials and check response code")
    public void courierCanLoginWithValidCredentials(){
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        loginResponse.statusCode(SC_OK)
                     .and()
                     .assertThat().body("id", notNullValue());
        id = loginResponse.extract().path("id").toString();
    }

    @Test
    @Description("Login with invalid password and check that the error in response is received")
    public void courierCanNotLoginWithIncorrectPassword(){
        courierClient.create(courier);
        ValidatableResponse firstLoginResponse = courierClient.login(CourierCredentials.from(courier));
        id = firstLoginResponse.extract().path("id").toString();
        courier.setPassword(courier.getPassword()+"w");
        ValidatableResponse secondLoginResponse = courierClient.login(CourierCredentials.from(courier));
        secondLoginResponse.statusCode(SC_NOT_FOUND)
                           .and()
                           .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    @Description("Login without password and check that the error in response is received")
    public void courierCanNotLoginWithoutPassword(){
        courierClient.create(courier);
        ValidatableResponse firstLoginResponse =courierClient.login(CourierCredentials.from(courier));
        id = firstLoginResponse.extract().path("id").toString();
        courier.setPassword("");
        ValidatableResponse secondLoginResponse = courierClient.login(CourierCredentials.from(courier));
        secondLoginResponse.statusCode(SC_BAD_REQUEST)
                           .and()
                           .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @Description("Login with nonExistent credentials and check that the error in response is received")
    public void nonExistentCourierCanNotLogin(){
        ValidatableResponse secondLoginResponse = courierClient.login(CourierCredentials.from(courier));
        secondLoginResponse.statusCode(SC_NOT_FOUND)
                           .and()
                           .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void cleanUp(){
        if(id != null) {
            courierClient.delete(id);
        }
    }



}
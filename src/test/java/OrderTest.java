import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class OrderTest {
    OrderClient orderClient;

    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }
    @Test
    @Description("Get orders and check that response body contains object orders")
    public void ordersCanBeReceived(){
        ValidatableResponse response = orderClient.getAll();
        response.statusCode(SC_OK)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}

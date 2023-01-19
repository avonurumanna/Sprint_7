import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class ParameterizedOrderTest {
    private Order order;
    private String description;
    private OrderClient orderClient;
    private int track;

    @Before
    public void setUp(){
        orderClient = new OrderClient();
        track=0;
    }
    public ParameterizedOrderTest(Order order, String description) {
        this.order=order;
        this.description = description;
    }

    //убрать хардкод
    @Parameterized.Parameters(name="{1}")
    public static Object[] getOrderData() {
        return new Object[][] {
                {OrderGenerator.getOrderWithoutColors(),"test create order without color"},
                {OrderGenerator.getOrderWithOneColor(),"test create order with grey color"},
                {OrderGenerator.getOrderWithBothColors(),"test create order with both colors"},
        };
    }

    //добавить валидашку ответа
    @Test
    @Description("Create Order and check response code")
    public void orderCanBeCreated() {
        ValidatableResponse response = orderClient.create(order);
        response.statusCode(SC_CREATED)
                .and()
                .assertThat().body("track", notNullValue());
        track = response.extract().path("track");

    }
    @After
    public void cleanUp(){
        if(track != 0) {
            orderClient.delete(track);
        }
    }




}

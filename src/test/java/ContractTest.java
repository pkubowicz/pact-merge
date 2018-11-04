import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Provider", port = "9051")
public class ContractTest {
    private static final String BASE_URL = "http://localhost:9051";

    @Pact(consumer="Consumer")
    public RequestResponsePact defineEndpoint(PactDslWithProvider builder) {
        return builder
                .uponReceiving("request for user")
                .path("/user")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("is user")
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "defineEndpoint")
    public void callEndpoint() throws Exception {
        Request.Get(BASE_URL + "/user").execute();
    }
}

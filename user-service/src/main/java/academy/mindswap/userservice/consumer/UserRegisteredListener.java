package academy.mindswap.userservice.consumer;

import academy.mindswap.userservice.dto.OrderProcessedPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredListener {

    public void onMessageReceived(String message) throws JsonProcessingException {
        System.out.println("Received [" + message + "]");

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<OrderProcessedPayload> mapType = new TypeReference<>(){};
        OrderProcessedPayload payload = objectMapper.readValue(message, mapType);

        System.out.println("Total: " + payload.total());

        // TODO: verify balance if success send email via sendGrid
    }
}

package academy.mindswap.userservice.consumer;

import academy.mindswap.userservice.dto.OrderProcessedPayload;
import academy.mindswap.userservice.model.User;
import academy.mindswap.userservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class UserRegisteredListener {

    // TODO : It will be service in the future
    UserRepository userRepository;

    @Autowired
    UserRegisteredListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onMessageReceived(String message) throws JsonProcessingException {
        System.out.println("Received [" + message + "]");

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<OrderProcessedPayload> mapType = new TypeReference<>(){};
        OrderProcessedPayload payload = objectMapper.readValue(message, mapType);

        System.out.println("Total: " + payload.total());

        Optional<User> optionalUser = userRepository.findByEmail(payload.email());

        if(optionalUser.isEmpty()) {
            // TODO: send error to queue of rabbitmq to revert changes using SAGA Pattern
            return;
        }

        User user = optionalUser.get();

        if (user.getBalance() - payload.total() < 0) {
            // TODO: send error to queue of rabbitmq to revert changes using SAGA Pattern
            return;
        }

        user.setBalance(user.getBalance() - payload.total());
        // TODO: send email via sendGrid
    }
}

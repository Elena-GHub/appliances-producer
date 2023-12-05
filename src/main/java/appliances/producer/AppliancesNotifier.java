package appliances.producer;

import static appliances.producer.AmqpConfiguration.MICROWAVE_EXCHANGE;
import static appliances.producer.AmqpConfiguration.OVEN_EXCHANGE;
import static appliances.producer.AmqpConfiguration.ROUTING_KEY;
import static appliances.producer.AmqpConfiguration.WASHING_MACHINE_EXCHANGE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@RequiredArgsConstructor
public class AppliancesNotifier implements Notifier {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void sendOvenNotification() {
    log.info("Sending notification");
    String message = "Message: The oven beeped! Your dinner is waiting";
    rabbitTemplate.convertAndSend(OVEN_EXCHANGE, ROUTING_KEY, message);
    log.info("Notification sent successfully");
  }

  @Override
  public void sendWashingMachineNotification() {
    log.info("Sending notification");
    String message = "Message: The washing machine beeped! Your clothes are clean";
    rabbitTemplate.convertAndSend(WASHING_MACHINE_EXCHANGE, ROUTING_KEY, message);
    log.info("Notification sent successfully");
  }

  @Override
  public void sendMicrowaveNotification() {
    log.info("Sending notification");
    String message = "Message: The microwave beeped! Your hot chocolate is waiting";
    rabbitTemplate.convertAndSend(MICROWAVE_EXCHANGE, ROUTING_KEY, message);
    log.info("Notification sent successfully");
  }
}


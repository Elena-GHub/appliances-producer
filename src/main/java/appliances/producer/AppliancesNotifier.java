package appliances.producer;

import static appliances.producer.AmqpConfiguration.APPLIANCES_EXCHANGE;
import static appliances.producer.AmqpConfiguration.MICROWAVE_ROUTING_KEY;
import static appliances.producer.AmqpConfiguration.OVEN_ROUTING_KEY;
import static appliances.producer.AmqpConfiguration.WASHING_MACHINE_ROUTING_KEY;

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
    String message = "Message: The oven beeped with 1 EXCHANGE and oven ROUTING KEY! Your dinner is waiting";
    rabbitTemplate.convertAndSend(APPLIANCES_EXCHANGE, OVEN_ROUTING_KEY, message);
    log.info("Notification sent successfully");
  }

  @Override
  public void sendWashingMachineNotification() {
    log.info("Sending notification");
    String message = "Message: The washing machine beeped with 1 EXCHANGE and washing machine ROUTING KEY! Your clothes are clean";
    rabbitTemplate.convertAndSend(APPLIANCES_EXCHANGE, WASHING_MACHINE_ROUTING_KEY, message);
    log.info("Notification sent successfully");
  }

  @Override
  public void sendMicrowaveNotification() {
    log.info("Sending notification");
    String message = "Message: The microwave beeped with 1 EXCHANGE and microwave ROUTING KEY! Your hot chocolate is waiting";
    rabbitTemplate.convertAndSend(APPLIANCES_EXCHANGE, MICROWAVE_ROUTING_KEY, message);
    log.info("Notification sent successfully");
  }
}


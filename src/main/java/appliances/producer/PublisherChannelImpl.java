package appliances.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PublisherChannelImpl implements PublisherChannel {

  @Override
  public void sendOvenNotification() {
    log.info("Sending notification");
    String message = "Message: The oven beeped! Your dinner is waiting";
//    outputChannel.output().send(MessageBuilder.withPayload(message).build());
    log.info("Notification sent successfully");
  }

  @Override
  public void sendWashingMachineNotification() {
    log.info("Sending notification");
    String message = "Message: The washing machine beeped! Your clothes are clean";
//    outputChannel.output().send(MessageBuilder.withPayload(message).build());
    log.info("Notification sent successfully");
  }

  @Override
  public void sendMicrowaveNotification() {
    log.info("Sending notification");
    String message = "Message: The microwave beeped! Your hot chocolate is waiting";
//    outputChannel.output().send(MessageBuilder.withPayload(message).build());
    log.info("Notification sent successfully");
  }
}


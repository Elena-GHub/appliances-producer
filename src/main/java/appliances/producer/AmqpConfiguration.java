package appliances.producer;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@Slf4j
public class AmqpConfiguration {

  public static final String APPLIANCES_EXCHANGE = "appliances_exchange";
  public static final String OVEN_BEEPED_QUEUE = "oven_beeped_queue";
  public static final String WASHING_MACHINE_BEEPED_QUEUE = "washing_machine_beeped_queue";
  public static final String MICROWAVE_BEEPED_QUEUE = "microwave_beeped_queue";
  private static final String DLX_EXCHANGE = "x-dead-letter-exchange";
  private static final String DLX_EXCHANGE_VALUE = "DLX";
  private static final String DLQ = "x-dead-letter-routing-key";
  private static final String DLQ_SUFFIX = ".dlq";
  public static final String WASHING_MACHINE_ROUTING_KEY = "washing_machine_routing_key";
  public static final String OVEN_ROUTING_KEY = "oven_routing_key";
  public static final String MICROWAVE_ROUTING_KEY = "microwave_routing_key";

  @Bean
  public TopicExchange appliancesExchange() {
    return new TopicExchange(APPLIANCES_EXCHANGE);
  }
  @Bean
  Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  DirectExchange dlxExchange() {
    return new DirectExchange(DLX_EXCHANGE_VALUE);
  }

  @Bean
  Notifier consumerChannel(RabbitTemplate rabbitTemplate) {
    return new AppliancesNotifier(rabbitTemplate);
  }

  @Bean
  Binding microwaveBeepedDlqBinding(DirectExchange dlxExchange, Queue microwaveBeepedDlq) {
    return BindingBuilder.bind(microwaveBeepedDlq).to(dlxExchange).with(MICROWAVE_BEEPED_QUEUE);
  }

  // Washing Machine related beans
  @Bean
  public Queue washingMachineBeepedQueue(){
    return new Queue(WASHING_MACHINE_BEEPED_QUEUE, true, false, false, Map.of(
      DLX_EXCHANGE, DLX_EXCHANGE_VALUE,
      DLQ, WASHING_MACHINE_BEEPED_QUEUE));
  }

  @Bean
  public Binding washingMachineBeepedBinding(TopicExchange appliancesExchange, Queue washingMachineBeepedQueue) {
    return BindingBuilder.bind(washingMachineBeepedQueue).to(appliancesExchange).with(WASHING_MACHINE_ROUTING_KEY);
  }

  @Bean
  Queue washingMachineBeepedDlq() {
    return new Queue(WASHING_MACHINE_BEEPED_QUEUE + DLQ_SUFFIX);
  }

  @Bean
  Binding washingMachineBeepedDlqBinding(DirectExchange dlxExchange, Queue washingMachineBeepedDlq) {
    return BindingBuilder.bind(washingMachineBeepedDlq).to(dlxExchange).with(WASHING_MACHINE_BEEPED_QUEUE);
  }

  // Oven related beans
  @Bean
  Queue ovenBeepedQueue() {
    return new Queue(OVEN_BEEPED_QUEUE, true, false, false, Map.of(
      DLX_EXCHANGE, DLX_EXCHANGE_VALUE,
      DLQ, OVEN_BEEPED_QUEUE));
  }

  @Bean
  Binding ovenBeepedBinding(TopicExchange appliancesExchange, Queue ovenBeepedQueue) {
    return BindingBuilder.bind(ovenBeepedQueue).to(appliancesExchange).with(OVEN_ROUTING_KEY);
  }

  @Bean
  Queue ovenBeepedDlq() {
    return new Queue(OVEN_BEEPED_QUEUE + DLQ_SUFFIX);
  }

  @Bean
  Binding ovenBeepedDlqBinding(DirectExchange dlxExchange, Queue ovenBeepedDlq) {
    return BindingBuilder.bind(ovenBeepedDlq).to(dlxExchange).with(OVEN_BEEPED_QUEUE);
  }

  // Microwave related beans
  @Bean
  Queue microwaveBeepedQueue() {
    return new Queue(MICROWAVE_BEEPED_QUEUE, true, false, false, Map.of(
      DLX_EXCHANGE, DLX_EXCHANGE_VALUE,
      DLQ, MICROWAVE_BEEPED_QUEUE));
  }

  @Bean
  Binding microwaveBeepedBinding(TopicExchange appliancesExchange, Queue microwaveBeepedQueue) {
    return BindingBuilder.bind(microwaveBeepedQueue).to(appliancesExchange).with(MICROWAVE_ROUTING_KEY);
  }

  @Bean
  Queue microwaveBeepedDlq() {
    return new Queue(MICROWAVE_BEEPED_QUEUE + DLQ_SUFFIX);
  }
}

package appliances.producer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
  value = "/appliances",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE
)
public class ApplianceController {

  private final PublisherChannel publisher;

  public ApplianceController(PublisherChannel publisher) {
    this.publisher = publisher;
  }

  @RequestMapping("/oven")
  @PostMapping
  public ResponseEntity<?> sendOvenNotification() {
    publisher.sendOvenNotification();
    return ResponseEntity.accepted().build();
  }

  @RequestMapping("/washing-machine")
  @PostMapping
  public ResponseEntity<?> sendWashingMachineNotification() {
    publisher.sendWashingMachineNotification();
    return ResponseEntity.accepted().build();
  }

  @RequestMapping("/microwave")
  @PostMapping
  public ResponseEntity<?> sendMicrowaveNotification() {
    publisher.sendMicrowaveNotification();
    return ResponseEntity.accepted().build();
  }
}


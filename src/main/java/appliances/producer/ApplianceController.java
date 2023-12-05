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

  private final Notifier notifier;

  public ApplianceController(Notifier notifier) {
    this.notifier = notifier;
  }

  @RequestMapping("/oven")
  @PostMapping
  public ResponseEntity<?> sendOvenNotification() {
    notifier.sendOvenNotification();
    return ResponseEntity.accepted().build();
  }

  @RequestMapping("/washing-machine")
  @PostMapping
  public ResponseEntity<?> sendWashingMachineNotification() {
    notifier.sendWashingMachineNotification();
    return ResponseEntity.accepted().build();
  }

  @RequestMapping("/microwave")
  @PostMapping
  public ResponseEntity<?> sendMicrowaveNotification() {
    notifier.sendMicrowaveNotification();
    return ResponseEntity.accepted().build();
  }
}


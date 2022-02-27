package xxx.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xxx.model.Struct;

@RestController
public class WebController {

  @Value("${spring.kafka.producer.topic}")
  private String topicName;
  
  @Autowired
  private KafkaTemplate<String, String> producer;
  
  @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> generateTestData() {
    
    Struct struct = new Struct();
    struct.setName(topicName);
    
    producer.send(topicName, struct.toJson().toString());
    
    return ResponseEntity.status(HttpStatus.OK).body("Request captured " + new Date());
  }
}

package xxx.kafka;

import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class KafkaDriver {

  private static final Logger logger = Logger.getLogger(KafkaDriver.class.getSimpleName());

  @KafkaListener(id = "topic-id", groupId = "topic-group", topics = "topic-1")
  public void consume(ConsumerRecord<String, String> consumerRecord, Acknowledgment ack) {
    
    String payload = consumerRecord.value();
    JsonObject json = JsonParser.parseString(payload).getAsJsonObject();
    
    logger.info("Received message " + json.toString());
    
    ack.acknowledge();
  }
}
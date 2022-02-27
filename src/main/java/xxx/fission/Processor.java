package xxx.fission;

import java.util.logging.Logger;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import io.fission.Context;
import io.fission.Function;

@SuppressWarnings("rawtypes")
public class Processor implements Function {

  private static final Logger logger = Logger.getLogger(Processor.class.getSimpleName());
  
  
  @Override
  public ResponseEntity<?> call(RequestEntity req, Context context) {
    
    logger.info("Function invoked");
    
    return ResponseEntity.ok("Working");
  }  
}

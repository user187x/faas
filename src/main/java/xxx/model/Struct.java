package xxx.model;

import java.util.UUID;
import com.google.gson.JsonObject;

public class Struct {

  private String id = UUID.randomUUID().toString();
  private String name = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public JsonObject toJson() {
    
    JsonObject json = new JsonObject();
    
    json.addProperty("id", id);
    json.addProperty("name", name);
    
    return json;
  }
}

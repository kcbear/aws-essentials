package poc.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Date;

public class Hello implements RequestHandler<Hello.HelloEvent, Hello.ResponseClass> {

  @Override
  public ResponseClass handleRequest(HelloEvent helloEvent, Context context) {
    System.out.println("print the event: " + helloEvent);
    context.getLogger().log("AWS log: " + helloEvent);

    return new ResponseClass(
        String.format(
            "values: %s, %s, %s ",
            helloEvent.getKey1(), helloEvent.getKey2(), helloEvent.getKey3()));
  }

  public static class HelloEvent {

    /*
    My event from AWS:

      {
        "key1": "value1",
        "key2": "value2",
        "key3": "value3"
      }


     */

    private String key1;
    private String key2;
    private String key3;

    public String getKey1() {
      return key1;
    }

    public void setKey1(String key1) {
      this.key1 = key1;
    }

    public String getKey2() {
      return key2;
    }

    public void setKey2(String key2) {
      this.key2 = key2;
    }

    public String getKey3() {
      return key3;
    }

    public void setKey3(String key3) {
      this.key3 = key3;
    }
  }

  public static class ResponseClass {
    private String result;
    private Date now;

    public ResponseClass(String result) {
      this.result = result;
      now = new Date();
    }

    public String getResult() {
      return result;
    }

    public void setResult(String result) {
      this.result = result;
    }

    public Date getNow() {
      return now;
    }

    public void setNow(Date now) {
      this.now = now;
    }
  }

  public String sayHello() {
    System.out.println("say a hello in console log");
    return "it's a hello result";
  }
}

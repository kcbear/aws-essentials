package poc.aws.lambda;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.nio.ByteBuffer;
import java.util.Date;

public class Hello implements RequestHandler<Hello.HelloEvent, Hello.ResponseClass> {

  public String sayHello() {
    System.out.println("say a hello in console log");

    return "it's a hello result";
  }

  public String helloOrchestration() {

    AWSLambda lambda =
        AWSLambdaClientBuilder.standard().withCredentials(AwsKeyPair.credentials).build();

    InvokeRequest request = new InvokeRequest();
    request
        .withFunctionName("HelloEventHandler")
        .withPayload(
            "     {"
                + "        \"key1\": \"hello\","
                + "        \"key2\": \"lambda\","
                + "        \"key3\": \"orchestration\""
                + "      }");

    try {
      InvokeResult invokeResult = lambda.invoke(request);
      System.out.println(invokeResult.getStatusCode());

      ByteBuffer byteBuffer = invokeResult.getPayload();

      String rawJson = null;

      try {
        rawJson = new String(byteBuffer.array(), "UTF-8");
      } catch (Exception e) {

      }

      System.out.println(rawJson);
    } catch (Exception e) {

    }

    return "check the log of HelloEventHandler function";
  }

  @Override
  public ResponseClass handleRequest(HelloEvent helloEvent, Context context) {
    System.out.println("print the event: " + helloEvent);
    context
        .getLogger()
        .log(
            "AWS log: "
                + String.format(
                    "values: %s, %s, %s ",
                    helloEvent.getKey1(), helloEvent.getKey2(), helloEvent.getKey3()));

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

    // require having an empty param constructor
    public HelloEvent() {}

    public HelloEvent(String key1, String key2, String key3) {
      this.key1 = key1;
      this.key2 = key2;
      this.key3 = key3;
    }

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
}

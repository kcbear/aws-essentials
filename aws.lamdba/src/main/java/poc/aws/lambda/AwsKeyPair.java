package poc.aws.lambda;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

public class AwsKeyPair {

  // TODO: update as your own access key and secret key
  public static final String key = "something";
  public static final String secret = "something";

  public static final AWSCredentialsProvider credentials =
      new AWSStaticCredentialsProvider(new BasicAWSCredentials(AwsKeyPair.key, AwsKeyPair.secret));
}

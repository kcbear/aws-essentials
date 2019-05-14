This poc project is to experience AWS Lambda environment

3 functions are created:
1. sayHello - a dummy function takes no input and return a string as output
2. handleRequest - takes in a JSON with a definition as HelloEvent and return a string based on the it
3. helloOrchestration - invoke as an end point then invoke another existing Lambda function with payload and get the result

============
sayHello
============

Expected execution result:

"it's a hello result"

Expected log output:

START RequestId: a51c9e48-f180-4b18-8400-3e2e920c7948 Version: $LATEST
say a hello in console log
END RequestId: a51c9e48-f180-4b18-8400-3e2e920c7948
REPORT RequestId: a51c9e48-f180-4b18-8400-3e2e920c7948	Duration: 7.93 ms	Billed Duration: 100 ms 	Memory Size: 512 MB	Max Memory Used: 81 MB


Expected output in AWS Lambda CloudWatch Logs:
2019-05-09

06:44:56
START RequestId: a51c9e48-f180-4b18-8400-3e2e920c7948 Version: $LATEST

06:44:56
say a hello in console log

06:44:56
END RequestId: a51c9e48-f180-4b18-8400-3e2e920c7948

06:44:56
REPORT RequestId: a51c9e48-f180-4b18-8400-3e2e920c7948	Duration: 7.93 ms	Billed Duration: 100 ms Memory Size: 512 MB	Max Memory Used: 81 MB

----------------------------------------------------------------------------------------

============
HelloEventHandler
============

Expected execution result:

{
  "result": "values: value1, value2, value3 ",
  "now": 1557384179279
}


Expected Log output:
START RequestId: 1a9bc7cb-eca7-4d18-9e84-a36aa68c748a Version: $LATEST
print the event: poc.aws.lambda.Hello$HelloEvent@610694f1
AWS log: poc.aws.lambda.Hello$HelloEvent@610694f1END RequestId: 1a9bc7cb-eca7-4d18-9e84-a36aa68c748a
REPORT RequestId: 1a9bc7cb-eca7-4d18-9e84-a36aa68c748a	Duration: 360.65 ms	Billed Duration: 400 ms 	Memory Size: 512 MB	Max Memory Used: 86 MB


Cloud watch:
2019-05-09

06:42:59
START RequestId: 1a9bc7cb-eca7-4d18-9e84-a36aa68c748a Version: $LATEST

06:42:59
print the event: poc.aws.lambda.Hello$HelloEvent@610694f1

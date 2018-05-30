# EBS_CAPACITY_ADJUSTER

AWS Lambda Function to adjust capacity of an EBS Volume

Use the gradle task 'fatJar' to build a fat jar.

The Handler is 'com.echovue.ebsVolumeAdjust.EBSVolumeAdjuster::handleRequest'

You will need to run the Lambda with an IAM Role which includes:

- AmazonEC2ReadOnlyAccess
- AWSLambdaBasicExecutionRole

package com.github.toddnguyen47.awssts;

import com.amazonaws.auth.STSSessionCredentialsProvider;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

public class AwsSts {
  public AmazonSimpleEmailService initializeSimpleEmailServiceClient(String awsRegion) {

    AWSSecurityTokenService stsService =
        AWSSecurityTokenServiceClientBuilder.standard().withRegion(awsRegion).build();
    STSSessionCredentialsProvider credentialsProviderAutoRefresh =
        new STSSessionCredentialsProvider(stsService);
    AmazonSimpleEmailService client =
        AmazonSimpleEmailServiceClientBuilder.standard()
            .withCredentials(credentialsProviderAutoRefresh)
            .withRegion(awsRegion)
            .build();

    if (client == null) {
      // TODO: Log error here!
    }

    return client;
  }
}

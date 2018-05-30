package com.echovue.ebsVolumeAdjust;

import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.echovue.ebsVolumeAdjust.model.EBSCapacityConfigEvent;
import com.amazonaws.services.ec2.model.Volume;

import java.util.ArrayList;
import java.util.Collection;

public class EBSVolumeAdjuster implements RequestHandler<EBSCapacityConfigEvent, String> {
    final AmazonEC2 amazonEC2 = AmazonEC2ClientBuilder.defaultClient();

    @Override
    public String handleRequest(EBSCapacityConfigEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        DescribeVolumesRequest request = new DescribeVolumesRequest();
        Collection<String> volumes = new ArrayList<>();
        volumes.add("vol-00c1c7dfb4342d058");
        request.setVolumeIds(volumes);
        DescribeVolumesResult volumeResults = amazonEC2.describeVolumes(request);
        for (Volume volume : volumeResults.getVolumes()) {
            Integer size = volume.getSize();
            logger.log("Size: " + size);
        }
        return "Found";
    }
}

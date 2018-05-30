package com.echovue.ebsVolumeAdjust;

import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeVolumesModificationsRequest;
import com.amazonaws.services.ec2.model.ModifyVolumeRequest;
import com.amazonaws.services.ec2.model.ModifyVolumeResult;
import com.amazonaws.services.ec2.model.VolumeModification;
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
    public String handleRequest(EBSCapacityConfigEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        DescribeVolumesRequest request = new DescribeVolumesRequest();
        Collection<String> volumes = new ArrayList<>();
        volumes.add(event.getVolumeId());
        request.setVolumeIds(volumes);
        ModifyVolumeResult result = null;
        DescribeVolumesResult volumeResults = amazonEC2.describeVolumes(request);
        for (Volume volume : volumeResults.getVolumes()) {
            Integer size = volume.getSize();

            logger.log("Size: " + size + "\n");
            logger.log("Max Size: " + event.getMaxSize() + "\n");
            logger.log("Volume State: " + volume.getState() + "\n");

            if (size < event.getMaxSize() && volume.getState().equals("in-use")) {
                ModifyVolumeRequest modification = new ModifyVolumeRequest();
                modification.setSize(size + event.getIncrement());
                modification.setVolumeId(volume.getVolumeId());
                result = amazonEC2.modifyVolume(modification);
            } else {
                if (size == event.getMaxSize()) {
                    logger.log("Max volume size reached.\n");
                }
                if (!volume.getState().equals("in-use")) {
                    logger.log("Cannot increase size of volume in " + volume.getState() + " state.\n");
                }
            }
        }
        if (result != null) {
            return result.toString();
        } else {
            return "No modification";
        }
    }
}

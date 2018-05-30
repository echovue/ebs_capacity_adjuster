package com.echovue.ebsVolumeAdjust.model;

import com.amazonaws.services.lambda.runtime.events.ConfigEvent;

public class EBSCapacityConfigEvent extends ConfigEvent {
    private String volumeId;
    private String region;
    private Integer size;

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

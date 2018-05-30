package com.echovue.ebsVolumeAdjust.model;

import com.amazonaws.services.lambda.runtime.events.ConfigEvent;

public class EBSCapacityConfigEvent extends ConfigEvent {
    private String volumeId;
    private String region;
    private Integer maxSize;
    private Integer increment;

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

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }
}

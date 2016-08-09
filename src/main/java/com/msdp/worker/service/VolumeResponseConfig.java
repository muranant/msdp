package com.msdp.worker.service;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "volumeResponseConfig")
public class VolumeResponseConfig {

    @XmlAttribute(name = "accountName")
    String accountName;

    @XmlElement(name = "volumes", required = true)
    protected List<VolumeConfig> volumes;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<VolumeConfig> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<VolumeConfig> volumes) {
        this.volumes = volumes;
    }
}

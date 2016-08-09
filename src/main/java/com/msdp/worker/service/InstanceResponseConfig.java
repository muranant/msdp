package com.msdp.worker.service;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "instanceResponseConfig")
public class InstanceResponseConfig {

    @XmlAttribute(name = "accountName")
    String accountName;

    @XmlElement(name = "instances", required = true)
    protected List<InstanceConfig> instances;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<InstanceConfig> getInstances() {
        return instances;
    }

    public void setInstances(List<InstanceConfig> instances) {
        this.instances = instances;
    }
}

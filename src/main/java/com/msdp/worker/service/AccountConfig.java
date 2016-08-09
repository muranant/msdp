package com.msdp.worker.service;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "accountConfig")
public class AccountConfig {

    @XmlAttribute(name = "accountName")
    String name;

    @XmlAttribute(name = "type")
    String cloud;

    @XmlAttribute(name = "id")
    String id;

    @XmlAttribute(name = "key")
    String key;

    @XmlAttribute(name = "numInstances")
    String numInstances;

    @XmlAttribute(name = "numVolumes")
    String numVolumes;

    @XmlAttribute(name = "numSnapshots")
    String numSnapshots;

    public String getNumInstances() {
        return numInstances;
    }

    public void setNumInstances(String numInstances) {
        this.numInstances = numInstances;
    }

    public String getNumVolumes() {
        return numVolumes;
    }

    public void setNumVolumes(String numVolumes) {
        this.numVolumes = numVolumes;
    }

    public String getNumSnapshots() {
        return numSnapshots;
    }

    public void setNumSnapshots(String numSnapshots) {
        this.numSnapshots = numSnapshots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

package com.msdp.worker.service;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "instanceConfig")
public class InstanceConfig {

    @XmlAttribute(name = "name")
    String name;

    @XmlAttribute(name = "time")
    String time;

    @XmlAttribute(name = "id")
    String id;

    @XmlAttribute(name = "status")
    String status;

    @XmlAttribute(name = "upTime")
    String upTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}

package com.msdp.worker.service;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "volumeConfig")
public class VolumeConfig {

    @XmlAttribute(name = "time")
    String time;

    @XmlAttribute(name = "id")
    String id;

    @XmlAttribute(name = "status")
    String status;

    @XmlAttribute(name = "size")
    String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}

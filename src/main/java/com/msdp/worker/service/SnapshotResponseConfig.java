package com.msdp.worker.service;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "snapshotResponseConfig")
public class SnapshotResponseConfig {

    @XmlAttribute(name = "accountName")
    String accountName;

    @XmlElement(name = "snapshots", required = true)
    protected List<SnapshotConfig> snapshots;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<SnapshotConfig> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<SnapshotConfig> snapshots) {
        this.snapshots = snapshots;
    }
}

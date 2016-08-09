package com.msdp.worker.service;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "accountResponseConfig")
public class AccountsResponseConfig {

    @XmlAttribute(name = "numRecords")
    String numRecords;

    @XmlElement(name = "accounts", required = true)
    protected List<AccountConfig> accounts;

    public String getNumRecords() {
        return numRecords;
    }

    public void setNumRecords(String numRecords) {
        this.numRecords = numRecords;
    }

    public List<AccountConfig> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountConfig> accounts) {
        this.accounts = accounts;
    }
}

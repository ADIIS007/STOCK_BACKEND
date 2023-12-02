package com.stock.project.dto;

public class SettingsPage {
    private Long netInFlow;
    private Long netOutFlow;
    private Long netWorth;
    private String name;
    private Boolean verifiedEmail;
    private Boolean verifiedPhoneNumber;

    public SettingsPage(Long netInFlow, Long netOutFlow, Long netWorth, String name, Boolean verifiedEmail, Boolean verifiedPhoneNumber) {
        this.netInFlow = netInFlow;
        this.netOutFlow = netOutFlow;
        this.netWorth = netWorth;
        this.name = name;
        this.verifiedEmail = verifiedEmail;
        this.verifiedPhoneNumber = verifiedPhoneNumber;
    }

    public Long getNetInFlow() {
        return netInFlow;
    }

    public void setNetInFlow(Long netInFlow) {
        this.netInFlow = netInFlow;
    }

    public Long getNetOutFlow() {
        return netOutFlow;
    }

    public void setNetOutFlow(Long netOutFlow) {
        this.netOutFlow = netOutFlow;
    }

    public Long getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Long netWorth) {
        this.netWorth = netWorth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(Boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public Boolean getVerifiedPhoneNumber() {
        return verifiedPhoneNumber;
    }

    public void setVerifiedPhoneNumber(Boolean verifiedPhoneNumber) {
        this.verifiedPhoneNumber = verifiedPhoneNumber;
    }
}

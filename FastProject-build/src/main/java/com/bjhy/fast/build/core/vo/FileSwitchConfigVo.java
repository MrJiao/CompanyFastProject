package com.bjhy.fast.build.core.vo;

/**
 * Create by: Jackson
 */
public class FileSwitchConfigVo {

    String id;

    String toFtpId;
    String fromFtpId;

    String toUrl;
    String fromUrl;
    
    String eventName;
    String filterSuffixId;

    public String getFilterSuffixId() {
        return filterSuffixId;
    }

    public void setFilterSuffixId(String filterSuffixId) {
        this.filterSuffixId = filterSuffixId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getToFtpId() {
        return toFtpId;
    }

    public void setToFtpId(String toFtpId) {
        this.toFtpId = toFtpId;
    }

    public String getFromFtpId() {
        return fromFtpId;
    }

    public void setFromFtpId(String fromFtpId) {
        this.fromFtpId = fromFtpId;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.tangcheng.app.domain.vo;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-08-01  19:26
 */
public class GpsVO {
    private Double longitude;
    private Double latitude;

    public GpsVO() {
    }

    public GpsVO(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

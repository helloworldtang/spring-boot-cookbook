package com.tangcheng.app.domain.query;

public class PathParam {

    private String type;
    private String uri;
    private String queryString;
    private String userId;

    public PathParam(String type, String uri, String queryString, String userId) {
        this.type = type;
        this.uri = uri;
        this.queryString = queryString;
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getUserId() {
        return userId;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "PathParam{" +
                "type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", queryString='" + queryString + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
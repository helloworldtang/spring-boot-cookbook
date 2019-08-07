package com.tangcheng.app.domain.query;

public class Result {

    private String userId;
    private Integer pageId;
    private Integer pageSize;
    private String auth;

    public Result() {
    }

    public Result(String userId, int pageId, int pageSize, String auth) {

        this.userId = userId;
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.auth = auth;
    }

    public Result(String userId, Integer pageId, Integer pageSize) {//如果pageId，pageSize如果使用基本类型，传入null时会报java.lang.NullPointerException

        this.userId = userId;
        this.pageId = pageId;
        this.pageSize = pageSize;
    }

    public Result(String userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (userId != null ? !userId.equals(result.userId) : result.userId != null) return false;
        if (pageId != null ? !pageId.equals(result.pageId) : result.pageId != null) return false;
        if (pageSize != null ? !pageSize.equals(result.pageSize) : result.pageSize != null) return false;
        return auth != null ? auth.equals(result.auth) : result.auth == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (pageId != null ? pageId.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "userId='" + userId + '\'' +
                ", pageId=" + pageId +
                ", pageSize=" + pageSize +
                ", auth='" + auth + '\'' +
                '}';
    }
}
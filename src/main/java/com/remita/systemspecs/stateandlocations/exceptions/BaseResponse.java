package com.remita.systemspecs.stateandlocations.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class BaseResponse<T> {

    private String status;

    private String message;

    @JsonProperty(value = "data")
    private T data;

    private Map<String, Object> _meta;
    private Map<String, Object> _links;

    public Map<String, Object> get_meta() {
        return _meta;
    }

    public void set_meta(Map<String, Object> _meta) {
        this._meta = _meta;
    }

    public Map<String, Object> get_links() {
        return _links;
    }

    public void set_links(Map<String, Object> _links) {
        this._links = _links;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ("BaseResponse{" + "status='" + status + '\'' + ", message='" + message + '\'' + ", data=" + data + ", _meta=" + '}');
    }
}




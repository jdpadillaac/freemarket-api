package com.freemarket.global.models;

public class JsonResp {
    
    public Boolean success = true;
    public String message;
    public Object data;
    public Object error;

    public JsonResp(Boolean success, String message, Object data, Object error) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }



}
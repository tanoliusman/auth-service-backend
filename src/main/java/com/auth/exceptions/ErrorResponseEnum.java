package com.auth.exceptions;

public enum ErrorResponseEnum {

    USER_NOT_FOUND(1,"User not found","Send valid username"),
    USERNAME_ALREADY_TAKEN(2,"Username already taken", "please try with another usernam"),
    EMAIL_ALREADY_TAKEN(3,"Email already in use", "Please send another email"),
    AUTHENTIACTION_ERROR(4,"Username and password are invalid", "Please send correct values");

    int id;
    String reason;
    String resolve;

    ErrorResponseEnum(int id, String reason, String resolve){
        this.id = id;
        this.reason = reason;
        this.resolve = resolve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }
}

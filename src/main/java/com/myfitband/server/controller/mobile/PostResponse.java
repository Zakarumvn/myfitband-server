package com.myfitband.server.controller.mobile;

class PostResponse {
    private final String error;

    private final boolean ok;

    private final String response;

    private PostResponse(String error, boolean ok, String response) {
        this.error = error;
        this.ok = ok;
        this.response = response;
    }

    static PostResponse ok() {
        return PostResponse.ok(null);
    }

    static PostResponse ok(String responseText) {
        return new PostResponse(null, true, responseText);
    }

    static PostResponse error(String errorText) {
        return new PostResponse(errorText, false, null);
    }

    public String getError() {
        return error;
    }

    public boolean isOk() {
        return ok;
    }

    public String getResponse() {
        return response;
    }
}

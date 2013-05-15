package idu0200.kliendid.common;

public class AjaxErrorResponse {
    public boolean isError = true;
    public String errorMessage = "";

    public AjaxErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

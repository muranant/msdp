/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.exceptions;

public enum AppWebErrorCodes {

    ACCOUNT_LOGIN_FAILED("AccountLoginFailed"), ACCOUNT_UNAUTHORIZED("AccountUnauthorized"), UNAUTHORIZED("Unauthorized"), INTERNAL_ERROR("InternalServerError"), RESOURCE_NOT_AVAILABLE(
            "ResourceNotAvailable"), NO_USER_NAME("NoUserCredentialsPresent"), REMOTE_EXECUTION_ERROR("RemoteExecutionError"), LOGIN_ERROR("LoginError"), UPLOAD_ERROR("UploadError"), DOWNLOAD_ERROR(
            "DownloadError"), MISSING_OPNAME("OpnameMissing"), BAD_REQUEST("InvalidInput"), FORBIDDEN("Forbidden");

    private final String value;

    AppWebErrorCodes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AppWebErrorCodes fromValue(String v) {
        for (AppWebErrorCodes c : AppWebErrorCodes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

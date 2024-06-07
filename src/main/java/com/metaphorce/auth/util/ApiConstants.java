package com.metaphorce.auth.util;

/**
 * Interface for API endpoint paths and versions. This interface is used to maintain
 * consistent endpoint paths throughout the application, facilitating updates and maintenance.
 * All fields in an interface are implicitly public, static, and final.
 */
public interface ApiConstants {

    /**
     * Interface for authentication-related constants.
     */
    interface Auth {
        /**
         * The base URL for authentication endpoints.
         */
        String BASE_URL = "/auth/v1";

        /**
         * The URL for the login endpoint. This is the full path used for user authentication.
         */
        String LOGIN_URL = "/login";
    }

}
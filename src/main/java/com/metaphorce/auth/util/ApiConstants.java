package com.metaphorce.auth.util;

/**
 * Constants for API endpoints and versions.
 */
public final class ApiConstants {

    /**
     * Private constructor to prevent instantiation.
     */
    private ApiConstants() {
        // Private constructor to prevent instantiation
    }

    /**
     * The base URL for all API endpoints.
     */
    public static final String API_BASE_URL = "/api";

    /**
     * The current version of the API.
     */
    public static final String API_VERSION = "v1";

    // Routes for Login

    /**
     * The base URL for authentication endpoints.
     */
    public static final String AUTH_BASE_URL = API_BASE_URL + "/" + API_VERSION + "/auth";

    /**
     * The URL for the login endpoint.
     */
    public static final String AUTH_LOGIN_URL = AUTH_BASE_URL + "/login";
}

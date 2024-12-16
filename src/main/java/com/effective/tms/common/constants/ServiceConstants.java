package com.effective.tms.common.constants;

import com.effective.tms.common.exception.TmsException;

public final class ServiceConstants {

    private ServiceConstants() {
        throw new TmsException("Init constants");
    }

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String CLAIM_NAME = "scope";
    public static final String CANT_RETRIEVE_USER_DETAILS = "Failed to retrieve UserDetails object from Authentication object";
    public static final String ACCOUNT_ALREADY_EXISTS = "Account with username = %s already exists";
    public static final String USER_DOESNT_EXIST = "User with username = %s doesn't exist";
    public static final String USER_PROFILE_BY_ID_ALREADY_EXISTS = "User profile with id = %d already exists";
    public static final String USER_PROFILE_BY_NAME_ALREADY_EXISTS = "User profile with username = %s already exists";
    public static final String TASK_DOESNT_EXIST = "Task with id = %d doesn't exist";
}

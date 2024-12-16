package com.effective.tms.common.constants;

import com.effective.tms.common.exception.TmsException;

public final class FacadeConstants {

    private FacadeConstants() {
        throw new TmsException("Init constants");
    }

    public static final String CANT_RETRIEVE_CUR_USER = "Can't retrieve current user";
    public static final String CANT_FIND_TASK = "Can't find task with id = %d";
    public static final String CANT_ADD_COMMENT = "User with id = %d doesn't have permission to add a comment to the task with id = %d";
    public static final String CANT_FIND_COMMENT = "Can't find comment with id = %d";
    public static final String CANT_DELETE_COMMENT = "User with id = %d doesn't have permission to delete the comment with id = %d";
    public static final String CANT_EDIT_COMMENT = "User with id = %d doesn't have permission to edit the comment with id = %d";
    public static final String CANT_SEE_COMMENTS = "User with id = %d doesn't have permission to see comments to the task with id = %d";
    public static final String CANT_ADD_TASK = "User with id = %d doesn't have permission to add a task";
    public static final String CANT_EDIT_TASK = "User with id = %d doesn't have permission to edit the task with id = %d";
    public static final String CANT_DELETE_TASK = "User with id = %d doesn't have permission to delete a task";
}

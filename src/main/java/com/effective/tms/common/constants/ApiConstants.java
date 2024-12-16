package com.effective.tms.common.constants;

import com.effective.tms.common.exception.TmsException;

public final class ApiConstants {

    private ApiConstants() {
        throw new TmsException("Init constants");
    }

    public static final String BEARER_KEY = "bearer-key";

    public static final String BASE_URL = "localhost:8080/api/v1";

    public static final String AUTH_MAPPING = "/api/v1/auth";
    public static final String ACCOUNTS_MAPPING = "/api/v1/accounts";
    public static final String TASKS_MAPPING = "/api/v1/tasks";
    public static final String COMMENTS_MAPPING = "/api/v1/tasks/{taskId}/comments";

    public static final String ACCOUNT_REGISTER_MAPPING = "/register";
    public static final String DELETE_TASK_MAPPING = "/{taskId}";
    public static final String DELETE_COMMENT_MAPPING = "/{commentId}";

    public static final String STATUS_OK = "200";
    public static final String STATUS_CREATED = "201";
    public static final String STATUS_BAD_REQUEST = "400";
    public static final String STATUS_UNAUTHORIZED = "401";
    public static final String STATUS_INTERNAL_ERROR = "500";

    public static final String AUTH_TAG = "Authentication";
    public static final String ACCOUNTS_TAG = "User Accounts";
    public static final String TASKS_TAG = "Tasks";
    public static final String COMMENTS_TAG = "Comments";

    public static final String AUTH_SUMMARY = "Authenticate user by username and password";
    public static final String REGISTER_ACCOUNT_SUMMARY = "Register user by username and password";
    public static final String ADD_TASK_SUMMARY = "Add a new task";
    public static final String ADD_COMMENT_SUMMARY = "Add a comment to the task";
    public static final String DELETE_TASK_SUMMARY = "Delete task by task ID";
    public static final String EDIT_TASK_SUMMARY = "Edit task";
    public static final String GET_TASKS_SUMMARY = "Get all tasks of the current user";
    public static final String GET_COMMENTS_SUMMARY = "Get all comments of the task";
    public static final String EDIT_COMMENT_SUMMARY = "Edit comment";
    public static final String DELETE_COMMENT_SUMMARY = "Delete comment by comment ID";

    public static final String TASK_ID_PARAM = "taskId";
    public static final String TASK_ID_PARAM_DESC = "ID of the task";
    public static final String TASK_ID_PARAM_TYPE = "Long";
    public static final String TASK_ID_PARAM_VALUE = "1";

    public static final String COMMENT_ID_PARAM = "commentId";
    public static final String COMMENT_ID_PARAM_DESC = "ID of the comment";
    public static final String COMMENT_ID_PARAM_TYPE = "Long";
    public static final String COMMENT_ID_PARAM_VALUE = "1";

    public static final String AUTH_REQUEST_BODY = """
                                    {
                                        "username": "test@gmail.com",
                                        "password": "test1234"
                                    }
                                    """;
    public static final String REGISTER_ACCOUNT_REQUEST_BODY = """
                                    {
                                        "username": "test@gmail.com",
                                        "password": "test1234"
                                    }
                                    """;

    public static final String AUTH_RESPONSE_BODY_OK = """
                                            {
                                                "token": "eyJraWQiOiJmNGY3OGJiO..."
                                            }
                                            """;
    public static final String AUTH_RESPONSE_BODY_BAD_REQUEST = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "Bad credentials",
                                                "instance": "/api/v1/auth"
                                            }
                                            """;
    public static final String REGISTER_ACCOUNT_RESPONSE_BODY_BAD_REQUEST = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "Account with username = test1234@gmail.com already exists",
                                                 "instance": "/api/v1/accounts/register"
                                             }
                                            """;
    public static final String ADD_COMMENT_REQUEST_BODY = """
                                    {
                                        "taskId": 6,
                                        "message": "this is a message"
                                    }
                                    """;
    public static final String ADD_COMMENT_RESPONSE_BODY_OK = """
                                            {
                                                 "id": 5,
                                                 "message": "this is a message",
                                                 "taskId": 6,
                                                 "authorId": 6,
                                                 "createdTimestamp": "2024-12-13T18:04:13.963991100Z",
                                                 "modifiedTimestamp": "2024-12-13T18:04:13.963991100Z"
                                             }
                                            """;
    public static final String ADD_COMMENT_RESPONSE_BODY_BAD_REQUEST = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "User doesn't have permission to add a comment to this task",
                                                 "instance": "/api/v1/tasks/9/comments"
                                             }
                                            """;
    public static final String ADD_TASK_REQUEST_BODY = """
                                    {
                                        "title": "Write unit tests",
                                        "description": "Unit tests need to be written",
                                        "status": "WAITING",
                                        "priority": "HIGH",
                                        "executorId": 1
                                    }
                                    """;
    public static final String ADD_TASK_RESPONSE_BODY_OK = """
                                            {
                                                 "id": 5,
                                                 "message": "this is a message",
                                                 "taskId": 6,
                                                 "authorId": 6,
                                                 "createdTimestamp": "2024-12-13T18:04:13.963991100Z",
                                                 "modifiedTimestamp": "2024-12-13T18:04:13.963991100Z"
                                             }
                                            """;
    public static final String ADD_TASK_RESPONSE_BODY_BAD_REQUEST = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "title must not be blank",
                                                "instance": "/api/v1/tasks"
                                            }
                                            """;
    public static final String ADD_TASK_RESPONSE_BODY_UNAUTHORIZED = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "Current user does not have permission to add task",
                                                 "instance": "/api/v1/tasks"
                                             }
                                            """;
    public static final String DELETE_TASK_RESPONSE_BODY_BAD_REQUEST = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "Method parameter 'taskId': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \\"rr\\"",
                                                "instance": "/api/v1/tasks/rr"
                                            }
                                            """;
    public static final String DELETE_TASK_RESPONSE_BODY_UNAUTHORIZED = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "User with id = 5 doesn't have permission to delete a task",
                                                 "instance": "/api/v1/tasks"
                                             }
                                            """;
    public static final String DELETE_TASK_RESPONSE_BODY_INTERNAL_ERROR = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "Task with id = -1 doesn't exist",
                                                "instance": "/api/v1/tasks/-1"
                                            }
                                            """;
    public static final String GET_TASKS_RESPONSE_BODY_OK = """
                                            [
                                                 {
                                                     "id": 7,
                                                     "title": "Task 1",
                                                     "description": "Description 1",
                                                     "status": "WAITING",
                                                     "priority": "HIGH",
                                                     "authorId": 6,
                                                     "executorId": 1,
                                                     "createdTimestamp": "2024-12-13T15:38:54.809545Z",
                                                     "modifiedTimestamp": "2024-12-13T15:38:54.809545Z"
                                                 },
                                                 {
                                                     "id": 8,
                                                     "title": "Task 2",
                                                     "description": "Description 2",
                                                     "status": "COMPLETED",
                                                     "priority": "LOW",
                                                     "authorId": 6,
                                                     "executorId": 2,
                                                     "createdTimestamp": "2024-12-13T15:49:00.219090Z",
                                                     "modifiedTimestamp": "2024-12-13T15:49:00.219090Z"
                                                 }
                                             ]
                                            """;
    public static final String GET_COMMENTS_RESPONSE_BODY_OK = """
            [
                {
                    "id": 6,
                    "message": "comment from admin",
                    "taskId": 9,
                    "authorId": 6,
                    "createdTimestamp": "2024-12-13T18:16:43.752331Z",
                    "modifiedTimestamp": "2024-12-13T18:16:43.752331Z"
                },
                {
                    "id": 9,
                    "message": "comment from admin",
                    "taskId": 9,
                    "authorId": 6,
                    "createdTimestamp": "2024-12-16T00:57:58.867255Z",
                    "modifiedTimestamp": "2024-12-16T00:57:58.867255Z"
                }
            ]
            """;
    public static final String GET_COMMENTS_RESPONSE_BODY_BAD_REQUEST = """
            {
                "type": "localhost:8080/api/v1",
                "title": "Bad Request",
                "status": 400,
                "detail": "Can't find task with id = 22",
                "instance": "/api/v1/tasks/22/comments"
            }
            """;
    public static final String EDIT_COMMENT_REQUEST_BODY = """
            {
                "id": 9,
                "message": "edited message"
            }
            """;
    public static final String EDIT_COMMENT_RESPONSE_BODY_OK = """
            {
                "id": 9,
                "message": "edited message",
                "taskId": 9,
                "authorId": 6,
                "createdTimestamp": "2024-12-16T00:57:58.867255Z",
                "modifiedTimestamp": "2024-12-16T00:57:58.867255Z"
            }
            """;
    public static final String EDIT_COMMENT_RESPONSE_BODY_BAD_REQUEST = """
            {
                "type": "localhost:8080/api/v1",
                "title": "Bad Request",
                "status": 400,
                "detail": "Can't find comment with id = 15",
                "instance": "/api/v1/tasks/10/comments"
            }
            """;
    public static final String DELETE_COMMENT_RESPONSE_BODY_BAD_REQUEST = """
            {
                "type": "localhost:8080/api/v1",
                "title": "Bad Request",
                "status": 400,
                "detail": "Can't find comment with id = 6",
                "instance": "/api/v1/tasks/10/comments/6"
            }
            """;
    public static final String EDIT_TASK_REQUEST_BODY = """
            {
                "id": 14,
                "title": "edited_title",
                "description": "edited_description",
                "status": "WAITING",
                "priority": "LOW",
                "executorId": 1
            }
            """;
    public static final String EDIT_TASK_RESPONSE_BODY_OK = """
            {
                "id": 13,
                "title": "edited_title",
                "description": "edited_description",
                "status": "WAITING",
                "priority": "LOW",
                "executorId": 1
            }
            """;
    public static final String EDIT_TASK_RESPONSE_BODY_BAD_REQUEST = """
            {
                "type": "localhost:8080/api/v1",
                "title": "Bad Request",
                "status": 400,
                "detail": "Can't find task with id = 16",
                "instance": "/api/v1/tasks"
            }
            """;

    public static final String AUTH_DESC_OK = "Successfully authenticated user";
    public static final String AUTH_DESC_BAD_REQUEST = "Failed to authenticate user";
    public static final String REGISTER_ACCOUNT_DESC_CREATED= "Successfully registered new user account";
    public static final String REGISTER_ACCOUNT_DESC_BAD_REQUEST = "Failed to create user from given credentials";
    public static final String ADD_COMMENT_DESC_OK = "Successfully added new comment to the task";
    public static final String ADD_COMMENT_DESC_BAD_REQUEST = "Not valid request";
    public static final String ADD_TASK_DESC_OK = "Successfully added a new task";
    public static final String ADD_TASK_DESC_BAD_REQUEST = "Not valid request";
    public static final String ADD_TASK_DESC_UNAUTHORIZED = "Current user does not have permission to add a new task";
    public static final String DELETE_TASK_DESC_OK = "Successfully deleted task";
    public static final String DELETE_TASK_DESC_BAD_REQUEST = "Failed to delete task from given credentials";
    public static final String DELETE_TASK_DESC_UNAUTHORIZED = "Current user does not have permission to delete task";
    public static final String DELETE_TASK_DESC_INTERNAL_ERROR = "Failed to delete task with given id";
    public static final String GET_TASKS_DESC_OK = "Successfully returned all current user's tasks";
    public static final String GET_COMMENTS_DESC_OK = "Successfully returned all task's comments";
    public static final String GET_COMMENTS_DESC_BAD_REQUEST = "Invalid task ID given";
    public static final String EDIT_COMMENT_DESC_OK = "Successfully edited the comment";
    public static final String EDIT_COMMENT_DESC_BAD_REQUEST = "Invalid comment ID given";
    public static final String DELETE_COMMENT_DESC_OK = "Successfully deleted the comment";
    public static final String DELETE_COMMENT_DESC_BAD_REQUEST = "Invalid comment ID given";
    public static final String EDIT_TASK_DESC_OK = "Successfully edited the task";
    public static final String EDIT_TASK_DESC_BAD_REQUEST = "Invalid task request body given";

    public static final String ARRAY_TYPE = "array";
}

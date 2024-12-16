package com.effective.tms.common.constants;

public final class ApiConstants {

    public static final String BEARER_KEY = "bearer-key";

    public static final String BASE_URL = "localhost:8080/api/v1";

    public static final String AUTH_MAPPING = "/api/v1/auth";
    public static final String ACCOUNTS_MAPPING = "/api/v1/accounts";
    public static final String TASKS_MAPPING = "/api/v1/tasks";
    public static final String COMMENTS_MAPPING = "/api/v1/tasks/{taskId}/comments";

    public static final String ACCOUNT_REGISTER_MAPPING = "/register";
    public static final String DELETE_TASK_MAPPING = "/{taskId}";

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
    public static final String GET_TASKS_SUMMARY = "Get all tasks of the current user";

    public static final String TASK_ID_PARAM = "taskId";
    public static final String TASK_ID_PARAM_DESC = "ID of the task to delete";
    public static final String TASK_ID_PARAM_TYPE = "Long";
    public static final String TASK_ID_PARAM_VALUE = "1";

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
                                                 "detail": "Account with this username already exists",
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
                                                 "detail": "Current user does not have permission to delete task",
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

    public static final String AUTH_DESC_OK = "Successfully authenticated user";
    public static final String AUTH_DESC_BAD_REQUEST = "Bad credentials";
    public static final String REGISTER_ACCOUNT_DESC_CREATED= "Successfully registered new user account";
    public static final String REGISTER_ACCOUNT_DESC_BAD_REQUEST = "Not valid request";
    public static final String ADD_COMMENT_DESC_OK = "Successfully added new comment to the task";
    public static final String ADD_COMMENT_DESC_BAD_REQUEST = "Not valid request";
    public static final String ADD_TASK_DESC_OK = "Successfully added a new task";
    public static final String ADD_TASK_DESC_BAD_REQUEST = "Not valid request";
    public static final String ADD_TASK_DESC_UNAUTHORIZED = "Current user does not have permission to add a new task";
    public static final String DELETE_TASK_DESC_OK = "Successfully deleted task";
    public static final String DELETE_TASK_DESC_BAD_REQUEST = "Successfully deleted task";
    public static final String DELETE_TASK_DESC_UNAUTHORIZED = "User doesn't have authorities to delete task";
    public static final String DELETE_TASK_DESC_INTERNAL_ERROR = "Failed to delete task with given id";
    public static final String GET_TASKS_DESC_OK = "Returned all current user's tasks";

    public static final String ARRAY_TYPE = "array";
}

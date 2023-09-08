package ApiPages;

public class Reqres {

    private String baseURL = "https://reqres.in";
    private String listUsersEndpoint = "/api/users?page=2";
    private String singleUserEndpoint = "/api/users/2";
    private String createUserEndpoint = "/api/users";
    private String updateUserEndpoint = "/api/users/2";

    public String getBaseURL() {
        return baseURL;
    }

    public String getListUsersEndpoint() {
        return listUsersEndpoint;
    }

    public String getSingleUserEndpoint() {
        return singleUserEndpoint;
    }

    public String getCreateUserEndpoint() {
        return createUserEndpoint;
    }

    public String getUpdateUserEndpoint() {
        return updateUserEndpoint;
    }
}

package org.grademaster.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;
import org.grademaster.models.Student;
import org.grademaster.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.List;

public class SqlUtil {
    // GET
    public static User getUserByEmail(String userEmail) { // Check if user is in the database
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/user?email=" + userEmail, ApiUtil.RequestMethod.GET, null); // No need to send a data object

            if (conn.getResponseCode() != 200) {
                System.out.println("Error (getUserByEmail): " + conn.getResponseCode());
                return null;
            }

            // Read the connection as string
            String userDataJson = ApiUtil.readApiResponse(conn);
            // Then convert back to JSON from string
            JsonObject jsonObject = JsonParser.parseString(userDataJson).getAsJsonObject();

            // Extract the json data
            int id = jsonObject.get("id").getAsInt();
            String name = jsonObject.get("name").getAsString();
            String email = jsonObject.get("email").getAsString();
            String password = jsonObject.get("password").getAsString();
            String role = jsonObject.get("role").getAsString();
            // Getting the LocalDateTime (since it's not a primitive data type)
            // LocalDateTime createdAt = new Gson().fromJson(jsonObject.get("createdAt"), LocalDateTime.class);
            String createdAt = jsonObject.get("createdAt").getAsString();

            return new User(id, name, email, password, role, createdAt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }

    // TODO: Get Student by ID

    public static Student getStudentByEmail(String studentEmail) {
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/student?studentEmail=" + studentEmail, ApiUtil.RequestMethod.GET, null);

            if (conn.getResponseCode() != 200) {
                System.out.println("Error (getStudentByEmail): " + conn.getResponseCode());
                return null;
            }

            // Read the connection as string
            String studentDataJson = ApiUtil.readApiResponse(conn);
            System.out.println(studentDataJson);
            // Then convert back to JSON from string
            JsonObject jsonObject = JsonParser.parseString(studentDataJson).getAsJsonObject();

            // Extract the json data
            int id = jsonObject.get("id").getAsInt();
            String firstName = jsonObject.get("firstName").getAsString();
            String middleName = jsonObject.get("middleName").getAsString();
            String lastName  = jsonObject.get("lastName").getAsString();
            String email = jsonObject.get("studentEmail").getAsString();
            String dateOfBirth = jsonObject.get("dateOfBirth").getAsString();
            String address = jsonObject.get("address").getAsString();
            String contactNumber = jsonObject.get("contactNumber").getAsString();
            String gender = jsonObject.get("gender").getAsString();
            // Getting the LocalDateTime (since it's not a primitive data type)
            // LocalDateTime createdAt = new Gson().fromJson(jsonObject.get("createdAt"), LocalDateTime.class);
            String createdAt = jsonObject.get("createdAt").getAsString();

            return new Student(id, firstName, middleName, lastName, email, dateOfBirth, address, contactNumber, gender, createdAt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }

    public static String getUserRole(String email) {
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/user/role?email=" + email, ApiUtil.RequestMethod.GET, null);

            // If user is not found
            if (conn.getResponseCode() != 200) {
                return "404";
            }

            // Found the user, read the JSON response. Since this is in the form of a JSON response, we have to convert it using BufferedReader
            BufferedReader in =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            String json = response.toString();

            // Hardcoded but can be improved :)
            if (json.contains("ADMIN")) {
                return "ADMIN";
            } else {
                return "TEACHER";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return "404";
    }

    public static int getSubjectCountForCurrentUser(int userId) {
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/subject-relation/count?userId=" + userId, ApiUtil.RequestMethod.GET, null);

            if (conn.getResponseCode() != 200) {
                return -1;
            }

            String response = ApiUtil.readApiResponse(conn);

            return Integer.parseInt(response.trim());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return -1;
    }

    public static int getStudentCountUnderUser(int userId) {
        HttpURLConnection conn =  null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/student-enrollment/count?userId=" + userId, ApiUtil.RequestMethod.GET, null);

            if (conn.getResponseCode() != 200) {
                return -1;
            }

            String response = ApiUtil.readApiResponse(conn);

            return Integer.parseInt(response.trim());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return -1;
    }

    public static JsonArray getSubjectsForUser(int userId) {
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/subject-relation?userId=" + userId, ApiUtil.RequestMethod.GET, null);

            if (conn.getResponseCode() != 200) {
                return null;
            }

            // Read the response and return the list of subjects as an array in JSON format
            String response = ApiUtil.readApiResponse(conn);
            return JsonParser.parseString(response).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }

    // POST
    public static boolean postLoginUser(String email, String password) { // Login the user
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/user/login?email=" + email + "&password=" + password, ApiUtil.RequestMethod.POST, null);

            if (conn.getResponseCode() != 200) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return true;
    }

    // Account creation utility method
    public static boolean postCreateUser(JsonObject userData) {
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/user/create", ApiUtil.RequestMethod.POST, userData);

            if (conn.getResponseCode() != 200) {
                return false; // Failed to create an account
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect(); // Disconnect the http connection after everything
            }
        }

        return true; // Account successfully created and stored into the database
    }

    // Student creation utility method
    public static boolean postCreateStudent(JsonObject studentData) {
        HttpURLConnection conn = null;

        try {
            conn = ApiUtil.fetchApi("/api/v1/student/create", ApiUtil.RequestMethod.POST, studentData);

            if (conn.getResponseCode() != 200) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return true;
    }
}

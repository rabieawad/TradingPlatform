package org.boes.praktikum.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    boolean loggedIn = false;
    String username;
    String password;


    // Handle user login locally, until server functionality for handling loginRequests is implemented
    public void handleLoginLocally() {

        Scanner userInput = new Scanner(System.in);


        while (!loggedIn) {
            System.out.println("Username:");
            String username = userInput.nextLine();
            System.out.println("Password:");
            String password = userInput.nextLine();
            User user = User.getUserById(username);
            if (user == null) {
                System.out.println("Invalid username!");
                System.out.println();
            } else {
                if (user.getPassword().equals(password)) {
                    loggedIn = true;
                    this.username = username;
                    this.password = password;
                } else {
                    System.out.println("Invalid password!");
                    System.out.println();
                }
            }
        }
    }

    @SneakyThrows
    // Handles login via POST-Requests to server, sending username and password inputs by user
    // Server responds with TRUE or FALSE, depending on whether username and password are correct.
    public void handleLoginGlobally(HttpClient client){
        var objectMapper = new ObjectMapper();

        Scanner userInput = new Scanner(System.in);

        while (!loggedIn) {
            System.out.println("Username:");
            String usernameInput = userInput.nextLine();
            System.out.println("Password:");
            String passwordInput = userInput.nextLine();

            UserRequest user = new UserRequest(usernameInput, passwordInput);
            var userRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:7070/Getkurs"))
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(user)))
                    .build();

            HttpResponse<String> responseUserRequest = client.send(userRequest, HttpResponse.BodyHandlers.ofString());
            var bodyUserRequest = responseUserRequest.body();
            var loginStatus = objectMapper.readValue(bodyUserRequest, Boolean.class);

            if(loginStatus){
                this.loggedIn = true;
                this.username = usernameInput;
                this.password = passwordInput;
                System.out.println("Logged in successfully!");
                System.out.println();
            } else {
                System.out.println("Wrong username or password! Please try again.");
                System.out.println();
            }
        }
    }
}

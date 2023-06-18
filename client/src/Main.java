package org.boes.praktikum.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        final var client = HttpClient.newBuilder()
                .build();


        Scanner userInput = new Scanner(System.in);
        var objectMapper = new ObjectMapper();

        // User has to be logged in to his account to purchase items etc.
        Login login = new Login();
        login.handleLoginLocally();

        // new Menu
        Menu menu = new Menu();

        while(true) {
            // Main-menu for user interaction
            menu.menuIdle(userInput,client,objectMapper, login);
        }
    }

    }


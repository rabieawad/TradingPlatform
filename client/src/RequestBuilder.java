package org.boes.praktikum.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Data
@AllArgsConstructor
public class RequestBuilder {

    // sends GET-request to server, hoping to receive List of all goods
    public List<Good> goodsListRequestHandler(HttpClient client, ObjectMapper objectMapper) throws URISyntaxException, IOException, InterruptedException {

        var request2 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/goods"))
                .GET()
                .build();

        HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
        var body2 = response2.body();
        return objectMapper.readValue(body2, new TypeReference<List<Good>>(){});
    }

    // Sends GET-Request to the server, hoping to receive a Good-Object
    public Good goodInformationHandler(HttpClient client, ObjectMapper objectMapper, String goodOfInterest) throws IOException, InterruptedException, URISyntaxException {
        var request =HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/goods/" + goodOfInterest))
                .GET()
                .build();
        HttpResponse<String> responseGood = client.send(request, HttpResponse.BodyHandlers.ofString());
        var bodyGood = responseGood.body();
        return objectMapper.readValue(bodyGood, Good.class);
    }

    //Sends POST-Request hoping to receive the current price for the asked good
    public Double kursInformationHandler(HttpClient client, ObjectMapper objectMapper,String goodOfInterest) throws IOException, InterruptedException, URISyntaxException {
        KursRequest kursReq = new KursRequest(goodOfInterest);

        var kursRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/Getkurs"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(kursReq)))
                .build();

        HttpResponse<String> responseKursReq = client.send(kursRequest, HttpResponse.BodyHandlers.ofString());
        var bodyFundRequest = responseKursReq.body();
        //var kurs = objectMapper.readValue(bodyFundRequest, Kurs.class);
        var kurs = objectMapper.readValue(bodyFundRequest, Double.class);
        return kurs;
    }

    //Sends POST-Request, adding funds to the users account
    public User addFundHandler(HttpClient client, ObjectMapper objectMapper,int fundValue, Login login) throws URISyntaxException, IOException, InterruptedException {

        AddFundRequest addFund = new AddFundRequest(login.getUsername(), fundValue);

        var addfundRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/addfund"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(addFund)))
                .build();

        HttpResponse<String> responseAddFund = client.send(addfundRequest, HttpResponse.BodyHandlers.ofString());
        var bodyFundRequest = responseAddFund.body();
        var fundAdded = objectMapper.readValue(bodyFundRequest, User.class);
        //var fundAdded = objectMapper.readValue(bodyFundRequest, Float.class);
        return fundAdded;
    }

    // Sends POST-Request, asking the server to buy a specific amount of a certain good
    public int buyItemHandler(HttpClient client, ObjectMapper objectMapper,String goodOfInterest,int amount, Login login) throws IOException, InterruptedException, URISyntaxException {
        BuyRequest buy = new BuyRequest(login.getUsername(), login.getPassword(), goodOfInterest,amount);
        var buyReq = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/buy"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(buy)))
                .build();

        HttpResponse<String> responseBuy = client.send(buyReq, HttpResponse.BodyHandlers.ofString());
        var bodyFundRequest = responseBuy.body();
        //var receipt = objectMapper.readValue(bodyFundRequest, User.class);
        //var receipt = objectMapper.readValue(bodyFundRequest, new TypeReference<List<Good>>(){});
        var receipt = objectMapper.readValue(bodyFundRequest, Integer.class);
        return receipt;
    }

    // Sends POST-Request, asking the server to sell a specific amount of a certain good
    public int sellItemHandler(HttpClient client, ObjectMapper objectMapper,String goodOfInterest,int amount, double price, Login login) throws URISyntaxException, IOException, InterruptedException {
        SellRequest sell = new SellRequest(login.getUsername(), login.getPassword(),amount,goodOfInterest, price);
        var sellReq = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/sell"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(sell)))
                .build();

        HttpResponse<String> responseSell = client.send(sellReq, HttpResponse.BodyHandlers.ofString());
        var bodySellRequest = responseSell.body();
        //var receipt = objectMapper.readValue(bodyFundRequest, User.class);
        //var receipt = objectMapper.readValue(bodyFundRequest, new TypeReference<List<Good>>(){});
        var receipt = objectMapper.readValue(bodySellRequest, Integer.class);
        return receipt;
    }

    public InventoryContent showInventory(HttpClient client, ObjectMapper objectMapper, Login login) throws URISyntaxException, IOException, InterruptedException {
        GetInventoryContentRequest getInvReq = new GetInventoryContentRequest(login.getUsername(),login.getPassword());
        var showInv = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/inventory"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(getInvReq)))
                .build();

        HttpResponse<String> responseInventory = client.send(showInv, HttpResponse.BodyHandlers.ofString());
        var bodySellRequest = responseInventory.body();
        //var receipt = objectMapper.readValue(bodyFundRequest, User.class);
        //var receipt = objectMapper.readValue(bodyFundRequest, new TypeReference<List<Good>>(){});
        var inventory = objectMapper.readValue(bodySellRequest, InventoryContent.class);
        return inventory;
    }

    public void addUser(HttpClient client, ObjectMapper objectMapper, String username, String password, float guthaben) throws IOException, URISyntaxException, InterruptedException {
        AddUserRequest newUserRequest = new AddUserRequest(username,password,guthaben);
        var addNewUser = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/addUser"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(newUserRequest)))
                .build();
        HttpResponse<String> responseAddUser = client.send(addNewUser, HttpResponse.BodyHandlers.ofString());
    }

    public void removeUser(HttpClient client, ObjectMapper objectMapper, String username,String password) throws IOException, URISyntaxException, InterruptedException {
        DeleteUserRequest newUserRequest = new DeleteUserRequest(username,password);
        var addNewUser = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/addUser"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(newUserRequest)))
                .build();
        HttpResponse<String> responseAddUser = client.send(addNewUser, HttpResponse.BodyHandlers.ofString());
    }

}

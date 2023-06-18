package org.boes.praktikum;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

import java.time.LocalDateTime;

import java.util.List;
import java.util.function.Function;


public class Main {
    public static void main(String[] f) {

        Javalin app = Javalin.create().start(7070);
        /**
         * Create an Endpoint to receive Buy Requests from the registered users
         * the Code needs to be modified because It does not transform Json into BuyRequest
         */
        app.post("/buy", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();

            // transform jason to Buyrequest
            BuyRequest p = objectMapper.readValue(ctx.body(), BuyRequest.class);

            // find the Buyer
            User buyer = User.getUserById(p.userName);
            addItemsToUser(p);
            ctx.json(buyer.getInventory().size());

        });
        app.post("/sell", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();

            // transform jason to Buyrequest
            SellRequest p = objectMapper.readValue(ctx.body(), SellRequest.class);

            // find the Buyer
            User buyer = User.getUserById(p.userName);
            if (!p.password.equals(buyer.getPassword()))
                ctx.json("unvalidpassword");
            else {
            List<Good> l = buyer.getInventory();
            removeGoods(p,l);
            adjustGuthaben(buyer,p);

            ctx.json(buyer.getInventory().size());}

        });


        /**
         * Create an Endpoint to receive Get Inventory content requests Requests from the registered users
         *
         */

        app.post("/inventory", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();

            // transform jason to Buyrequest
            GetInventoryContentRequest p = objectMapper.readValue(ctx.body(), GetInventoryContentRequest.class);

            // find the Buyer
            User buyer = User.getUserById(p.userName);

               InventoryContent c =  getinventoryContent(buyer);
               ctx.json(c);


        });




        /**
         * Create an Endpoint to receive add fund Requests from the registered users
         * the Code needs to be modified because It does not transform Json into AddFundRequest
         */
        app.post("/addfund", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();
            // transform json to AddFundRequest
            AddFundRequest p = objectMapper.readValue(ctx.body(), AddFundRequest.class);
            // find the Buyer
            User buyer = User.getUserById(p.getUserId());
            //add fund to buyer's account
            addFundsToUser(buyer, p.getValue());
             ctx.json(buyer);
        });


        /**
         * Create an Endpoint to receive Get Kurs requests from the registered users
         * the Code needs to be modified because It does not transform Json into AddFundRequest
         */

        app.post("/Getkurs", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();
            // transform jason to Buyrequest
             GetKursRequest p = objectMapper.readValue(ctx.body(), GetKursRequest.class);
             //send back response to the client
            ctx.json(getKursByRequest(p));
        });
        /**
         *registers a new user in the server
         *
         */
        app.post("/addUser", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();

            // transform jason to AddUserRequest
            AddUserRequest p = objectMapper.readValue(ctx.body(), AddUserRequest.class);
            User user = new User(p.getUsername(),p.getPassword(),p.getGuthaben());
           User.userList.add(user);

            // find the Buyer


        });
        /**
         * deletes a user in the server
         */
        app.post("/deleteUser", ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();

            // transform jason to AddUserRequest
            DeleteUserRequest p = objectMapper.readValue(ctx.body(), DeleteUserRequest.class);
            User user = User.getUserById(p.getUserName());
           User.userList.remove(user);

            // find the Buyer


        });

        app.get("/goods", ctx -> ctx.json(GoodsService.getGoods()));
        app.get("/goods/{id}", ctx -> ctx.json(GoodsService.getGood(ctx.pathParam("id"))));

    }

    private static void adjustGuthaben(User buyer, SellRequest p) {
        buyer.setGuthaben(buyer.getGuthaben() - p.price* p.amount);
    }

    /**
     * removes Goods from an inventory based on the Sell Request
     * @param p
     * @param l
     */
    private static void removeGoods(SellRequest p, List<Good> l) {
        for(int i = 0; i < p.amount; i++){
           int j = 0;
            while (true){

                    if(l.get(j).getId().equals(p.goodId) ){
                        l.remove(j);
                        break;

                }
                    j++;

            }
        }

    }

    /**
     * returns the content of an inventory
     * @param buyer
     * @return
     */
    private static InventoryContent getinventoryContent(User buyer) {
        InventoryContent c = new InventoryContent();
        List<Good>  l= buyer.getInventory();
        for(int i = 0; i < l.size(); i++){
           String id =  l.get(i).getId();
            if(id == "dimond")
                c.dimond = c.dimond +1;
            if(id == "steel")
                c.steel = c.steel + 1;
            if(id == "gold")
                c.gold = c.gold + 1;
            if(id == "silver")
                c.silver = c.silver + 1;
            if(id == "wood")
                c.wood = c.wood +1;
            if(id == "kidny")
                c.kidny = c.kidny + 1;

        }


        return c;
    }

    /**
     * adds funds to a User account
     * @param user
     * @param fundsToAdd
     */
    private static void addFundsToUser(User user, float fundsToAdd){
        user.setGuthaben(user.getGuthaben() + fundsToAdd);


    }

    /**
     * returns the course of a good based on information provided by th GetKursRequest object
     * @param p
     * @return
     */

    private static double getKursByRequest(GetKursRequest p){
        Good good = GoodsService.getGood(p.getGoodId());
        double kurs = good.getKurs().getCourseByTime(p.getMin(), p.getHour());
        return kurs;
    }
    /**
     * processes buy request and does all necessary steps to get the request done
     */
    private static void addItemsToUser(BuyRequest p ) throws Exception {
        // find the Buyer
        User buyer = User.getUserById(p.userName);

        //find the desired good
        Good desiredGood = GoodsService.getGood(p.goodId);
        ;;
        //get current course
        double kurs = desiredGood.getKurs().getCourseByTime(LocalDateTime.now().getMinute(),LocalDateTime.now().getHour()
        );

        // calculate how much does his Request cost
        double cost = p.amount * kurs;//p.currentcourse


        //check if there is enough money on the account
        if (cost > buyer.getGuthaben())
            //throw an exception back to the client informing him/her of the account balance
            throw new Exception("not enough credit");

        //withdrawl money from the buyer
        buyer.setGuthaben(buyer.getGuthaben() - (float) cost);

        // move the items to the buyer's inventory
        for (int i = 0; i < p.amount; i++) {

            buyer.getInventory().add(new Good(desiredGood.getId(), desiredGood.getName()));
        }

    }

}

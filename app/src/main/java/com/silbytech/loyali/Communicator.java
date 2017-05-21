package com.silbytech.loyali;
import com.silbytech.loyali.communication.Interface;
import com.silbytech.loyali.entities.RewardsSerializable;
import com.silbytech.loyali.entities.SubscriptionSerializable;
import com.silbytech.loyali.entities.VendorCardSerializer;
import com.silbytech.loyali.responses.MessageResponse;
import com.silbytech.loyali.responses.RegisterResponse;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;

/************************************
 * Created by Yosef Silberhaft
 ************************************/
public class Communicator {
    private static final String TAG = "Communicator";
    private static final String SERVER_URL = "http://192.168.137.1:8000/loyali";
    private static final String LOYALI_API = "http://192.168.137.1:8000/loyaliapi";

    /****************************************************************************************
     *                                                                      -----Tested------
     * This function will POST the new Mobile users information to the API
     * @param firstName - first name
     * @param lastName - last name
     * @param email - email
     * @param username - username
     * @param password - password
     * @param facebook - facebook id
     * @param pushKey - push_api_key
     * @param callback - Server callback response
     *****************************************************************************************/
    void postNewMobileUserAPI(String firstName, String lastName, String email,
                              String username, String password, String facebook,
                              String pushKey, Callback<RegisterResponse> callback){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface comInterface = restAdapter.create(Interface.class);
        comInterface.postNewUserAPI(firstName, lastName, username, email, password, facebook, pushKey, callback);
    }


    void userLogin(String username, String password, Callback<RegisterResponse> callback){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface comInterface = restAdapter.create(Interface.class);
        comInterface.loginUser(username, password, callback);
    }


    /************************************************************************
     * This method will post the new FB users details to the server
     * @param name - users name
     * @param userID - users ID
     * @param callback - callback response
     ************************************************************************/
     void newFBUserPost(String name,
                              String userID,
                              Callback<RegisterResponse> callback){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface comInterface = restAdapter.create(Interface.class);
        comInterface.postNewFBUser(name, userID, callback);
    }

    void getVendorsWithCards(Callback<List<VendorCardSerializer>> serverResponse){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface inter = restAdapter.create(Interface.class);
        inter.getVendorsWithCards(serverResponse);
    }


    void postCreateSubscription(String vendor_id, String customer_id,
                                       Callback<MessageResponse> callback) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface comInterface = restAdapter.create(Interface.class);
        comInterface.postCreateSubscription(vendor_id, customer_id, callback);
    }


    void deleteSubscriptionPost(String customerID, String vendorID,
                                Callback<MessageResponse> callback){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface comInterface = restAdapter.create(Interface.class);
        comInterface.deleteSubscriptionPost(customerID, vendorID, callback);
    }


    void getSubscriptions(String customer_id, Callback<List<SubscriptionSerializable>> serverResponse){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface inter = restAdapter.create(Interface.class);
        inter.getSubscriptions(customer_id, serverResponse);
    }


    void getSubscriptionCardsByVendorID(String customer_id, String vendor_id,
                                        Callback<List<SubscriptionSerializable>> serverResponse){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface inter = restAdapter.create(Interface.class);
        inter.getSubscriptionCardsByVendorID(customer_id, vendor_id, serverResponse);
    }


    void punchCard(String customerID, String barcode, String cardID,
                   Callback<MessageResponse> serverResponse){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface inter = restAdapter.create(Interface.class);
        inter.punchCardPOST(customerID, barcode, cardID, serverResponse);
    }


    void getRewards(String customerID, Callback<RewardsSerializable> serverResponse){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(LOYALI_API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Interface inter = restAdapter.create(Interface.class);
        inter.getRewards(customerID, serverResponse);
    }
}
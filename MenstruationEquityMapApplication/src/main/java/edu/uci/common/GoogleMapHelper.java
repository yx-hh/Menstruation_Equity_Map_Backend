package edu.uci.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.uci.objects.BuildingAddress;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Huang Yuxin
 * @date 2022/11/2
 */
@Component
public class GoogleMapHelper {

    @Value("${google.api-key}")
    private String apiKey;

    @Value("${google.distance-matrix}")
    private String distanceMatrixUrl;

    @Value("${google.geoUrl}")
    private String geoUrl;

//    public List<BuildingAddress> getNearestBuildings(BuildingAddress origin, List<BuildingAddress> addresses) throws IOException {
//        String originEncode = URLEncoder.encode(origin.getLatitude() + "," + origin.getLongitude(), "UTF-8");
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < addresses.size(); i++) {
//            BuildingAddress address = addresses.get(i);
//            sb.append(URLEncoder.encode(address.getLatitude() + "," + address.getLongitude(), "UTF-8"));
//            if (i != addresses.size() - 1) {
//                sb.append(URLEncoder.encode("|", "UTF-8"));
//            }
//        }
//        String destinationEncode = sb.toString();
//
//        String url = String.format(distanceMatrixUrl,
//                originEncode,
//                destinationEncode,
//                apiKey);
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//        Response response = client.newCall(request).execute();
//
//        response.headers();
//        String responseBody = response.body().string();
//        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
//        JsonObject locationJson = jsonObject.get("rows").getAsJsonObject().get("location").getAsJsonObject();
//        return new BuildingAddress(locationJson.get("lat").getAsDouble(), locationJson.get("lng").getAsDouble());
//    }

    public BuildingAddress getLagAndLat(String address) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        address = address + "  Irvine";
        String encoderAddress = URLEncoder.encode(address, "UTF-8");
        String url = String.format(geoUrl, encoderAddress, apiKey);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        response.headers();
        String responseBody = response.body().string();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        if (jsonObject.get("status").getAsString().equalsIgnoreCase("OK")) {
            JsonObject locationJson = jsonObject.get("results").getAsJsonArray().
                    get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
            return new BuildingAddress(locationJson.get("lat").getAsDouble(), locationJson.get("lng").getAsDouble());
        } else {
            return null;
        }
    }
}

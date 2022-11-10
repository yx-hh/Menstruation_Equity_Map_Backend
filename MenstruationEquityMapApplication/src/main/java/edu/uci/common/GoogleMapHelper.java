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

    public List<BuildingAddress> getNearestBuildings(BuildingAddress origin, List<BuildingAddress> addresses) throws IOException {
        // todo mock data, need delete later
        origin = new BuildingAddress(40.6655101, -73.89188969999998);
        addresses = new LinkedList<BuildingAddress>();
        addresses.add(new BuildingAddress(40.659569, -73.933783));
        addresses.add(new BuildingAddress(40.729029, -73.851524));
        addresses.add(new BuildingAddress(40.6860072, -73.6334271));
        addresses.add(new BuildingAddress(40.598566, -73.7527626));

        String originEncode = URLEncoder.encode(origin.getLatitude() + "," + origin.getLongitude(), "UTF-8");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addresses.size(); i++) {
            BuildingAddress address = addresses.get(i);
            sb.append(URLEncoder.encode(address.getLatitude() + "," + address.getLongitude(), "UTF-8"));
            if (i != addresses.size() - 1) {
                sb.append(URLEncoder.encode("|", "UTF-8"));
            }
        }
        String destinationEncode = sb.toString();

        String url = String.format(distanceMatrixUrl,
                originEncode,
                destinationEncode,
                apiKey);

        String url2 = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=40.6655101%2C-73.89188969999998&destinations=40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626&key=AIzaSyDL0gcQf5NFWRFZdXgUtatPoOxE5JaxdJY";

        Assert.isTrue(url.equals(url2), "url encode correct");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        response.headers();
        String responseBody = response.body().string();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonObject locationJson = jsonObject.get("rows").getAsJsonObject().get("location").getAsJsonObject();
        BuildingAddress address = new BuildingAddress(locationJson.get("lat").getAsDouble(), locationJson.get("lng").getAsDouble());
        return null;
    }

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

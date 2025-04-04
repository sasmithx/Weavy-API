package com.sasmithx.pro.service.impl;

import com.sasmithx.pro.service.UserService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final OkHttpClient httpClient;
    private final String baseUrl = "https://8015b5dbc0724d38882ac90397c27649.weavy.io/api/users";
    private final String apiToken = "wys_hMWpXdekxcn9Gc8Ioah3azOllzUZ7l3HN9yB";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    public String createUser(String jsonPayload) throws IOException {
        RequestBody body = RequestBody.create(jsonPayload, JSON);
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }


    @Override
    public String listUsers(int get) throws IOException {
        HttpUrl url = HttpUrl.parse(baseUrl).newBuilder()
                .addQueryParameter("get", String.valueOf(get))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public String getUserDetails(String userId) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/" + userId)
                .get()
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public String updateUser(String userId, String jsonPayload) throws IOException {
        RequestBody body = RequestBody.create(jsonPayload, JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/" + userId)
                .put(body)
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public String deleteUser(String userId) throws IOException {
        return "";
    }
}

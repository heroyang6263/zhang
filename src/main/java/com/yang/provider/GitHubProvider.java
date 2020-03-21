package com.yang.provider;

import com.alibaba.fastjson.JSON;
import com.yang.dto.AccessTokenDto;
import com.yang.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String[] split = string.split("&");
                String tokenstr = split[0];
                String token = tokenstr.split("=")[1];
                return token;
            }catch (IOException w){
                w.printStackTrace();
            }
            return null;
    }
    public GitHubUser getUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        GitHubUser gitHubUser = JSON.parseObject(string,GitHubUser.class);
        return gitHubUser;
    }
}

package com.yang.controller;

import com.yang.dto.AccessTokenDto;

import com.yang.dto.GitHubUser;
import com.yang.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @author Administrator
 */
@Controller
public class AuthorController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.uri}")
    private String uri;
    @Value("${github.client.secret}")
    private String clientSecret;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                            @RequestParam(name = "state") String state) throws IOException {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(uri);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(clientId);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }

}

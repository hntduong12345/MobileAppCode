package com.example.petrial.API.Repos;

import com.example.petrial.API.APIClient;
import com.example.petrial.API.Services.SachService;

public class SachRepo {
    public static SachService getSachService(){
        return APIClient.getClient().create(SachService.class);
    }
}

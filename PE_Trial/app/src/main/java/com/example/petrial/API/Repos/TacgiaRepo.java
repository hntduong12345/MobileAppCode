package com.example.petrial.API.Repos;

import com.example.petrial.API.APIClient;
import com.example.petrial.API.Services.TacgiaService;

public class TacgiaRepo {
    public static TacgiaService getTacgiaService(){
        return APIClient.getClient().create(TacgiaService.class);
    }
}

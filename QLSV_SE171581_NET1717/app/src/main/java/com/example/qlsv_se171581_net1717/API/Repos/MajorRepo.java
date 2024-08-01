package com.example.qlsv_se171581_net1717.API.Repos;

import com.example.qlsv_se171581_net1717.API.APIClient;
import com.example.qlsv_se171581_net1717.API.Services.MajorService;

public class MajorRepo {
    public static MajorService getMajorService(){
        return APIClient.getClient().create(MajorService.class);
    }
}

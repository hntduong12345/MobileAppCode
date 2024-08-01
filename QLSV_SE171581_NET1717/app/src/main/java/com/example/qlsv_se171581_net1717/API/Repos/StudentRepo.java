package com.example.qlsv_se171581_net1717.API.Repos;

import com.example.qlsv_se171581_net1717.API.APIClient;
import com.example.qlsv_se171581_net1717.API.Services.MajorService;
import com.example.qlsv_se171581_net1717.API.Services.StudentService;

public class StudentRepo {
    public static StudentService getStudentService(){
        return APIClient.getClient().create(StudentService.class);
    }
}

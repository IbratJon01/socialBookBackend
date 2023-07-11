package com.example.demo.Controller;

import com.example.demo.Entety.Status;
import com.example.demo.Repository.StatusRepo;
import com.example.demo.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusRepo statusRepo;
    @Autowired
    public StatusController(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Autowired
    StatusService statusService;

    @PostMapping("")
    private Status submitStatus(@RequestBody Status status){

        return statusService.submitDataIntoDB(status);
    }

    @GetMapping("")
    private ArrayList<Status> getAllStatus(){
        return statusService.retrieveStatus();

    }


    @GetMapping("/user/{userId}")
    public List<Status> getStatusByUserId(@PathVariable String userId) {
        return statusRepo.findByUserId(userId);
    }

}

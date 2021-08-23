package com.gunishjha.cabbooking.controllers;

import com.gunishjha.cabbooking.database.RidersManager;
import com.gunishjha.cabbooking.database.TripsManager;
import com.gunishjha.cabbooking.model.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RidersController {
    private RidersManager ridersManager;
    private TripsManager tripsManager;

    @Autowired
    public RidersController(RidersManager ridersManager,TripsManager tripsManager){
        this.ridersManager=ridersManager;
        this.tripsManager=tripsManager;
    }

    @RequestMapping(value = "/register/rider",method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId,
                                        final String riderName){
        ridersManager.createRider(new Rider(riderId,riderName));
        return ResponseEntity.ok("");
    }



}

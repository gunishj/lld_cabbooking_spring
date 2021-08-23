package com.gunishjha.cabbooking.database;


import com.gunishjha.cabbooking.model.Rider;
import lombok.NonNull;
import lombok.experimental.NonFinal;

import java.util.HashMap;
import java.util.Map;

public class RidersManager {
    Map<String, Rider> riders = new HashMap<>();
    public void createRider(@NonNull final Rider newRider){
        if (riders.containsKey(newRider.getId())){
            throw new RuntimeException();
        }
        riders.put(newRider.getId(), newRider);
    }

    public Rider getRider(@NonNull final String riderId){
        if (!riders.containsKey(riderId)){
            throw new RuntimeException();
        }
        return riders.get(riderId);
    }
}

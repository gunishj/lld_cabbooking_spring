package com.gunishjha.cabbooking.strategies;

import com.gunishjha.cabbooking.model.Cab;
import com.gunishjha.cabbooking.model.Location;
import com.gunishjha.cabbooking.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component()
public class DefaultCabMatchingStrategy implements CabMatchingStrategy{

    @Override
    public Cab matchCabToRider(@NonNull final Rider rider,
                               @NonNull final List<Cab> candidateCabs,
                               @NonNull final Location fromPoint,
                               @NonNull final Location toPoint) {
        if (candidateCabs.isEmpty()){
            return null;
        }
        return candidateCabs.get(0);
    }
}

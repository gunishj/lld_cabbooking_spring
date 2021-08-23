package com.gunishjha.cabbooking.strategies;

import com.gunishjha.cabbooking.model.Cab;
import com.gunishjha.cabbooking.model.Location;
import com.gunishjha.cabbooking.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {
    Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint,Location toPoint);
}

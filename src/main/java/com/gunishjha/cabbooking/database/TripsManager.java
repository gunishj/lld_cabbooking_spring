package com.gunishjha.cabbooking.database;

import com.gunishjha.cabbooking.model.Cab;
import com.gunishjha.cabbooking.model.Location;
import com.gunishjha.cabbooking.model.Rider;
import com.gunishjha.cabbooking.model.Trip;
import com.gunishjha.cabbooking.strategies.CabMatchingStrategy;
import com.gunishjha.cabbooking.strategies.PricingStrategy;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripsManager {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE=10.0;
    private Map<String , List<Trip>> trips = new HashMap<>();

    private CabsManager cabsManager;
    private RidersManager ridersManager;
    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;

    public TripsManager(CabsManager cabsManager,
                        RidersManager ridersManager,
                        CabMatchingStrategy cabMatchingStrategy,
                        PricingStrategy pricingStrategy){
        this.cabsManager = cabsManager;
        this.ridersManager=ridersManager;
        this.cabMatchingStrategy=cabMatchingStrategy;
        this.pricingStrategy=pricingStrategy;
    }

    public void createTrip(
            @NonNull final Rider rider,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint){
        final List<Cab> closeByCabs = cabsManager.getCabs(fromPoint,MAX_ALLOWED_TRIP_MATCHING_DISTANCE);

        final List<Cab> closeByAvailableCabs = closeByCabs.stream()
                .filter(cab -> cab.getCurrentTrip()==null)
                .collect(Collectors.toList());

        final Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider,closeByAvailableCabs,fromPoint,toPoint);
        if (selectedCab==null){
            throw new RuntimeException();
        }

        final Double price = pricingStrategy.findPrice(fromPoint,toPoint);
        final Trip newTrip = new Trip(rider,selectedCab,price,fromPoint, toPoint);
        if (!trips.containsKey(rider.getId())){
            trips.put(rider.getId(),new ArrayList<>());
        }
        trips.get(rider.getId()).add(newTrip);
        selectedCab.setCurrentTrip(newTrip);
    }

    public List<Trip> tripHistory(@NonNull final Rider rider){
        return trips.get(rider.getId());
    }

    public void endTrip(@NonNull final Cab cab){
        if(cab.getCurrentTrip() == null){
            throw new RuntimeException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }

}

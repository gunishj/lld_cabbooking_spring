package com.gunishjha.cabbooking.strategies;

import com.gunishjha.cabbooking.model.Location;

public interface PricingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}

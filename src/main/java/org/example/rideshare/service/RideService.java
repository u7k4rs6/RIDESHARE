package org.example.rideshare.service;

import org.example.rideshare.dto.RideDTOs;
import org.example.rideshare.model.Ride;
import org.example.rideshare.model.User;
import org.example.rideshare.repository.RideRepository;
import org.example.rideshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    private User getAuthenticatedUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Ride createRide(RideDTOs.CreateRideRequest request) {
        User user = getAuthenticatedUser();
        Ride ride = new Ride();
        ride.setUserId(user.getId());
        ride.setPickupLocation(request.getPickupLocation());
        ride.setDropLocation(request.getDropLocation());
        ride.setStatus("REQUESTED");
        return rideRepository.save(ride);
    }

    public List<Ride> getMyRides() {
        User user = getAuthenticatedUser();
        return rideRepository.findByUserId(user.getId());
    }

    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus("REQUESTED");
    }

    public Ride acceptRide(String rideId) {
        User driver = getAuthenticatedUser();
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        if (!"REQUESTED".equals(ride.getStatus())) {
            throw new RuntimeException("Ride is not in REQUESTED status");
        }
        ride.setDriverId(driver.getId());
        ride.setStatus("ACCEPTED");
        return rideRepository.save(ride);
    }

    public Ride completeRide(String rideId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        if (!"ACCEPTED".equals(ride.getStatus())) {
            throw new RuntimeException("Ride is not in ACCEPTED status");
        }
        ride.setStatus("COMPLETED");
        return rideRepository.save(ride);
    }
}

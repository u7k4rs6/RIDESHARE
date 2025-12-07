package org.example.rideshare.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RideDTOs {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRideRequest {
        @NotBlank(message = "Pickup location is required")
        private String pickupLocation;

        @NotBlank(message = "Drop location is required")
        private String dropLocation;
    }
}

package com.acme.autoprotracker.workshop.application.internal.commandservices;

import com.acme.autoprotracker.workshop.domain.model.aggregates.Advertising;

import com.acme.autoprotracker.workshop.domain.model.commands.*;
import com.acme.autoprotracker.workshop.domain.services.AdvertisingCommandService;
import com.acme.autoprotracker.workshop.infrastructure.persistence.jpa.repositories.AdvertisingRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisingCommandServicelmpl implements AdvertisingCommandService {
    private final AdvertisingRepository advertisingRepository;

    public AdvertisingCommandServicelmpl(AdvertisingRepository advertisingRepository) {
        this.advertisingRepository = advertisingRepository;
    }


    @Override
    public Long handle(CreateAdvertisingCommand command) {
        if (advertisingRepository.existsByComName(command.comName())) {
            throw new IllegalArgumentException("Advertising with same company name already exists");
        }
        var advertising = new Advertising(command);
        try {
            advertisingRepository.save(advertising);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return advertising.getId();
    }

    @Override
    public Optional<Advertising> handle(UpdateAdvertisingCommand command) {
        var result = advertisingRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Advertising does not exist");
        var advertisingToUpdate = result.get();
        try {
            var advertisingUpdated = advertisingRepository.save(advertisingToUpdate.updateAdvertising(command.comName(),command.comImage(), command.workshopId(),command.imageUrl(), command.slogan(), command.priceMsg(), command.disMsg(), command.repairMsg()));
            return Optional.of(advertisingUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating advertising: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteAdvertisingCommand command) {
        if (!advertisingRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Advertising does not exist");
        }
        try {
            advertisingRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting advertising: " + e.getMessage());
        }

    }
}

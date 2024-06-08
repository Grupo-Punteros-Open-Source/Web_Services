package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Entities.Workshop;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.CustomerResource;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.WorkshopResource;
import org.springframework.stereotype.Component;

@Component
public class WorkshopResourceFromEntityAssembler {

    public WorkshopResource toResourceFromEntity(Workshop workshop){
        return new WorkshopResource(
                workshop.getUserId(),
                workshop.getId()
        );
    }
}

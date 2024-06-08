package com.acme.autoprotracker.User.Interfaces.rest.Transform;

import com.acme.autoprotracker.User.Domain.Model.Commands.DeleteCustomerCommand;
import com.acme.autoprotracker.User.Interfaces.rest.Resources.DeleteCustomerResource;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerCommandFromResourceAssembler {
    public DeleteCustomerCommand toCommandFromResource(DeleteCustomerResource resource){
        return new DeleteCustomerCommand(
                resource.id()
        );
    }
}

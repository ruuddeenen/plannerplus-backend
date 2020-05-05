package com.ruuddeenen.plannerplus.web.assemblers;

import com.ruuddeenen.plannerplus.web.controllers.EmployeeController;
import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeResourceAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    @Override
    public EntityModel<Employee> toModel(Employee entity) {
        try {
            return new EntityModel<>(entity,
                    linkTo(methodOn(EmployeeController.class).getEmployeeById(entity.getUuid())).withSelfRel(),
                    linkTo(methodOn(EmployeeController.class).getAllEmployees()).withRel("employees")
            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

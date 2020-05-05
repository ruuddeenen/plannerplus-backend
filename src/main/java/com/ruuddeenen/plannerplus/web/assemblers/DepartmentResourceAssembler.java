package com.ruuddeenen.plannerplus.web.assemblers;

import com.ruuddeenen.plannerplus.web.controllers.DepartmentController;
import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Department;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentResourceAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {
    @Override
    public EntityModel<Department> toModel(Department entity) {
        try {
            return new EntityModel<>(entity,
                    linkTo(methodOn(DepartmentController.class).getDepartmentById(entity.getId())).withSelfRel(),
                    linkTo(methodOn(DepartmentController.class).getAllDepartments()).withRel("departments")
            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

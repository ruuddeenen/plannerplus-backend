package com.ruuddeenen.plannerplus.web.assemblers;

import com.ruuddeenen.plannerplus.web.controllers.ShiftController;
import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Shift;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShiftResourceAssembler  implements RepresentationModelAssembler<Shift, EntityModel<Shift>> {
    @Override
    public EntityModel<Shift> toModel(Shift entity) {
        try {
            return new EntityModel<>(entity,
                    linkTo(methodOn(ShiftController.class).getShiftById(entity.getId())).withSelfRel(),
                    linkTo(methodOn(ShiftController.class).getAllShifts()).withRel("shifts")
            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
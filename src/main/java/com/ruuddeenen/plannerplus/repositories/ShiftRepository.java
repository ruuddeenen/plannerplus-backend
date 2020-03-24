package com.ruuddeenen.plannerplus.repositories;

import com.ruuddeenen.plannerplus.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

    List<Shift> getByDay(Date day);

}

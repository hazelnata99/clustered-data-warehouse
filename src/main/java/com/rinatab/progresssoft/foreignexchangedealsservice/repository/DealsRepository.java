package com.rinatab.progresssoft.foreignexchangedealsservice.repository;

import com.rinatab.progresssoft.foreignexchangedealsservice.model.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DealsRepository extends JpaRepository<Deal, Integer> {
    Boolean existsFxDealByDealId(int dealId);
}

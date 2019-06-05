package org.lasencinas.cotxox.Repository;

import org.lasencinas.cotxox.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long>, QuerydslPredicateExecutor<Driver> {

    List<Driver> findByBussyIsFalse();
}

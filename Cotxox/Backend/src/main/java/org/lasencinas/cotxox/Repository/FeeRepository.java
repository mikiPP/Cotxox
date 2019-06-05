package org.lasencinas.cotxox.Repository;

import org.lasencinas.cotxox.Model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeeRepository extends JpaRepository<Fee, Long>, QuerydslPredicateExecutor<Fee> {

    Fee getFeeByName(String name);
}

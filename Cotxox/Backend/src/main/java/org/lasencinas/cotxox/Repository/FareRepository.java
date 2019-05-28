package org.lasencinas.cotxox.Repository;

import org.lasencinas.cotxox.Model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<Fare, Long> {
}

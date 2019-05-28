package org.lasencinas.cotxox.Repository;

import org.lasencinas.cotxox.Model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    Fee getFeeByName(String name);
}

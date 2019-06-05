package org.lasencinas.cotxox.Predicates;

import com.querydsl.core.types.Predicate;
import org.lasencinas.cotxox.Model.QFare;

public final class FarePredicates {

    private FarePredicates() {
    }


    public static Predicate idDriverIs(final long id) {
        return QFare.fare.driver.id.eq(id);
    }

    public static Predicate mileageIsGreaterThan(final double mileage) {
        return QFare.fare.mileage.gt(mileage);
    }

    public static Predicate CostIsGreaterThan(final double cost) {
        return QFare.fare.cost.gt(cost);
    }
}

package cft.shift.lab.b4lmor.rangemerger.repository;

import cft.shift.lab.b4lmor.rangemerger.entity.impl.IntInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntIntervalRepository extends JpaRepository<IntInterval, Long> {
    @Query(value = "select * \n" +
            "from int_interval\n" +
            "order by interval_start, interval_end asc\n" +
            "fetch first 1 row only;", nativeQuery = true)
    Optional<IntInterval> findMinInterval();
}

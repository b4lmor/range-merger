package cft.shift.lab.b4lmor.rangemerger.repository;

import cft.shift.lab.b4lmor.rangemerger.entity.impl.StrInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StrIntervalRepository extends JpaRepository<StrInterval, Long> {
    @Query(value = "select * \n" +
            "from str_interval\n" +
            "order by interval_start, interval_end asc\n" +
            "fetch first 1 row only;", nativeQuery = true)
    Optional<StrInterval> findMinInterval();
}

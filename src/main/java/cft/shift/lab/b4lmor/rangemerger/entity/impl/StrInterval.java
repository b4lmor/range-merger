package cft.shift.lab.b4lmor.rangemerger.entity.impl;

import cft.shift.lab.b4lmor.rangemerger.entity.IInterval;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Str_Interval")
public class StrInterval implements IInterval<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interval_id",
            updatable = false,
            nullable = false,
            unique=true)
    private Long intervalId;
    @Column(name = "interval_start")
    @Pattern(regexp = "[a-zA-Z]")
    private String intervalStart;
    @Column(name = "interval_end")
    @Pattern(regexp = "[a-zA-Z]")
    private String intervalEnd;

    public StrInterval(Long id, String start, String end) {
        this.intervalId = id;
        this.intervalStart = start;
        this.intervalEnd = end;
    }
    public StrInterval(String start, String end) {
        this.intervalStart = start;
        this.intervalEnd = end;
    }
    public StrInterval() {
    }

    public String getIntervalEnd() {
        return intervalEnd;
    }

    public String getIntervalStart() {
        return intervalStart;
    }

    public Long getIntervalId() {
        return intervalId;
    }

    public void setIntervalEnd(String intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    public void setIntervalStart(String intervalStart) {
        this.intervalStart = intervalStart;
    }

    public void setIntervalId(Long id) {
        this.intervalId = id;
    }

    @Override
    public String toString() {
        return "[" + getIntervalStart() + "; " +
                getIntervalEnd() + "]";
    }

    @Override
    public int compareTo(IInterval<String> o) {
        return this.intervalStart.compareTo(o.getIntervalStart());
    }
}

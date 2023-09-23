package cft.shift.lab.b4lmor.rangemerger.entity.impl;

import cft.shift.lab.b4lmor.rangemerger.entity.IInterval;
import jakarta.persistence.*;

@Entity
@Table(name = "Int_Interval")
public class IntInterval implements IInterval<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interval_id",
            updatable = false,
            nullable = false,
            unique=true)
    private Long intervalId;
    @Column(name = "interval_start")
    private Integer intervalStart;
    @Column(name = "interval_end")
    private Integer intervalEnd;

    public IntInterval(Long id, Integer start, Integer end) {
        this.intervalId = id;
        this.intervalStart = start;
        this.intervalEnd = end;
    }
    public IntInterval(Integer start, Integer end) {
        this.intervalStart = start;
        this.intervalEnd = end;
    }
    public IntInterval() {
    }

    public Integer getIntervalEnd() {
        return intervalEnd;
    }

    public Integer getIntervalStart() {
        return intervalStart;
    }

    public Long getIntervalId() {
        return intervalId;
    }

    public void setIntervalEnd(Integer intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    public void setIntervalStart(Integer intervalStart) {
        this.intervalStart = intervalStart;
    }

    public void setIntervalId(Long intervalId) {
        this.intervalId = intervalId;
    }

    @Override
    public String toString() {
        return "[" + getIntervalStart().toString() + "; " +
                getIntervalEnd().toString() + "]";
    }

    @Override
    public int compareTo(IInterval<Integer> o) {
        return intervalStart.compareTo(o.getIntervalStart());
    }
}

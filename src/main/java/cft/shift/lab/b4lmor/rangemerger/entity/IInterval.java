package cft.shift.lab.b4lmor.rangemerger.entity;

public interface IInterval<T extends Comparable<T>> extends Comparable<IInterval<T>> {
    public T getIntervalEnd();

    public T getIntervalStart();

    public Long getIntervalId();

    public void setIntervalEnd(T intervalEnd);

    public void setIntervalStart(T intervalStart);

    public void setIntervalId(Long intervalId);
}

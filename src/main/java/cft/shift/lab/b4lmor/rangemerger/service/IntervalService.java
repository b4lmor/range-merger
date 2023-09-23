package cft.shift.lab.b4lmor.rangemerger.service;

import cft.shift.lab.b4lmor.rangemerger.DTO.impl.IntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.utils.Kind;

import java.util.List;

public interface IntervalService {
    public IntervalDTO<?> getMinInterval(Kind kind);
    public void mergeIntervals(Kind kind, List<IntervalDTO<?>> intervalDTOs);
}

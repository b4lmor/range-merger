package cft.shift.lab.b4lmor.rangemerger.service.impl;

import cft.shift.lab.b4lmor.rangemerger.DTO.impl.IntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.entity.impl.IntInterval;
import cft.shift.lab.b4lmor.rangemerger.entity.impl.StrInterval;
import cft.shift.lab.b4lmor.rangemerger.exception.MinIntervalNotFoundException;
import cft.shift.lab.b4lmor.rangemerger.repository.IntIntervalRepository;
import cft.shift.lab.b4lmor.rangemerger.repository.StrIntervalRepository;
import cft.shift.lab.b4lmor.rangemerger.service.IntervalService;
import cft.shift.lab.b4lmor.rangemerger.utils.IntervalMerger;
import cft.shift.lab.b4lmor.rangemerger.utils.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntervalServiceImpl implements IntervalService {
    private final IntIntervalRepository intIntervalRepository;
    private final StrIntervalRepository strIntervalRepository;
    @Autowired
    public IntervalServiceImpl(
            IntIntervalRepository intervalRepository,
            StrIntervalRepository strIntervalRepository) {
        this.intIntervalRepository = intervalRepository;
        this.strIntervalRepository = strIntervalRepository;
    }

    @Override
    public IntervalDTO<?> getMinInterval(Kind kind) {
        switch (kind) {
            case DIGITS -> {
                IntInterval interval = intIntervalRepository.findMinInterval()
                        .orElseThrow(MinIntervalNotFoundException::new);
                return new IntervalDTO<>(
                        interval.getIntervalStart(),
                        interval.getIntervalEnd()
                );
            }
            case LETTERS -> {
                StrInterval interval = strIntervalRepository.findMinInterval()
                        .orElseThrow(MinIntervalNotFoundException::new);
                return new IntervalDTO<>(
                        interval.getIntervalStart(),
                        interval.getIntervalEnd()
                );
            }
            default -> {
                throw new RuntimeException("undefined behavior");
            }
        }
    }

    @Override
    public void mergeIntervals(Kind kind, List<IntervalDTO<?>> intervalDTOs) {
        switch (kind) {
            case DIGITS -> {
                List<IntInterval> intervals = intervalDTOs.stream()
                        .map(interval -> new IntInterval(
                                (Integer) interval.getStart(),
                                (Integer) interval.getEnd()
                        ))
                        .collect(Collectors.toList());
                List<IntInterval> mergedIntervals =
                        IntervalMerger.<Integer, IntInterval>merge(intervals);
                mergedIntervals.forEach(intIntervalRepository::save);
            }
            case LETTERS -> {
                List<StrInterval> intervals = intervalDTOs.stream()
                        .map(interval -> new StrInterval(
                                (String) interval.getStart(),
                                (String) interval.getEnd()
                        ))
                        .collect(Collectors.toList());
                List<StrInterval> mergedIntervals =
                        IntervalMerger.<String, StrInterval>merge(intervals);
                mergedIntervals.forEach(strIntervalRepository::save);
            }
        }
    }
}

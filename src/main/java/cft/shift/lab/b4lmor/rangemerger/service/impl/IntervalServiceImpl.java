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
        IntervalDTO<?> minIntervalDTO = null;
        switch (kind) {
            case DIGITS -> {
                IntInterval interval = intIntervalRepository.findMinInterval()
                        .orElseThrow(MinIntervalNotFoundException::new);
                minIntervalDTO = new IntervalDTO<>(
                        interval.getIntervalStart(),
                        interval.getIntervalEnd()
                );
            }
            case LETTERS -> {
                StrInterval interval = strIntervalRepository.findMinInterval()
                        .orElseThrow(MinIntervalNotFoundException::new);
                minIntervalDTO = new IntervalDTO<>(
                        interval.getIntervalStart(),
                        interval.getIntervalEnd()
                );
            }
        }
        return minIntervalDTO;
    }

    @Override
    public void mergeIntervals(Kind kind, List<IntervalDTO<?>> intervalDTOs) {
        switch (kind) {
            case DIGITS -> {
                List<IntInterval> intervals = new ArrayList<>();
                for (IntervalDTO<?> intervalDTO : intervalDTOs) {
                    IntInterval interval = new IntInterval();
                    interval.setIntervalStart((Integer) intervalDTO.getStart());
                    interval.setIntervalEnd((Integer) intervalDTO.getEnd());
                    intervals.add(interval);
                }
                List<IntInterval> mergedIntervals =
                        IntervalMerger.<Integer, IntInterval>merge(intervals);
                for (IntInterval interval : mergedIntervals) {
                    intIntervalRepository.save(interval);
                }
            }
            case LETTERS -> {
                List<StrInterval> intervals = new ArrayList<>();
                for (IntervalDTO<?> intervalDTO : intervalDTOs) {
                    StrInterval interval = new StrInterval();
                    interval.setIntervalStart((String) intervalDTO.getStart());
                    interval.setIntervalEnd((String) intervalDTO.getEnd());
                    intervals.add(interval);
                }
                List<StrInterval> mergedIntervals =
                        IntervalMerger.<String, StrInterval>merge(intervals);
                for (StrInterval interval : mergedIntervals) {
                    strIntervalRepository.save(interval);
                }
            }
        }
    }
}

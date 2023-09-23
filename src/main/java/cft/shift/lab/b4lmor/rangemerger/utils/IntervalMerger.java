package cft.shift.lab.b4lmor.rangemerger.utils;

import cft.shift.lab.b4lmor.rangemerger.entity.IInterval;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntervalMerger {
    public static <
            U extends Comparable<U>, 
            T extends IInterval<U>
            > List<T> merge(List<T> intervals) {
        List<T> mergedIntervals = new ArrayList<>();
        Collections.sort(intervals);
        T prev = intervals.get(0);
        mergedIntervals.add(prev);
        T cur;
        for (int i = 1; i < intervals.size(); i++) {
            cur = intervals.get(i);
            if (prev.getIntervalStart().compareTo(cur.getIntervalEnd()) <= 0
                    && prev.getIntervalEnd().compareTo(cur.getIntervalStart()) >= 0) {
                T merged = mergedIntervals.get(mergedIntervals.size() - 1);
                merged.setIntervalStart(
                        prev.getIntervalStart()
                                .compareTo(cur.getIntervalStart()) < 0 ?
                                prev.getIntervalStart() :
                                cur.getIntervalStart()
                );
                merged.setIntervalEnd(
                        prev.getIntervalEnd()
                                .compareTo(cur.getIntervalEnd()) > 0 ?
                                prev.getIntervalEnd() :
                                cur.getIntervalEnd()
                );
                mergedIntervals.set(mergedIntervals.size() - 1, merged);
            } else { mergedIntervals.add(cur); }
            prev = mergedIntervals.get(mergedIntervals.size() - 1);
        }
        return mergedIntervals;
    }
}

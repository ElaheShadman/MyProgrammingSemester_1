package hospital;

import java.util.*;
import java.util.stream.Collectors;

public class Analytics {

    public static double averageDuration(List<Surgery> logs) {
        return logs.stream().mapToInt(s -> s.duration).average().orElse(0);
    }

    public static double averageRecovery(List<Surgery> logs) {
        return logs.stream().mapToInt(s -> s.recoveryDays).average().orElse(0);
    }

    public static int totalOrMinutes(List<Surgery> logs) {
        return logs.stream().mapToInt(s -> s.duration).sum();
    }

    public static Surgery longestSurgery(List<Surgery> logs) {
        return logs.stream().max(Comparator.comparingInt(s -> s.duration)).orElse(null);
    }

    public static Surgery shortestSurgery(List<Surgery> logs) {
        return logs.stream().min(Comparator.comparingInt(s -> s.duration)).orElse(null);
    }

    public static Map<String, Double> avgDurationByDept(List<Surgery> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(
                        s -> s.department,
                        Collectors.averagingInt(s -> s.duration)
                ));
    }

    public static Map<String, Long> countByDept(List<Surgery> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(
                        s -> s.department,
                        Collectors.counting()
                ));
    }

    public static Map<String, Long> countByProcedure(List<Surgery> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(
                        s -> s.procedureName,
                        Collectors.counting()
                ));
    }
}

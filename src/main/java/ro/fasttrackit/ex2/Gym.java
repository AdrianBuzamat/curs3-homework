package ro.fasttrackit.ex2;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Data
public class Gym {
    private final List<Member> members = new ArrayList<>();
    private final Map<String, Duration> subscriptions;
    private final Map<String, Duration> timeSpent;


    public Gym(List<Member> members) {
        this.members.addAll(members);
        this.subscriptions = members.stream().collect(Collectors.toMap(member -> member.name(), name -> Duration.ofHours(0)));
        this.timeSpent = members.stream().collect(Collectors.toMap(member -> member.name(), name -> Duration.ofHours(0)));
    }

    public void registerTimeSpentByMember(String membersName, Duration durationHours) {
        if (subscriptions.get(membersName).compareTo(durationHours) >= 0) {
            timeSpent.put(membersName, timeSpent.get(membersName).plus(durationHours));
            subscriptions.put(membersName, subscriptions.get(membersName).minus(durationHours));
        } else {
            throw new IllegalArgumentException("Buy some time!");
        }
    }

    public void addingTime(String membersName, Duration timeInHours) {
        if (subscriptions.containsKey(membersName)) {
            subscriptions.put(membersName, subscriptions.get(membersName).plus(timeInHours));
        } else {
            throw new IllegalArgumentException("not such member");
        }
    }

    public Duration totalRemainingTime() {
        return subscriptions.values().stream()
                .reduce(Duration.ZERO, Duration::plus);
    }


   public IntSummaryStatistics ageStatistics(){
       return members.stream()
               .mapToInt(member -> findAge(member))
               .summaryStatistics();
    }

    public OptionalInt findMinAge() {
        return members.stream()
                .mapToInt(member -> findAge(member))
                .min();
    }

    public OptionalInt findMaxAge() {
        return members.stream()
                .mapToInt(member -> findAge(member))
                .max();
    }

    public OptionalDouble findAvgAge() {
        return members.stream()
                .mapToInt(member -> findAge(member))
                .average();
    }


    Function<Duration, FlagTag> person2Group = duration -> FlagTag.RED;


    private int findAge(Member member) {
        LocalDate birthday = member.birthday();
        return LocalDate.now().minus(Period.of(birthday.getYear(), birthday.getMonthValue(), birthday.getDayOfMonth())).getYear();
    }
}

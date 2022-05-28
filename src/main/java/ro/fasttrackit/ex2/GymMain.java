package ro.fasttrackit.ex2;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class GymMain {
    public static void main(String[] args) {
        Gym gym = new Gym(
                List.of(
                        new Member("Kia", LocalDate.of(2000, 2, 23))
                        , new Member("Shaunte", LocalDate.of(2002, 8, 12))
                        , new Member("Mack", LocalDate.of(2003, 6, 3))
                        , new Member("Zolo", LocalDate.of(2021, 6, 3))
                        , new Member("Arnulfo", LocalDate.of(2006, 4, 6))
        ));

        gym.addingTime("Kia", Duration.ofHours(10));
        gym.registerTimeSpentByMember("Kia", Duration.ofHours(10));
        gym.addingTime("Kia", Duration.ofHours(10));
        System.out.println(gym.getSubscriptions());
        System.out.println(gym.getTimeSpent());

        System.out.println(gym.totalRemainingTime());

        System.out.println(gym.findMinAge());
        System.out.println(gym.ageStatistics());


    }
}

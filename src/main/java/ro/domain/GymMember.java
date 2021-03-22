package ro.domain;

import java.time.LocalDate;
import java.time.Period;

public class GymMember {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private Period subscription;

    public GymMember(Integer id, String name, LocalDate birthDate, Period subscription) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.subscription = subscription;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Period getSubscription() {
        return subscription;
    }
}

package ro.service;

import ro.domain.GymMember;

import java.time.LocalDate;
import java.time.Period;

public class GymMemberBuilder {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private Period subscription;

    public GymMember getGymMember(){
        return new GymMember(id, name, birthDate, subscription);
    }

    public GymMemberBuilder setId(Integer id){
        this.id = id;
        return this;
    }

    public GymMemberBuilder setName(String name){
        this.name = name;
        return this;
    }

    public GymMemberBuilder setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
        return this;
    }

    public GymMemberBuilder setSubscription(Period subscription){
        this.subscription = subscription;
        return this;
    }
}



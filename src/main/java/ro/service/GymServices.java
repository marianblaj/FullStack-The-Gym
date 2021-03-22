package ro.service;

import ro.domain.GymMember;
import ro.repository.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GymServices {

    private Repository repository = new Repository();

    public GymServices(Repository repository) {
        this.repository = repository;
    }

    public void addGymMember(Integer id, String name, LocalDate birthDate, Period subscription) {
        repository.addEntity(new GymMember(id, name, birthDate, subscription));
    }

    public void deleteGymMember(Integer id) {
        repository.deleteEntity(id);
    }

    public void updateGymMember(Integer id, String name, LocalDate birthDate, Period subscription) {
        repository.updateEntity(new GymMember(id, name, birthDate, subscription));
    }

    public List<GymMember> findALl() {
        return repository.getAllMembers();
    }

    public GymMember getInfoByName(String name) throws RuntimeException{
        if (name == null)
            throw new RuntimeException("Not existent in Database.");
        return repository.getAllMembers()
                .stream()
                .filter(element ->element.getName()==name)
                .findAny().orElseThrow();

    }

    public Boolean containsOne(Integer id) {
        return repository.findOne(id).isPresent();
    }

    public void updatePeriod(Integer id, Period timeToBeAdded) throws RuntimeException {
        if (!containsOne(id))
            throw new RuntimeException("Not existent in Database.");

        GymMember oldGymMember = repository.findOne(id).orElseThrow();
        Period newPeriod = oldGymMember.getSubscription().plus(timeToBeAdded);
        GymMember newGymMember = new GymMember(id,
                oldGymMember.getName(),
                oldGymMember.getBirthDate(),
                newPeriod);
        repository.updateEntity(newGymMember);
    }

    public String minMaxAverage() throws RuntimeException {

        int min = repository.getAllMembers()
                .stream()
                .mapToInt((element) -> ageOfGymMember(element.getBirthDate()))
                .min()
                .orElseThrow(RuntimeException::new);

        double average = repository.getAllMembers()
                .stream()
                .mapToDouble((element) -> ageOfGymMember(element.getBirthDate()))
                .average()
                .orElseThrow(RuntimeException::new);

        int max = repository.getAllMembers()
                .stream()
                .mapToInt((element) -> ageOfGymMember(element.getBirthDate()))
                .max()
                .orElseThrow(RuntimeException::new);

        return "Minimum age: " + min +
                "\nAverage age: " + average +
                "\nMaximum age: " + max + "\n";
    }

    private Integer ageOfGymMember(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Period totalRemainingTime() {
        return repository.getAllMembers()
                .stream()
                .map(GymMember::getSubscription)
                .reduce(Period.ofDays(0), Period::plus);
    }



}

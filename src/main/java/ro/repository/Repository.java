package ro.repository;

import ro.domain.Gym;
import ro.domain.GymMember;

import java.util.*;

public class Repository {

    private Map<Integer, GymMember> gymMembers = new HashMap<>();

    public void addEntity(GymMember gymMember) throws RuntimeException {

        if (gymMember == null)
            throw new RuntimeException("Gym member cannot be null");

        gymMembers.putIfAbsent(gymMember.getId(), gymMember);
    }

    public void deleteEntity(Integer id) throws RuntimeException {
        if (id == null)
            throw new RuntimeException("Id cannot be null");
        gymMembers.remove(id);
    }

    public void updateEntity(GymMember gymMember) throws RuntimeException{
        if (gymMember == null)
            throw new RuntimeException("Gym member cannot be null");
        gymMembers.computeIfPresent(gymMember.getId(), (k, v) -> gymMember);
    }

    public List<GymMember> getAllMembers(){
        return new ArrayList<>(gymMembers.values());
    }

    public Optional<GymMember> findOne(Integer id) throws RuntimeException{
        if (id == null)
            throw new RuntimeException("Id cannot be null");
        return Optional.ofNullable(gymMembers.get(id));
    }
}

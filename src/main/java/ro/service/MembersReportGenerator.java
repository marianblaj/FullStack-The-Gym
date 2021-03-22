package ro.service;

import ro.domain.GymMember;
import ro.repository.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MembersReportGenerator {

    private Repository repository = new Repository();

    public MembersReportGenerator(Repository repository) {
        this.repository = repository;
    }


    public void generateReport(String outputFile) throws IOException {


        writeReport(repository.getAllMembers(), outputFile);

    }

    private void writeReport(List<GymMember> listMembers, String outputFile) throws IOException {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            List<GymMember> RED = listMembers.stream()
                    .filter(element -> element.getSubscription().getDays() < 10)
                    .collect(Collectors.toList());

            writeMemberLine(writer, "RED:");
            RED.stream().forEach(element -> writeMemberLine(writer, element.getName()));


            List<GymMember> YELLOW = listMembers.stream()
                    .filter(element -> element.getSubscription().getDays() < 30)
                    .collect(Collectors.toList());
            writeMemberLine(writer, "YELLOW:");
            YELLOW.stream().forEach(element -> writeMemberLine(writer, element.getName()));

            List<GymMember> GREEN = listMembers.stream()
                    .filter(element -> element.getSubscription().getDays() >= 30)
                    .collect(Collectors.toList());
            writeMemberLine(writer, "GREEN:");
            GREEN.stream().forEach(element -> writeMemberLine(writer, element.getName()));

        }
    }


    private void writeMemberLine(BufferedWriter writer, String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

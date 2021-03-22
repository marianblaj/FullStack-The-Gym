package ro;

import ro.domain.GymMember;
import ro.repository.Repository;
import ro.service.GymMemberBuilder;
import ro.service.GymServices;
import ro.service.MembersReportGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws IOException {

        GymMemberBuilder gymMemberBuilder = new GymMemberBuilder();
        Repository repo = new Repository();
        GymServices service = new GymServices(repo);

        repo.addEntity(getMember1(gymMemberBuilder));
        repo.addEntity(getMember2(gymMemberBuilder));
        repo.addEntity(getMember3(gymMemberBuilder));
        repo.addEntity(getMember4(gymMemberBuilder));



        //testReturnMinMixAverage
        System.out.println(service.minMaxAverage());

        //testTotalRemainingTime
        System.out.println(service.totalRemainingTime().getDays());

        //testAddingTimeToMember
        service.updatePeriod(1,Period.ofDays(30));

        //retreivingInfoByName
        System.out.println(service.getInfoByName("Name1").getId());

        //writeReport

        MembersReportGenerator membersReportGenerator = new MembersReportGenerator(repo);
        membersReportGenerator.generateReport("src/main/resources/GymMembers.txt");





        //testLocalDate
       // LocalDate localDate = LocalDate.parse("2018-12-06");
      //  System.out.println(Period.between(localDate, LocalDate.now()).getYears());





    }



    private static GymMember getMember1(GymMemberBuilder gymMemberBuilder) {
        return gymMemberBuilder.setId(1)
                .setName("Name1")
                .setBirthDate(LocalDate.parse("2018-12-06"))
                .setSubscription(Period.ofDays(5))
                .getGymMember();
    }

    private static GymMember getMember2(GymMemberBuilder gymMemberBuilder) {
        return gymMemberBuilder.setId(2)
                .setName("Name2")
                .setBirthDate(LocalDate.parse("1992-12-06"))
                .setSubscription(Period.ofDays(1))
                .getGymMember();
    }

    private static GymMember getMember3(GymMemberBuilder gymMemberBuilder) {
        return gymMemberBuilder.setId(3)
                .setName("Name3")
                .setBirthDate(LocalDate.parse("1980-12-06"))
                .setSubscription(Period.ofDays(29))
                .getGymMember();
    }

    private static GymMember getMember4(GymMemberBuilder gymMemberBuilder) {
        return gymMemberBuilder.setId(4)
                .setName("Name4")
                .setBirthDate(LocalDate.parse("2010-12-06"))
                .setSubscription(Period.ofDays(124))
                .getGymMember();
    }


}
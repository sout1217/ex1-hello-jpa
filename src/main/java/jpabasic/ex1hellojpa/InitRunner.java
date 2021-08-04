package jpabasic.ex1hellojpa;

import jpabasic.ex1hellojpa.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitRunner implements ApplicationRunner {

    private final MemberRepo memberRepo;
    private final OrderRepo orderRepo;
    private final ItemRepo itemRepo;
    private final TeamRepo teamRepo;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Team team = new Team();
        team.setName("Team A");
        teamRepo.save(team);

        Member member = new Member();
        member.setName("martin");
        member.setCity("seoul");
        team.addMember(member);
        memberRepo.save(member);

        Team findTeam = teamRepo.findById(1L).get();

        System.out.println(team);
        findTeam.getMembers().forEach(System.out::println);


//        Item item = new Item();
//        item.setName("computer");
//
//        itemRepo.save(item);
//        memberRepo.findAll().forEach(System.out::println);
//        itemRepo.findAll().forEach(System.out::println);
//
//
//        Order order = new Order();
//        order.setMember(member);
//        order.setStatus(OrderStatus.ORDER);
//        order.setOrderDate(LocalDateTime.now());
//        orderRepo.save(order);
//        orderRepo.findAll().forEach(System.out::println);
    }
}

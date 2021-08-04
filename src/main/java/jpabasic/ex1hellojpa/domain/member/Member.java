package jpabasic.ex1hellojpa.domain.member;


import jpabasic.ex1hellojpa.domain.order.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = "orders")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
    }
}

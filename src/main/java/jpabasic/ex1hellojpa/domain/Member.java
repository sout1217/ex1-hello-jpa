package jpabasic.ex1hellojpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = "team")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

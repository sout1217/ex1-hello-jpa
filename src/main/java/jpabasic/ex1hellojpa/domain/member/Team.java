package jpabasic.ex1hellojpa.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        // 실무에서는 기존에 있는 경우 제거해
        members.add(member);
        member.setTeam(this);
    }

}

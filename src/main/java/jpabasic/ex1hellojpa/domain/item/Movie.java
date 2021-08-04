package jpabasic.ex1hellojpa.domain.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Movie extends Item{

    private String director;
    private String actor;
}

package jpabasic.ex1hellojpa.domain.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Book extends Item{

    private String author;
    private String isbn;
}

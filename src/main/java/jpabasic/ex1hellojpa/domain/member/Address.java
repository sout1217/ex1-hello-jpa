package jpabasic.ex1hellojpa.domain.member;


import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String city;

    private String street;

    private String zipcode;

    /*
    * EqualsAndHashCode 는 string 으로 오타가 나도 에러를 잡지 못하고 오타난 부분의 값이 틀린 경우에도 equal 1true 를 내뱉는다는 문제가 있음
    * 때문에 인라인으로 때려박는게 좋다 (빌드 시 오류를 찾을 수 있음)
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}

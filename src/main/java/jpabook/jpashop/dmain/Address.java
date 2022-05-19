package jpabook.jpashop.dmain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter // 값 타입은 값이 변경되면 안 된다.
public class Address {

    private String city;
    private String street;
    private String zipcode;
    
    // 값이 변경되면 안 되기 때문에 생성 시점에서 값을 초기화
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    // JPA 스펙상 엔티티는 기본 생성자가 필수이다.
    protected Address() {}

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
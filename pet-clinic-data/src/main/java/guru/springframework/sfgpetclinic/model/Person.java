package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

//    @Builder
    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;


}

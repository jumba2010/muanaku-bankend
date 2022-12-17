package jumba.tec.muanaku.client.domain;


import jumba.tec.muanaku.user.domain.Company;
import jumba.tec.muanaku.utils.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {


    @Id
    private Long Id;

    @NotBlank(message = ValidationMessages.THE_CLIENT_NAME_IS_REQUIRED)
    @Max(value = 100,message = ValidationMessages.THE_NAME_LENGTH_SHOULD_BE_LESS_OR_EQUAL_TO_100)
    @Column(name="name",nullable = false)
    private String name;

    @Pattern(regexp="(^$|[0-9]{10})",message = ValidationMessages.INVALID_PHONE_NUMBER)
    @NotBlank(message = ValidationMessages.THE_CONTACT_IS_REQUIRED)
    @Column(name="contact",nullable = false)
    private String contact;

    @Email(message = ValidationMessages.INVALID_EMAIL)
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="company_id",nullable = false)
    private Long companyId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;
}

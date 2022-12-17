package jumba.tec.muanaku.chickenbatch.domain;

import jumba.tec.muanaku.user.domain.Company;
import jumba.tec.muanaku.utils.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@Entity
public class ChickenBatch {

    @Id
    private Long Id;

    @Column(name = "alive_quantity",nullable = false)
    @Min(value=1,message= ValidationMessages.THE_MINIMUM_VALUE_FOR_ALIVE_QUANTITY_SHOULD_BE_1)
    private int aliveQuantity;

    @Column(name = "death_quantity",nullable = false)
    private int deathQuantity;

    @NotNull(message = ValidationMessages.THE_ENTRANCE_DATE_IS_REQUIRED)
    @Column(name = "entrance_date",nullable = false)
    private LocalDate entranceDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;

    @NotNull(message = ValidationMessages.THE_EXPECTED_EXIT_DATE_IS_REQUIRED)
    @Column(name = "expected_exit_date",nullable = false)
    private LocalDate expectedExitDate;

    @NotNull(message =ValidationMessages.THE_PLUCKED_PRICE_IS_REQUIRED)
    @Positive(message =ValidationMessages.PLUCKED_PRICE_SHOULD_BE_GREATER_THAN_ZERO)
    @Column(name = "plucked_price",nullable = false)
    private BigDecimal pluckedPrice;

    @NotNull(message = ValidationMessages.THE_UN_PLUCKED_PRICE_IS_REQUIRED)
    @Positive(message =ValidationMessages. UN_PLUCKED_PRICE_SHOULD_BE_GREATER_THAN_ZERO)
    @Column(name = "un_plucked_price",nullable = false)
    private BigDecimal unPluckedPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Company company;

    @NotNull(message =ValidationMessages.THE_COMPANY_ID_IS_REQUIRED)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private Long companyId;


}

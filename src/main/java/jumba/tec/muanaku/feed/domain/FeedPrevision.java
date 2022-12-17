package jumba.tec.muanaku.feed.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@AllArgsConstructor
@Entity
public class FeedPrevision {
    @Id
    private Long Id;

    @Column(name="created_at",nullable = false)
    private Instant createdAt;

    @Column(name="number_of_days",nullable = false)
    private int numberOfDays;

    @Column(name="number_of_bags",nullable = false)
    private int numberOfBags;

    @Column(name="number_of_chickens",nullable = false)
    private int numberOfChickens;

}

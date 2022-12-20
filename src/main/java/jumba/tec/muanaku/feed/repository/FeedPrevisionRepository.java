package jumba.tec.muanaku.feed.repository;

import jumba.tec.muanaku.feed.domain.FeedPrevision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedPrevisionRepository extends JpaRepository<FeedPrevision,Long> {
    List<FeedPrevision> findByCompanyIdAndOfDayGreaterThanOrEqualTo(Long companyId, long daysPassed);

    List<FeedPrevision> findByCompanyId(Long companyId);
}

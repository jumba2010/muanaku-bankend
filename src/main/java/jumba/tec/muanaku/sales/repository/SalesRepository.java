package jumba.tec.muanaku.sales.repository;

import jumba.tec.muanaku.sales.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesOrder,Long> {
}

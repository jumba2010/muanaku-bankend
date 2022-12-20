package jumba.tec.muanaku.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> {
    private long pageSize;
    private long totalElements;
    private long totalPerPage;
    List<T> pageElements;
}

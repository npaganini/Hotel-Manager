package ar.edu.itba.paw.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PaginatedDTO<T> {
    private List<T> list;
    private long maxItems;
}

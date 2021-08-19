package demo.deliveryservice.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

    private LocalDateTime transactionTime;

    private String description;

    private T data;

    private Pagination pagination;

    public Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .description("OK")
                .build();
    }

    public Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .description("OK")
                .data(data)
                .build();
    }

    public Header<T> OK(T data, Pagination pagination) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    public Header ERROR(String description) {
        return Header.builder()
                .transactionTime(LocalDateTime.now())
                .description(description)
                .build();
    }

}

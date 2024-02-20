package com.project.book.request;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "details")
@NoArgsConstructor
public class Request {
    @NotNull(message = "Empty UserName Not Allowed")
    private String name;
    @NotNull
    private String author;
    private String published_by;
    private int no_of_copies;
}

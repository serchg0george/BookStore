package com.bookstore.dto;

import com.bookstore.dto.base.BaseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 60)
    private String bookTitle;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 100)
    private String bookAuthor;

    @NotNull
    @Max(13)
    private Integer bookIsbn;

}
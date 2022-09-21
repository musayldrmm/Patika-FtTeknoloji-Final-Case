package com.ftTeknoloji.finalcase.excetions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionsResponse {
    private String errorMessage;
    private int status;

}

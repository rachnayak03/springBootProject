package com.info.springRestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class AddStudentRequestDTO {

        private String name;
        private String email;


}

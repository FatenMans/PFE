package com.example.jwtauth.Entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}

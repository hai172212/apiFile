package com.example.spring4mbankingapisasu.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
private String name;
private int id;
private String  gender;
private String onesignal;
private Boolean isDeleted;
private String studentCard;
private Boolean isStudent;

}

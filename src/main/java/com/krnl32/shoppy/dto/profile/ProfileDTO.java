package com.krnl32.shoppy.dto.profile;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String bio;
	private String company;
	private Long userId;
}

package com.krnl32.shoppy.dto.profile;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfilePatchRequestDTO {
	@Size(max = 32, message = "First Name can up up to 32 Characters")
	private String firstName;

	@Size(max = 32, message = "Last Name can up up to 32 Characters")
	private String lastName;

	private LocalDate dateOfBirth;

	@Size(max = 15, message = "Phone Number can up up to 15 Characters")
	private String phoneNumber;

	@Size(max = 120, message = "Bio can up up to 120 Characters")
	private String bio;

	@Size(max = 32, message = "Company can up up to 32 Characters")
	private String company;
}

package com.krnl32.shoppy.dto.profile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCreateRequestDTO {
	@NotNull(message = "First Name Required")
	@Size(max = 32, message = "First Name can up up to 32 Characters")
	private String firstName;

	@NotNull(message = "Last Name Required")
	@Size(max = 32, message = "Last Name can up up to 32 Characters")
	private String lastName;

	@NotNull(message = "Date of Birth Required")
	private LocalDate dateOfBirth;

	@NotNull(message = "Phone Number Required")
	@Size(max = 15, message = "Phone Number can up up to 15 Characters")
	private String phoneNumber;

	@NotNull(message = "Bio Required")
	@Size(max = 120, message = "Bio can up up to 120 Characters")
	private String bio;

	@NotNull(message = "Company Required")
	@Size(max = 32, message = "Company can up up to 32 Characters")
	private String company;

	@NotNull(message = "userId Required")
	private Long userId;
}

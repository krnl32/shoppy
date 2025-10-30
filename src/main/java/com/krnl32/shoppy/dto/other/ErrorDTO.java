package com.krnl32.shoppy.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private int status;
    private String error;
	private String path;
    private Long timestamp;
}

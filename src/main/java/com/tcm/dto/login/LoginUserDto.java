package com.tcm.dto.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDto {
	private String userId;
	private String password;
}

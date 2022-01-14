package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDto extends BaseDto {
	private Long id ;
	private String name  ;
	private int age ;
	private long salary ;
}	

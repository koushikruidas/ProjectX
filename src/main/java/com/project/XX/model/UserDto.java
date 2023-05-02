package com.project.XX.model;

import com.project.XX.util.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String name;
    private Role role;
}

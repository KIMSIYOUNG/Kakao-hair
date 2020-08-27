package com.example.kakaohair.user.owner.web;

import java.beans.ConstructorProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties(
    {"name", "phone", "email", "license"}))
@Getter
public class OwnerUpdateRequest {
    private final String name;
    private final String phone;
    private final String email;
    private final String license;
}

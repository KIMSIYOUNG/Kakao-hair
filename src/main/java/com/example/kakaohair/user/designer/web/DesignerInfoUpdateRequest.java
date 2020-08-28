package com.example.kakaohair.user.designer.web;

import java.beans.ConstructorProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties(""))
@Builder
@Getter
public class DesignerInfoUpdateRequest {
    @Valid @NotNull
    private final MemberInfo memberInfo;

}

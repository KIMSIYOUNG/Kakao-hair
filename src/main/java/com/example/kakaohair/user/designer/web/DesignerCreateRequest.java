package com.example.kakaohair.user.designer.web;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.kakaohair.user.designer.Designer;
import com.example.kakaohair.user.member.domain.memberinfo.MemberInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class DesignerCreateRequest {
    @Valid @NotNull
    private MemberInfo memberInfo;

    public Designer toDesigner() {
        return Designer.builder()
            .memberInfo(memberInfo)
            .build();
    }
}
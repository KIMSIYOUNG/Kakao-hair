package com.example.kakaohair.docs;

import static com.example.kakaohair.docs.ApiDocumentationUtils.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import org.apache.http.HttpHeaders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

public class MemberDocumentation {

    public static RestDocumentationResultHandler createMember() {
        return document("member/create-member",
            getDocumentRequest(),
            getDocumentResponse(),
            requestHeaders(
                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
            ),
            requestFields(
                fieldWithPath("memberInfo.name").type(STRING).description("회원의 이름"),
                fieldWithPath("memberInfo.socialId").type(STRING).description("회원의 카카오 아이디"),
                fieldWithPath("memberInfo.email").type(STRING).description("회원의 이메일"),
                fieldWithPath("memberInfo.profile").type(STRING).description("회원의 프로필 사진"),
                fieldWithPath("memberInfo.phone").type(STRING).description("회원의 휴대폰 번호"),
                fieldWithPath("memberInfo.gender").type(STRING).description("성별"),
                fieldWithPath("memberInfo.birthday").type(STRING).description("회원의 생일"),
                fieldWithPath("hairInfo.scalp").type(STRING).description("회원의 모발 상태"),
                fieldWithPath("hairInfo.thickness").type(STRING).description("모발이 두께"),
                fieldWithPath("hairInfo.curl").type(STRING).description("곱슬 정도"),
                fieldWithPath("hairInfo.hairCondition").type(STRING).description("손상 정도"),
                fieldWithPath("hairInfo.volume").type(STRING).description("풍성도"),
                fieldWithPath("hairInfo.grayHair").type(STRING).description("새치"),
                fieldWithPath("hairInfo.amount").type(STRING).description("모발의 양")
            ),
            responseFields(
                fieldWithPath("accessToken").description("회원에 대한 Access 토큰")
            )
        );
    }

    public static RestDocumentationResultHandler createMemberFail() {
        return document("member/create-fail",
            getDocumentRequest(),
            getDocumentResponse(),
            requestHeaders(
                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
            ),
            requestFields(
                fieldWithPath("memberInfo").description("회원의 개인 정보"),
                fieldWithPath("hairInfo").description("회원의 모발 정보")
            ),
            getErrorResponseFieldsWithFieldErrors()
        );
    }

    public static RestDocumentationResultHandler redirectToLogin() {
        return document("member/redirect-login",
            getDocumentRequest(),
            getDocumentResponse()
        );
    }

    public static RestDocumentationResultHandler createSocialToken() {
        return document("member/create-social-token",
            getDocumentRequest(),
            getDocumentResponse(),
            requestParameters(
                parameterWithName("code").description("소셜 로그인 성공시 제공받는 Code")
            ),
            responseFields(
                fieldWithPath("accessToken").description("외부에서 발급해주는 토큰")
            )
        );
    }

    public static RestDocumentationResultHandler findMyInfo() {
        return document("member/find-member",
            getDocumentRequest(),
            getDocumentResponse(),
            requestHeaders(
                headerWithName(HttpHeaders.AUTHORIZATION).description("토큰"),
                headerWithName(HttpHeaders.ACCEPT).description("응답 받고자 하는 타입")
            ),
            responseFields(
                fieldWithPath("id").description("찾아온 회원의 아이디"),
                fieldWithPath("name").description("찾아온 회원의 이름"),
                fieldWithPath("socialId").description("찾아온 회원의 소셜 아이디")
            )
        );
    }

    public static RestDocumentationResultHandler deleteById() {
        return document("member/delete-member",
            getDocumentRequest(),
            getDocumentResponse(),
            requestHeaders(
                headerWithName(HttpHeaders.AUTHORIZATION).description("자신의 정보를 담은 토큰")
            )
        );
    }

    public static RestDocumentationResultHandler updateName() {
        return document("member/update-name",
            getDocumentRequest(),
            getDocumentResponse(),
            requestHeaders(
                headerWithName(HttpHeaders.AUTHORIZATION).description("자신의 정보를 담은 토큰"),
                headerWithName(HttpHeaders.CONTENT_TYPE).description("요청의 Body에 있는 정보 타입")
            ),
            responseHeaders(
                headerWithName(HttpHeaders.LOCATION).description("변경된 Resource 값")
            )
        );
    }
}

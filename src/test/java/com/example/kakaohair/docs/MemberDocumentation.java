package com.example.kakaohair.docs;

import static com.example.kakaohair.docs.ApiDocumentationUtils.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
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
                fieldWithPath("name").description("프로필로 사용할 이름"),
                fieldWithPath("socialId").description("소셜 로그인 ID")
            ),
            responseFields(
                fieldWithPath("accessToken").description("사용자가 사용 할 엑세스 토큰")
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
                fieldWithPath("name").description("프로필로 사용할 이름"),
                fieldWithPath("socialId").description("소셜 로그인 ID")
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

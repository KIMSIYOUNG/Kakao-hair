package com.example.kakaohair.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.member.domain.MemberFixture;
import com.example.kakaohair.user.member.web.MemberResponse;
import com.example.kakaohair.user.member.web.MemberUpdateRequest;

public class MemberAcceptanceTest extends AcceptanceTest {
    private static final String RESOURCE = "/api/members";

    /*
    Scenario : 회원을 관리한다.
        when : 회원을 만든다.
        then : 회원이 생성된다.

        when : 회원 전체 정보를 조회한다.
        then : 회원 전체 정보가 조회되었다.

        when : 회원을 변경했을 때
        then : 회원 정보를 읽어온다.
        then : 회원 정보가 변경 정보와 일치한다.

        when : 회원을 삭제한다.
        then: 기존 회원이 삭제되었다.
     */
    @Test
    void manageMember() {
        TokenResponse token = createMemberAndRetrieveToken();
        fetchMember(token);
        fetchDelete(token);
        fetchNotExistMember(token);
    }

    private void fetchNotExistMember(TokenResponse token) {
        given()
            .auth().oauth2(token.getAccessToken())
            .when()
            .get(RESOURCE)
            .then()
            .log().all()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void fetchDelete(TokenResponse token) {
        given()
            .auth().oauth2(token.getAccessToken())
            .when()
            .delete(RESOURCE)
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private String fetchUpdate(TokenResponse token) {
        MemberUpdateRequest request = MemberFixture.updateDto();

        return given()
            .auth().oauth2(token.getAccessToken())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(RESOURCE)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .header("Location");
    }

    private MemberResponse fetchMember(TokenResponse token) {
        return given()
            .auth().oauth2(token.getAccessToken())
            .when()
            .get(RESOURCE)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(MemberResponse.class);
    }
}

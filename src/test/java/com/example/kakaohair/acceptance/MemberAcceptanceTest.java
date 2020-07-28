package com.example.kakaohair.acceptance;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import com.example.kakaohair.member.application.MemberUpdateRequest;
import com.example.kakaohair.member.domain.MemberFixture;
import com.example.kakaohair.member.domain.MemberState;
import com.example.kakaohair.member.web.MemberCreateRequest;
import com.example.kakaohair.member.web.MemberResponse;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    private static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

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
    @Rollback(false)
    void manageMember() {
        String resource = fetchCreateMember();
        final MemberResponse findMember = fetchMember(resource);
        String updatedResource = fetchUpdate(resource);
        final MemberResponse updatedMember = fetchMember(updatedResource);
        assertThat(findMember).isEqualToIgnoringGivenFields(updatedMember, "name");
        fetchDelete(resource);
        final MemberResponse deletedMember = fetchMember(updatedResource);
        assertThat(deletedMember.getMemberState()).isEqualTo(MemberState.DELETED);
    }

    private void fetchDelete(final String resource) {
        given()
            .when()
            .delete(resource)
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private String fetchUpdate(final String resource) {
        MemberUpdateRequest request = MemberFixture.updateDto();

        return given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(resource)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .header("Location");
    }

    private MemberResponse fetchMember(final String resource) {
        return given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(resource)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(MemberResponse.class);
    }

    private String fetchCreateMember() {
        MemberCreateRequest request = MemberFixture.createDto();

        return given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/members")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .header("Location");
    }
}

package com.example.kakaohair.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.kakaohair.member.domain.Member;
import com.example.kakaohair.member.domain.MemberFixture;
import com.example.kakaohair.member.web.MemberCreateRequest;
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
    void manageMember() {
        String resource = createMember();
        Member findMember = fetchMember(resource);
    }

    private Member fetchMember(final String resource) {
        return given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(resource)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(Member.class);
    }

    private String createMember() {
        MemberCreateRequest request = MemberFixture.createDto();
        return fetchCreateMember(request);
    }

    private String fetchCreateMember(final MemberCreateRequest request) {
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

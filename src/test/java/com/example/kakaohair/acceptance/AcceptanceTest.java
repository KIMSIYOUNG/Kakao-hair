package com.example.kakaohair.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.kakaohair.common.infra.kakao.TokenResponse;
import com.example.kakaohair.user.member.domain.MemberFixture;
import com.example.kakaohair.user.member.web.MemberCreateRequest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
    @LocalServerPort
    protected int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    protected TokenResponse createMemberAndRetrieveToken() {
        final MemberCreateRequest request = MemberFixture.createDto();

        return given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(request)
            .post("/api/members")
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(TokenResponse.class);
    }
}

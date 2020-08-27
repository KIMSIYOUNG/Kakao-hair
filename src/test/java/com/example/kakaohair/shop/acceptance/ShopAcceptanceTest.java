package com.example.kakaohair.shop.acceptance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.kakaohair.acceptance.AcceptanceTest;
import com.example.kakaohair.common.infra.kakao.TokenResponse;

class ShopAcceptanceTest extends AcceptanceTest {

    /**
     * given : 회원이 등록 되어 있다.
     * when : 회원이 가게 등록 요청을 한다.
     * then : 가게가 등록 되었다.
     *
     * when : 가게를 조회한다.
     * then : 가게가 조회된다.
     *
     * when : 가게의 정보를 수정한다.
     * when : 가게를 조회한다.
     * then : 수정된 정보가 반환된다.
     *
     * when : 가게를 삭제한다.
     * when : 가게를 조회한다.
     * then : 가게가 존재하지 않는다.
     */
    @Test
    void manageShop() {
        TokenResponse token = createMemberAndRetrieveToken();



    }
}
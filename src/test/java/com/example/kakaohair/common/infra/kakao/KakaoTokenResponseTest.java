package com.example.kakaohair.common.infra.kakao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class KakaoTokenResponseTest {
    public static final String KAKAO_REAL_TOKEN_RESPONSE = ""
        + "{\n"
        + "    \"token_type\":\"bearer\",\n"
        + "    \"access_token\":\"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\",\n"
        + "    \"expires_in\":43199,\n"
        + "    \"refresh_token\":\"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\",\n"
        + "    \"refresh_token_expires_in\":25184000,\n"
        + "    \"scope\":\"account_email profile\"\n"
        + "}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("카카오에서 반환한 Json을 Deserialize 할 수 있다.")
    @Test
    void kakaoTokenResponseDtoFormat() throws JsonProcessingException {
        assertThat(objectMapper.readValue(KAKAO_REAL_TOKEN_RESPONSE, KakaoTokenResponse.class)).isNotNull();
    }
}
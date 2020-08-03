package com.example.kakaohair.common.infra.kakao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class KakaoUserResponseTest {
    public static final String KAKAO_REAL_USER_RESPONSE = "{\n"
        + "    \"id\": 1,\n"
        + "    \"properties\": {\n"
        + "        \"nickname\": \"nickname\",\n"
        + "        \"profile_image\": \"https://14floorguys.com\",\n"
        + "        \"thumbnail_image\": \"https://14floorguys.com\"\n"
        + "    },\n"
        + "    \"kakao_account\": {\n"
        + "        \"profile_needs_agreement\": true,\n"
        + "        \"profile\": {\n"
        + "            \"nickname\": \"nickname\",\n"
        + "            \"thumbnail_image_url\": \"https://14floorguys.com\",\n"
        + "            \"profile_image_url\": \"https://14floorguys.com\"\n"
        + "        },\n"
        + "        \"has_email\": true,\n"
        + "        \"email_needs_agreement\": true,\n"
        + "        \"is_email_valid\": false,\n"
        + "        \"is_email_verified\": false,\n"
        + "        \"email\": \"jj@woowa.com\",\n"
        + "        \"has_age_range\": true,\n"
        + "        \"age_range_needs_agreement\": true,\n"
        + "        \"age_range\": \"20~29\",\n"
        + "        \"has_birthday\": true,\n"
        + "        \"birthday_needs_agreement\": true,\n"
        + "        \"birthday\": \"0429\",\n"
        + "        \"birthday_type\": \"SOLAR\",\n"
        + "        \"has_gender\": true,\n"
        + "        \"gender_needs_agreement\": true\n"
        + "    }\n"
        + "}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("카카오에서 응답한 Json을 Deserialize 할 수 있다.")
    @Test
    void kakaoUserResponseDtoFormat() throws JsonProcessingException {
        assertThat(objectMapper.readValue(KAKAO_REAL_USER_RESPONSE, KakaoUserResponse.class)).isNotNull();
    }
}
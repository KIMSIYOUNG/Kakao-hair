package com.example.kakaohair.user.designer.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kakaohair.user.designer.Designer;

public interface DesignerRepository extends JpaRepository<Designer, Long> {
    @Query("select d from Designer d where d.memberInfo.socialId = :socialId")
    Optional<Designer> findBySocialId(@Param("socialId") String id);
}

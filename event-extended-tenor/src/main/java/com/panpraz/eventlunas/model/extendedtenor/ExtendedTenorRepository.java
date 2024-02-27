package com.panpraz.eventlunas.model.extendedtenor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExtendedTenorRepository extends JpaRepository<ExtendedTenor,Long> {
    @Query(value = "SELECT LogId, EmailAdr, Title, AccountName, OldAngsuran, NewAngsuran, NewAngsuran2, SendSts, SentDate, CreatedDate, SubjectEmail, BodyEmail \n" +
            "FROM ExtendedTenor_Log with(nolock) WHERE Sendsts = 0 AND EmailAdr <> '' ORDER BY LogID asc", nativeQuery = true)
    List<ExtendedTenor> getExtendedTenorEmail();
}

package com.panpraz.informasisertifikasidanedukasicp.model.extendedtenorlist;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ExtendedTenorRepository extends CrudRepository<ExtendedTenorList,String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE ExtendedTenor_Log SET SendSts = '1', SentDate = GETDATE() where LogID = :LogID")
    void updateStatus(@Param("LogID") Integer LogID);

}

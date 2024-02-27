package com.panpraz.informasisertifikasidanedukasicp.dto.request;

import lombok.*;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedTenor {
    private @Id Integer LogId;
    private String EmailAdr;
    private String Title;
    private String AccountName;
    private Integer OldAngsuran;
    private Integer NewAngsuran;
    private Integer NewAngsuran2;
    private String SendSts;
    private String SubjectEmail;
    private String BodyEmail;
    private Date SentDate;
    private Date CreatedDate;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        ExtendedTenor that = (ExtendedTenor) o;
//        return LogId != null && Objects.equals(LogId, that.LogId);
//    }

    public static ExtendedTenor mapFromSolaceArray(Object[] solaceArray) {
        ExtendedTenor extendedTenor = new ExtendedTenor();
        extendedTenor.setLogId((Integer) solaceArray[0]);
        extendedTenor.setEmailAdr((String) solaceArray[1]);
        extendedTenor.setTitle((String) solaceArray[2]);
        extendedTenor.setAccountName((String) solaceArray[3]);
        extendedTenor.setOldAngsuran((Integer) solaceArray[4]);
        extendedTenor.setNewAngsuran2((Integer) solaceArray[5]);
        extendedTenor.setSendSts((String) solaceArray[6]);
        extendedTenor.setSentDate((solaceArray[7] instanceof Date) ? (Date) solaceArray[7] : null);
        extendedTenor.setCreatedDate(new Date((Long) solaceArray[8]));

        return extendedTenor;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

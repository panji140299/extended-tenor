package com.panpraz.eventlunas.model.extendedtenor;

import com.panpraz.eventlunas.compositekey.ExtendedTenorCompositeKey;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ExtendedTenorCompositeKey.class)
public class ExtendedTenor {
    private @Id Integer LogId;
    private String EmailAdr;
    private String Title;
    private String AccountName;
    private Integer OldAngsuran;
    private Integer NewAngsuran;
    private Integer NewAngsuran2;
    private String SendSts;
    private String BodyEmail;
    private String SubjectEmail;
    private Date SentDate;
    private Date CreatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExtendedTenor that = (ExtendedTenor) o;
        return LogId != null && Objects.equals(LogId, that.LogId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

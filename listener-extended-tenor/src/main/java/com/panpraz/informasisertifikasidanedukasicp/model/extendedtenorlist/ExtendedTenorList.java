package com.panpraz.informasisertifikasidanedukasicp.model.extendedtenorlist;

//import com.pranpraz.informasisertifikasidanedukasicp.compositekey.DataKonsumenCompositeKey;
import com.panpraz.informasisertifikasidanedukasicp.compositekey.DataKonsumenExtendedCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DataKonsumenExtendedCompositeKey.class)

public class ExtendedTenorList {
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
}

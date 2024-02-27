package com.panpraz.informasisertifikasidanedukasicp.request.scheme;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String emailBody;
    private String emailSubject;
    private String emailCc;
    private String emailBcc;
    private Integer emailIdDataSources;
    private String emailAttachment;
    private String emailAttachmentName;
}

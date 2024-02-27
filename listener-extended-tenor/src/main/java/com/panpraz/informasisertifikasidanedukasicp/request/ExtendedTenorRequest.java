package com.panpraz.informasisertifikasidanedukasicp.request;

import com.panpraz.informasisertifikasidanedukasicp.request.scheme.Notification;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedTenorRequest {
    private String customerName;
    private String phoneNumber;
    private String sources;
    private String email;
    private String rule;
    private List<Notification> notificationList;
}

package com.panpraz.informasisertifikasidanedukasicp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DebeziumResponse {
    private String receiveNo;
    private String objCarId;
    private String status;

    @Override
    public String toString() {
        return "DebeziumResponse{" +
                "receiveNo='" + receiveNo + '\'' +
                ", objCarId='" + objCarId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

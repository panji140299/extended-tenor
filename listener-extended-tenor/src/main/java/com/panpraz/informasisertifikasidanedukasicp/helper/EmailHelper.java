package com.panpraz.informasisertifikasidanedukasicp.helper;

import com.panpraz.informasisertifikasidanedukasicp.configuration.EngineNotifConfiguration;
//import com.pranpraz.informasisertifikasidanedukasicp.model.datakonsumen.DataKonsumen;
//import com.pranpraz.informasisertifikasidanedukasicp.model.datakonsumen.DataKonsumenRepository;
import com.panpraz.informasisertifikasidanedukasicp.model.extendedtenorlist.ExtendedTenorRepository;
import com.panpraz.informasisertifikasidanedukasicp.request.ExtendedTenorRequest;
import com.panpraz.informasisertifikasidanedukasicp.response.EngineNotifResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j @Service
public class EmailHelper {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EngineNotifConfiguration engineNotifConfiguration;

    @Autowired
    ExtendedTenorRepository extendedTenorRepository;


    public EngineNotifResponse callEngineNotif(ExtendedTenorRequest req, String LogID) {
        log.info("Sending ");

        EngineNotifResponse res = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ExtendedTenorRequest> httpEntity = new HttpEntity<>(req, headers);
            log.info("With Data : {}",httpEntity);
            res = restTemplate.postForObject(engineNotifConfiguration.getUrl(), httpEntity,EngineNotifResponse.class);
            log.info(res.toString());
        } catch (Exception e) {
            log.info("Error Sending Data to Engine Notification: {}",e.getMessage());
        }

        if (res.getResponseCode().equals("00")) {
            extendedTenorRepository.updateStatus(Integer.valueOf(LogID));
            log.info("Update Status Selesai");
        }

        return res;
    }

}

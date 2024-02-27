package com.panpraz.informasisertifikasidanedukasicp.service;

import com.panpraz.informasisertifikasidanedukasicp.configuration.EngineNotifConfiguration;
import com.panpraz.informasisertifikasidanedukasicp.dto.request.ExtendedTenor;
import com.panpraz.informasisertifikasidanedukasicp.helper.EmailHelper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.panpraz.informasisertifikasidanedukasicp.request.ExtendedTenorRequest;
import com.panpraz.informasisertifikasidanedukasicp.request.scheme.Email;
import com.panpraz.informasisertifikasidanedukasicp.request.scheme.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExtendedTenorService {
    private static final Logger logger = LoggerFactory.getLogger(ExtendedTenorService.class);
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private EmailHelper emailHelper;
    @Autowired
    EngineNotifConfiguration engineNotifConfiguration;

    public void processExtendedTenor(String object) {
        try {
            logger.info("Processing Extended Tenor Request");
            ExtendedTenor req = mapper.readValue(object, ExtendedTenor.class);
            processEngineNotif(req);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("Error : {}",e.getMessage());
        }
    }

    public void processEngineNotif(ExtendedTenor extendedTenor) throws ParseException {
        logger.info("Preparing Email Engine Notif");
        ExtendedTenorRequest reqNotif = engineNotifRequestBuilder(extendedTenor);
//        logger.info(reqNotif.toString());
        emailHelper.callEngineNotif(reqNotif, String.valueOf(extendedTenor.getLogId()));
    }



    public ExtendedTenorRequest engineNotifRequestBuilder(ExtendedTenor extendedTenor) throws ParseException {
        //Due Date
        SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date inputDate = originalFormat.parse(String.valueOf(extendedTenor.getCreatedDate()));

        // Add 7 days to the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        Date resultDate = calendar.getTime();

        // Format the result date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dueDate = dateFormat.format(resultDate);

        //Format Rupiah

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String oldAngsuran = numberFormat.format(extendedTenor.getOldAngsuran());
        String newAngsuran = numberFormat.format(extendedTenor.getNewAngsuran());
        String newAngsuran2 = numberFormat.format(extendedTenor.getNewAngsuran2());
        String subject = extendedTenor.getSubjectEmail();
        String body = extendedTenor.getBodyEmail();

//        String subject = "Cicilanmu Berkurang Hingga 70% Trying ";
        String bodyTemplate = "<body> <p> Dear %s %s, <br><br> Manfaatkan program Angkringan (ANGsuran maKin RINGAN) dari BCA Finance yang akan membuat cicilan mobilmu menjadi lebih ringan. <br><br>Terdapat 2 pilihan yang bisa kamu ambil: <br><br>Angkringan 1<br>Cicilanmu sekarang  Rp %s/bulan<br>menjadi  Rp %s/bulan <br><br>Angkringan 2<br>Cicilanmu sekarang Rp %s/bulan<br>menjadi Rp %s/bulan <br><br>Segera ajukan pilihanmu sebelum tanggal %s dengan klik link berikut: <br> https://wa.me/6287740600111 <br><br>Atau hubungi Sdri. Ardina di nomor 0877 4060 0111 <br><br>Penawaran spesial ini hanya khusus untuk kamu loh! <br><br>Have a nice day. </p> <br> <br> </body>";

// Replace placeholders with actual values
//        String body = String.format(
//                bodyTemplate,
//                extendedTenor.getTitle(),
//                extendedTenor.getAccountName(),
//                oldAngsuran,
//                newAngsuran,
//                oldAngsuran,
//                newAngsuran2,
//                dueDate
//
//        );


        String to = extendedTenor.getEmailAdr();
        String cc = "";
        String bcc = "";
        String sourceApp = "Extended-tenor";
        Integer sourceId = engineNotifConfiguration.getSource();

        Email email = new Email(body,subject,cc,bcc,sourceId,null,null);
        Notification notification = new Notification("EMAIL",email);
        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        return new ExtendedTenorRequest(
                extendedTenor.getAccountName(),"",
                sourceApp,to,"OFFERING",notificationList);
    }

    private static int parseAmount(String amountString) {
        // Remove commas and parse the integer
        return Integer.parseInt(amountString.replaceAll(",", "."));
    }

    public String getFileName() {
        return "extended-tenor.html";
    }
}

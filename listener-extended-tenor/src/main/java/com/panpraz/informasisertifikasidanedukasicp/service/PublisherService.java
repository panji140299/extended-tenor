package com.panpraz.informasisertifikasidanedukasicp.service;

import com.solacesystems.jcsmp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Value("${solace.jms.host}")
    private String host;

    @Value("${solace.jms.msgVpn}")
    private String msgVpn;

    @Value("${solace.jms.clientUsername}")
    private String username;

    @Value("${solace.jms.clientPassword}")
    private String password;


    private static final Logger logger = LoggerFactory.getLogger(PublisherService.class);

    /**
     * Sending Message To Solace, this is general function
     * @param data
     * @param strTopic
     * @throws JCSMPException
     */
    public void sendingMessagetoSolace(String data, String strTopic) throws JCSMPException{
        // Initializing Config
        final JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, host.replace("smf://", ""));     // host:port
        properties.setProperty(JCSMPProperties.USERNAME, username); // client-username
        properties.setProperty(JCSMPProperties.VPN_NAME,  msgVpn); // message-vpn
        properties.setProperty(JCSMPProperties.PASSWORD, password); // client-password

        final JCSMPSession session =  JCSMPFactory.onlyInstance().createSession(properties);

        session.connect();

        try {
            final Topic topic = JCSMPFactory.onlyInstance().createTopic(strTopic);

            XMLMessageProducer prod = session.getMessageProducer(new JCSMPStreamingPublishCorrelatingEventHandler() {

                @Override
                public void handleErrorEx(Object arg0, JCSMPException arg1, long arg2) {
                    logger.info("Producer received error for msg: {}@{} - {}", arg0, arg1, arg2);
                    
                }

                @Override
                public void responseReceivedEx(Object arg0) {
                    logger.info("Producer received response for msg: {}",arg0);
                }
                
            });
    
            TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
            msg.setText(data);
            prod.send(msg,topic);
            session.closeSession();
            logger.info("Message has been sent to solace");
        } catch (Exception e) {
            session.closeSession();
            logger.error(e.toString());
        }
        
    }

}

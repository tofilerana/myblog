//package RanaEnterprices.myblog8.service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TwilioService {
//
//    @Value("${twilio.accountSid}")
//    private String accountSid;
//
//    @Value("${twilio.authToken}")
//    private String authToken;
//
//    @Value("${twilio.phoneNumber}")
//    private String twilioPhoneNumber;
//
//    public void sendSms(String toPhoneNumber, String messageText) {
//        Twilio.init(accountSid, authToken);
//
//        Message message = Message.creator(
//                        new PhoneNumber(toPhoneNumber), // Recipient's phone number
//                        new PhoneNumber(twilioPhoneNumber), // Twilio phone number
//                        messageText)
//                .create();
//
//        System.out.println("SMS sent with SID: " + message.getSid());
//    }
//}


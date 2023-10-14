package RanaEnterprices.myblog8.controller;
//
//import RanaEnterprices.myblog8.payload.SmsDto;
//import RanaEnterprices.myblog8.service.TwilioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/sms")
//@Validated // Enable validation for this controller
//public class SmsController {
//
//    private final TwilioService twilioService;
//
//    @Autowired
//    public SmsController(TwilioService twilioService) {
//        this.twilioService = twilioService;
//    }
//
//    //      http://localhost:8080/send
//    @PostMapping("/send")
//    public void sendSms(@Valid @RequestBody SmsDto smsDto) {
//        twilioService.sendSms(smsDto.getToPhoneNumber(), smsDto.getMessageText());
//    }
//}
//


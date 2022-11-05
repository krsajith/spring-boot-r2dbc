package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Applicant {
    
    private Integer applicantId;

    
    private Integer candidateId;

    
   
    private String jobRequistionNumber;

    
   
    private String firstName;

    
   
    private String lastName;

    
   
    private String email;


    private Integer atsStatusId;

    
   
    private String applicationDate;

    
   
    private String statusDate;

    
    private Integer applicantOwner;

    
   
    private String dateOfJoining;

    
    private Integer isEuropeanNationalist;

    
    private Integer isSubscribeEmail;

    
    private Integer isSubscribeSms;

    
    private Integer isConsentGiven;

    
    private Integer emailUnsubscribe;

    
    private Integer noticePeriod;

    
   
    private String dob;

    
   
    private String resumeSource;

    
   
    private String resumeSubSource;

    
   
    private String offerSentDate;
    private String hrStatusDate;

    
    private Integer hiringManagerId;

    
    private Integer createdBy;

    
   
    private String createdDate;

    
    private Integer updatedBy;

    
   
    private String updatedDate;

    
   
    private String company;

    
   
    private String createdRole;

    
   
    private String atsStatusid;

    
   
    private String candidateid;

    
   
    private String hiringManagerid;

    
   
    private String isSubscribesms;

    
   
    private String status;

    
   
    private String address;

    
   
    private String isAddressConfirmed;

    
    private Integer interviewRound;

    
    private Integer interviewStatusId;

    
   
    private String isEmailUnsubscribe;

    
   
    private String eventName;
}
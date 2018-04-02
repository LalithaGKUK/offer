package offer;

import offer.entities.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class Schedule {
    @Autowired
    private OfferRepository offerRepository;

    private Date getCurrentDate(){
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        Date date = Date.from(utc.toInstant());
        return date;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upadteStatusToActive(){
        int noOfRecord = offerRepository.updateStatusToActive(getCurrentDate());
        System.out.println("Update status to ACTIVE: Number of records updated"+ noOfRecord);
    }

    @Scheduled(fixedDelay = 65000)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateSatusToExpired(){
        System.out.println(getCurrentDate());
        int noOfRecords = offerRepository.updateStatusToExpired(getCurrentDate());
        System.out.println("Updated status to EXPIRED: Number of records updated"+ noOfRecords);
    }
}


package offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import offer.data.RequestOffer;
import offer.data.ResponseOffer;
import offer.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferUtils {
        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private MerchantRepository merchantRepository;
        @Autowired
        private OfferRepository offerRepository;


        private Date getCurrentDate() {
            ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
            Date date = Date.from(utc.toInstant());
            return date;
        }

        public boolean addOffer(String offerJson){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                RequestOffer data = objectMapper.readValue(offerJson, RequestOffer.class);

                Product product = new Product(data.getProduct());
                product.setCreatedAt(getCurrentDate());
                product.setUpdatedAt(getCurrentDate());

                Product savedProduct = productRepository.save(product);

                List<Merchant> merchant = merchantRepository.findMerchantByName(data.getMerchant().getName());
                Long merchantId = -1L;
                if(merchant.size() == 0) {
                    Merchant m = new Merchant();
                    m.setName(data.getMerchant().getName());
                    m.setActive(true);
                    m.setCreatedAt(getCurrentDate());
                    m.setUpdatedAt(getCurrentDate());
                    merchantId = merchantRepository.save(m).getId();
                }
                else{
                    merchantId = merchant.get(0).getId();
                }
                Offer offer = new Offer();
                offer.setDescription((data.getOffer().getDescription()));
                offer.setProductId(savedProduct.getId());
                offer.setMerchantId(merchantId);
                offer.setStatus(JobStatus.CREATED.toString());
                offer.setSellingPrice(data.getOffer().getSellingPrice());
                offer.setCreatedAt(getCurrentDate());
                offer.setUpdatedAt(getCurrentDate());
                offer.setStartDate(data.getOffer().getStartDate());
                offer.setExpiryDate(data.getOffer().getExpiryDate());

                offerRepository.save(offer);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        public  List<ResponseOffer> getActiveOffers(){
            Iterable<Offer> offers = offerRepository.findOfferByStatus(JobStatus.ACTIVE.toString());
            Date currentDate = getCurrentDate();
            List<Offer> createdOffers = offerRepository.findOfferByStatus("CREATED")
                                                       .stream()
                                                       .filter(o -> currentDate.compareTo(o.getStartDate()) > 0)
                                                       .filter(o -> currentDate.compareTo(o.getExpiryDate()) < 0)
                                                       .collect(Collectors.toList());

            List<ResponseOffer> offerList = new ArrayList<ResponseOffer>();
            for(Offer o: offers){
                ResponseOffer offer = new ResponseOffer();
                offer.setId(o.getId());
                offer.setDescription(o.getDescription());
                offer.setSellingPrice(o.getSellingPrice());
                offer.setStartDate(o.getStartDate());
                offer.setExpiryDate(o.getExpiryDate());
                offerList.add(offer);
            }

            for(Offer o:createdOffers){
                ResponseOffer offer = new ResponseOffer();
                offer.setId(o.getId());
                offer.setDescription(o.getDescription());
                offer.setDescription(o.getDescription());
                offer.setSellingPrice(o.getSellingPrice());
                offer.setStartDate(o.getStartDate());
                offer.setExpiryDate(o.getExpiryDate());
                offerList.add(offer);
            }
            return offerList;
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public boolean cancelOfferById(int id){
            int n = offerRepository.updateStatusToCancelled(id);
            return (n != 0);
        }
}

enum JobStatus{
    CREATED,
    ACTIVE,
}

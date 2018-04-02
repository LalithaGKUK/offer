package offer;

import offer.data.ResponseOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController {
    @Autowired
    private OfferUtils offerUtils ;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, name = "/add/offers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addOffers(@RequestBody String offerJson) throws IOException {
        try{
        offerUtils.addOffer(offerJson);
    }
    catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return "Added Offer Successfully";
    }

    @RequestMapping(value = "/offer")
    public @ResponseBody Iterable<ResponseOffer> getAllOffers() {
        return offerUtils.getActiveOffers();
    }

    @RequestMapping(value = "offer/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String cancelOffer(@PathVariable("id") int id){
        boolean isCancelled = offerUtils.cancelOfferById(id);
        if(isCancelled) {
            return "Cancelled Ofer:" + id;
        }
        throw new IllegalArgumentException("Cannnot cancel offer id:" + id);
    }
}

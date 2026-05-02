package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.Offer;

public class OfferDto {
    private long id;
    private String description;
    private boolean active;

    public OfferDto(){
    }

    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.description = offer.getDescription();
        this.active = offer.getActive();
    }
    public Offer toOffer() {
        return new Offer(this.id, this.description, this.active);
    }
}

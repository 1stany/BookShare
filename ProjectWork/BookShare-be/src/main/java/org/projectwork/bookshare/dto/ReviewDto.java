package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.Review;

public class ReviewDto {
    private long id;
    private String title;
    private String content;
    private int rating;

    public ReviewDto(){
    }

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
    }

    public Review toReview(){
        return new Review(this.id, this.title, this.content, this.rating);
    }
}

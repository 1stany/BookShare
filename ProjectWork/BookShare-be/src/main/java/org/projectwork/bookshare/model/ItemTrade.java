package org.projectwork.bookshare.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "object_trade")
public class ItemTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "request_date")
    private LocalDate requestDate;
    private boolean accepted;
    @Column (name = "exchange_date")
    private LocalDate exchangeDate;
    @OneToOne
    @JoinColumn (name = "requested_object_id")
    private Item requestedItem;         //oggetto che lo user vuole ricevere
    @ManyToOne
    @JoinColumn (name = "requesting_user_id")
    private AppUser requestingUser;
    @OneToOne
    @JoinColumn (name = "exchanged_object_id")
    private Item exchangedItem;         //oggetto dello user che si desidera scambiare
    @ManyToOne
    @JoinColumn (name = "home_user_id")
    private AppUser homeUser;
    @OneToMany (mappedBy = "completedItemTrade")
    private List<Review> reviews = new ArrayList<>();

    public ItemTrade() {}

    public ItemTrade(LocalDate requestDate, boolean accepted, LocalDate exchangeDate) {
        this.requestDate = requestDate;
        this.accepted = accepted;
        this.exchangeDate = exchangeDate;
    }

    public ItemTrade(LocalDate requestDate, boolean accepted, LocalDate exchangeDate, Item requestedItem, AppUser requestingUser, Item exchangedItem, AppUser homeUser) {
        this.requestDate = requestDate;
        this.accepted = accepted;
        this.exchangeDate = exchangeDate;
        this.requestedItem = requestedItem;
        this.requestingUser = requestingUser;
        this.exchangedItem = exchangedItem;
        this.homeUser = homeUser;
    }


    public Item getRequestedItem() {
        return requestedItem;
    }

    public Item getExchangedItem() {
        return exchangedItem;
    }

    public long getId() {
        return id;
    }

    public LocalDate getRequestedData() {
        return requestDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public AppUser getRequestingUser() {
        return requestingUser;
    }

    public AppUser getHomeUser() {
        return homeUser;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public void setRequestedItem(Item requestedItem) {
        this.requestedItem = requestedItem;
    }

    public void setRequestingUser(AppUser requestingUser) {
        this.requestingUser = requestingUser;
    }

    public void setExchangedItem(Item exchangedItem) {
        this.exchangedItem = exchangedItem;
    }

    public void setHomeUser(AppUser homeUser) {
        this.homeUser = homeUser;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }
}

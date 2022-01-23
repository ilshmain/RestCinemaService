package com.rest.cinemaroomrestservice;

public class Stats {
    private Integer current_income;
    private Integer number_of_available_seats;
    private Integer number_of_purchased_tickets;

    public Stats() {
        this.current_income = 0;
        this.number_of_available_seats = 81;
        this.number_of_purchased_tickets = 0;
    }

    public Integer getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(Integer current_income) {
        this.current_income = current_income;
    }

    public Integer getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(Integer number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public Integer getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(Integer number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }
}

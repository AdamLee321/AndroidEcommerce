package com.example.adaml.androidecommerce.Model;

import java.util.List;

/**
 * Created by adaml on 26/01/2018.
 */

public class SubmitOrder {
    private String phone, name, address, total, status;

    //list of purchased items
    private List<Order> items;

    public SubmitOrder() {
    }

    public SubmitOrder(String phone, String name, String address, String total, List<Order> items) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.items = items;
        this.status = "0";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }
}

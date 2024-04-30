package com.bookstore.search;

import lombok.Getter;

@Getter
public class CustomerSearchRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
}

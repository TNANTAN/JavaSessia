package com.idk.fxs;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String surName;
    private String login;
    private String password;
    private String phoneNumber;
    private String country;
    private String gender;
    private String status;
    private String role;
    private ArrayList<User> Friends;
    private ArrayList<User> BlackListUser;

    public User() {
    }

    public User(String name, String surname, String login, String password, String phoneNumber, String country, String gender) {
        //this.id = id;
        this.name = name;
        this.surName = surname;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.gender = gender;
//        Friends = friends;
//        BlackListUser = blackListUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int id, String name, String surname, String login, String password, String phoneNumber, String country, String gender, String role, String status) {
        this.id = id;
        this.name = name;
        this.surName = surname;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }

    public ArrayList<User> getFriends() {
        return Friends;
    }

    public void setFriends(ArrayList<User> friends) {
        Friends = friends;
    }

    public ArrayList<User> getBlackListUser() {
        return BlackListUser;
    }

    public void setBlackListUser(ArrayList<User> blackListUser) {
        BlackListUser = blackListUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }


    public void setSurName(String surname) {
        this.surName = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "id: " + id +
                ", name: " + name + '\'' +
                ", surname: " + surName + '\'' +
                ", phoneNumber: " + phoneNumber + '\'' +
                ", country: " + country + '\'' +
                ", gender: " + gender + '\'' +
                ", status: " + status+'\'' +
                '}';
    }
}

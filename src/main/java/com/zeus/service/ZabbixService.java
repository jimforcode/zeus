package com.zeus.service;

public interface ZabbixService {

    boolean isLogin(String userName, String passWord);

    Object hostGet(String[] host);
}

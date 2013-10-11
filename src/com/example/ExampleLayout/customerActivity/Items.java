package com.example.ExampleLayout.customerActivity;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/10/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Items
{
    String phoneNumber;
    String name;

    public Items(String phoneNumber, String name)
    {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

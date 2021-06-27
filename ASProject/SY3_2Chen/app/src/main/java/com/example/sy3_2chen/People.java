package com.example.sy3_2chen;

public class People {
    private String name;
    private String phone;
    private String email;
    private String shortcall;

    public People() {}

    public People(String name){
        this.name = name;
    }
    public People(String name,String phone,String email,String shortcall){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.shortcall = shortcall;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getShortcall() { return shortcall; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setShortcall(String shortcall) { this.shortcall = shortcall; }
}

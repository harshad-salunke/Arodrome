package com.example.aerodromeApp.Model;

public class UserObject {


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }



    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }




   public UserObject(){

    }

    public UserObject(String discription, String user_id, String view, String user_name, String media_url) {
        this.discription = discription;
        this.user_id = user_id;
        this.view = view;
        this.user_name = user_name;
        this.media_url = media_url;
    }

      String discription;
    String user_id;
  String  view;
  String user_name;
 String  media_url;
}

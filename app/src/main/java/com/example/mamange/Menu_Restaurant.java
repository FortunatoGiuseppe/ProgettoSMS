package com.example.mamange;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu_Restaurant  implements Parcelable {
    private String name;
  //  private int totalInCart;
    private String url;

  /*  public int getTotalInCart() {
        return totalInCart;
    }*/
/*
    public void setTotalInCart(int totalInCart) {
        this.totalInCart = totalInCart;
    }*/

    protected Menu_Restaurant(Parcel in) {
        name = in.readString();
        url = in.readString();
      //  totalInCart = in.readInt();
    }

    public static final Creator<Menu_Restaurant> CREATOR = new Creator<Menu_Restaurant>() {
        @Override
        public Menu_Restaurant createFromParcel(Parcel in) {
            return new Menu_Restaurant(in);
        }

        @Override
        public Menu_Restaurant[] newArray(int size) {
            return new Menu_Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}

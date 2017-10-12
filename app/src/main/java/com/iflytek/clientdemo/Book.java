package com.iflytek.clientdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 2017/09/05.
 */

public class Book implements Parcelable{
    String name;
    int price;

    public Book() {
    }

    protected Book(Parcel in) {
//        name = in.readString();
//        price = in.readInt();
        readFromParcel(in);

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
    }

    public void readFromParcel(Parcel in) {
      name=in.readString();
        price=in.readInt();
    }


}

package com.example.phonebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    int image;
    String name;
    String description;
    String universe;
    String favorites;
    String email;
    String phonenumber;

    Contact(int i, String n, String d, String u, String f, String e, String p)
    {
        image = i;
        name = n;
        description = d;
        universe = u;
        favorites = f;
        email = e;
        phonenumber = p;
    }
    Contact(Parcel in)
    {
        image = in.readInt();
        name = in.readString();
        description = in.readString();
        universe = in.readString();
        favorites = in.readString();
        email = in.readString();
        phonenumber = in.readString();
    }

    public int getImage()
    {
        return image;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public String getUniverse()
    {
        return universe;
    }
    public String getSaying()
    {
        return favorites;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhonenumber()
    {
        return phonenumber;
    }


    public int describeContents()
    {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeInt(image);
        out.writeString(name);
        out.writeString(description);
        out.writeString(universe);
        out.writeString(favorites);
        out.writeString(email);
        out.writeString(phonenumber);
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>()
    {
        public Contact createFromParcel(Parcel in)
        {
            return new Contact(in);
        }
        public Contact[] newArray(int size)
        {
            return new Contact[size];
        }
    };
}

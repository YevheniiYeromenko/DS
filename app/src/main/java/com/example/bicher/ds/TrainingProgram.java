package com.example.bicher.ds;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TrainingProgram implements Parcelable {

    @Getter private String name;
    //@Getter private float raiting;
    @Getter private String imageUrl;
    @Getter private String videoUri;
    @Getter private List<String> programList;

    protected TrainingProgram(Parcel in) {
        name = in.readString();
        //raiting = in.readFloat();
        imageUrl = in.readString();
        videoUri = in.readString();
        programList = in.createStringArrayList();
    }

    public static final Creator<TrainingProgram> CREATOR = new Creator<TrainingProgram>() {
        @Override
        public TrainingProgram createFromParcel(Parcel in) {
            return new TrainingProgram(in);
        }

        @Override
        public TrainingProgram[] newArray(int size) {
            return new TrainingProgram[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        //dest.writeFloat(raiting);
        dest.writeString(imageUrl);
        dest.writeString(videoUri);
        dest.writeStringList(programList);
    }

}

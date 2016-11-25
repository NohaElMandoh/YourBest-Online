package com.example.prof_mohamedatef.yourbestonlinetv;

/**
 * Created by Prof-Mohamed Atef on 11/4/2016.
 */
public class CarsEntity {
    String ImagePath,CarType;

    public CarsEntity(String image_url_string, String title_string) {
        this.ImagePath=image_url_string;
        this.CarType=title_string;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }
}
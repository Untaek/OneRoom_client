package com.untaek.oneroom.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

/**
 * Created by ejdej on 2017-05-15.
 */

public interface RoomService {
    class Room{
        public Room(String address,
                    String building_type,
                    String floor,
                    int size){}
        int id;
        String address;
        String building_type;
        String floor;
        int size;
        float[] coordinate;
        String[] universities;
        boolean parking;
        String infra;
        String options;
        String[] images;
        String[] thumbnails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBuilding_type() {
            return building_type;
        }

        public void setBuilding_type(String building_type) {
            this.building_type = building_type;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public float[] getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(float[] coordinate) {
            this.coordinate = coordinate;
        }

        public String[] getUniversities() {
            return universities;
        }

        public void setUniversities(String[] universities) {
            this.universities = universities;
        }

        public boolean isParking() {
            return parking;
        }

        public void setParking(boolean parking) {
            this.parking = parking;
        }

        public String getInfra() {
            return infra;
        }

        public void setInfra(String infra) {
            this.infra = infra;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        public String[] getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(String[] thumbnails) {
            this.thumbnails = thumbnails;
        }
    }

    class RoomBoard{
        int id;
        int owner;
        int room;
        int cost;
        String title;
        String description;
        String available_date;
        String post_date;
        String expire_date;
        int admin_expenses;
    }

    class DBStatus{
        int code;
    }

    @POST("rooms/register/")
    Call<DBStatus> registerRoom(@Body Room room);
    @POST("rooms/post")
    Call<DBStatus> postRoom(@Body RoomBoard roomBoard);
}

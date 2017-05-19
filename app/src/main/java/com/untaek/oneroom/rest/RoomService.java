package com.untaek.oneroom.rest;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ejdej on 2017-05-15.
 */

public interface RoomService {
    class Room implements Serializable{
        public Room(long user_id,
                    String address,
                    String building_type,
                    String floor,
                    int size){
            this.address = address;
            this.building_type = building_type;
            this.floor = floor;
            this.size = size;
            this.user_id = user_id;
        }
        int id;
        long user_id;
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
        int code;
        boolean posted;

        public int getCode() {
            return code;
        }

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

    class RoomPost {
        public RoomPost(long user_id, long room_id, String contract_type, int cost, int cost_additional, String title, String description, int admin_expenses) {
            this.user_id = user_id;
            this.room_id = room_id;
            this.contract_type = contract_type;
            this.cost = cost;
            this.cost_additional = cost_additional;
            this.title = title;
            this.description = description;
            this.admin_expenses = admin_expenses;
        }

        int id;
        long user_id;
        long room_id;
        int cost;
        int cost_additional;
        String title;
        String description;
        String available_date;
        String post_date;
        String expire_date;
        int admin_expenses;
        String contract_type;
    }

    class DBStatus{
        int code;
    }

    class ResultList{
        public ArrayList<Room> result;
        public int code;
    }

    class RoomDetail implements Serializable{
        int id;
        long user_id;
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
        boolean posted;
        long room_id;
        int cost;
        int cost_additional;
        String title;
        String description;
        String available_date;
        String post_date;
        String expire_date;
        int admin_expenses;
        String contract_type;
        int code;

        public int getId() {
            return id;
        }

        public long getUser_id() {
            return user_id;
        }

        public String getAddress() {
            return address;
        }

        public String getBuilding_type() {
            return building_type;
        }

        public String getFloor() {
            return floor;
        }

        public int getSize() {
            return size;
        }

        public float[] getCoordinate() {
            return coordinate;
        }

        public String[] getUniversities() {
            return universities;
        }

        public boolean isParking() {
            return parking;
        }

        public String getInfra() {
            return infra;
        }

        public String getOptions() {
            return options;
        }

        public String[] getImages() {
            return images;
        }

        public String[] getThumbnails() {
            return thumbnails;
        }

        public boolean isPosted() {
            return posted;
        }

        public long getRoom_id() {
            return room_id;
        }

        public int getCost() {
            return cost;
        }

        public int getCost_additional() {
            return cost_additional;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getAvailable_date() {
            return available_date;
        }

        public String getPost_date() {
            return post_date;
        }

        public String getExpire_date() {
            return expire_date;
        }

        public int getAdmin_expenses() {
            return admin_expenses;
        }

        public String getContract_type() {
            return contract_type;
        }

        public int getCode() {
            return code;
        }
    }

    class RoomDetailList{
        ArrayList<RoomDetail> result;
        int code;
    }

    @POST("rooms/register/")
    Call<DBStatus> registerRoom(@Body Room room);
    @GET("rooms/register/{id}")
    Call<ResultList> getRooms(@Path("id") long id);
    @POST("rooms/post/")
    Call<DBStatus> postRoom(@Body RoomPost roomPost);
    @GET("rooms/post/{id}")
    Call<RoomDetail> getRoomPost(@Path("id") long id);
    @GET("rooms/posts/{id}")
    Call<RoomDetailList> getMyRoomPosts(@Path("id") long id);
    @GET("rooms/posts/")
    Call<RoomDetailList> getRoomPosts();

}

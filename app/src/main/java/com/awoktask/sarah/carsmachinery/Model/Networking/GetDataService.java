package com.awoktask.sarah.carsmachinery.Model.Networking;

import com.awoktask.sarah.carsmachinery.Model.CarsData.Car;
import com.awoktask.sarah.carsmachinery.Model.CarsData.CarsListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sarah on 4/6/19.
 */

public interface GetDataService {

    @GET("/carsonline")
    Call<List<Car>> getCars();
}

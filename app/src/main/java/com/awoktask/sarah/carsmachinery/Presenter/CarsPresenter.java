package com.awoktask.sarah.carsmachinery.Presenter;

import android.util.Log;

import com.awoktask.sarah.carsmachinery.Model.CarsData.CarsListResponse;
import com.awoktask.sarah.carsmachinery.Model.Networking.GetDataService;
import com.awoktask.sarah.carsmachinery.Model.Networking.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sarah on 4/10/19.
 */

public class CarsPresenter implements CarsContract.presenter {
    public CarsContract.view view;
    @Override
    public void loadData() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<CarsListResponse> call = service.getCars();



        call.enqueue(new Callback<CarsListResponse>() {
            @Override
            public void onResponse(Call<CarsListResponse> call, Response<CarsListResponse> response) {


                view.loadDataSuccess(response.body().getCars(), response.body().getTicks());
               /* generateDataList(response.body().getCars()
                );*/


            }

            @Override
            public void onFailure(Call<CarsListResponse> call, Throwable t) {
                view.loadDataFail(t);
                Log.e("error message",t.getMessage());

               /* Toast.makeText(MainActivity.this, "Something went wrong ... please try later ! "+t.getMessage(),Toast.LENGTH_LONG
                ).show();*/


            }



        });
    }
}

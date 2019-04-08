package com.awoktask.sarah.carsmachinery.View;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.awoktask.sarah.carsmachinery.Model.CarsData.Car;
import com.awoktask.sarah.carsmachinery.Model.CarsData.CarsListResponse;
import com.awoktask.sarah.carsmachinery.Model.Networking.GetDataService;
import com.awoktask.sarah.carsmachinery.Model.Networking.RetrofitClientInstance;
import com.awoktask.sarah.carsmachinery.R;
import com.awoktask.sarah.carsmachinery.View.Adapter.CarsAdapter;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView ;
    private CarsAdapter adapter;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<CarsListResponse> call = service.getCars();



        call.enqueue(new Callback<CarsListResponse>() {
            @Override
            public void onResponse(Call<CarsListResponse> call, Response<CarsListResponse> response) {

                progressDialog.dismiss();


                generateDataList(response.body().getCars()
                );


            }

            @Override
            public void onFailure(Call<CarsListResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error message",t.getMessage());

                Toast.makeText(MainActivity.this, "Something went wrong ... please try later ! "+t.getMessage(),Toast.LENGTH_LONG
                ).show();


            }



        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {


                fetchTimelineAsync(0);

            }

        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);


    }


    private void

    generateDataList(List<Car> carsListResponses){

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CarsAdapter(this, carsListResponses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }

    public void fetchTimelineAsync(int page) {

        // Send the network request to fetch the updated data

        // `client` here is an instance of Android Async HTTP

        // getHomeTimeline is an example endpoint.

    }
            public void onFailure(Throwable e) {

                Log.d("DEBUG", "Fetch timeline error: " + e.toString());

            }




}

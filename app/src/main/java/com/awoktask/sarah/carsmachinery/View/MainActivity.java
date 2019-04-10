package com.awoktask.sarah.carsmachinery.View;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.awoktask.sarah.carsmachinery.Presenter.CarsContract;
import com.awoktask.sarah.carsmachinery.Presenter.CarsPresenter;
import com.awoktask.sarah.carsmachinery.Model.CarsData.Car;
import com.awoktask.sarah.carsmachinery.R;
import com.awoktask.sarah.carsmachinery.View.Adapter.CarsAdapter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements CarsContract.view{

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView ;
    private CarsAdapter adapter;
    ProgressDialog progressDialog;
    private CarsPresenter carsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carsPresenter=new CarsPresenter();
        carsPresenter.view=this;

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        carsPresenter.loadData();

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

    generateDataList(List<Car> carsListResponses, String ticks){
        int ticksInSeconds;
        try {
             ticksInSeconds = Integer.valueOf(ticks);
        }catch (Exception E){
            ticksInSeconds=-1;
        }

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CarsAdapter(this, carsListResponses,ticksInSeconds);
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


    @Override
    public void loadDataSuccess(List<Car> cars, String ticks) {
        progressDialog.dismiss();
        generateDataList(cars, ticks);
    }

    @Override
    public void loadDataFail(Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(MainActivity.this, "Something went wrong ... please try later ! "+t.getMessage(),Toast.LENGTH_LONG
        ).show();
    }
}

package com.awoktask.sarah.carsmachinery.Presenter;

import com.awoktask.sarah.carsmachinery.Model.CarsData.Car;

import java.util.List;

/**
 * Created by sarah on 4/10/19.
 */

public interface CarsContract {
    interface presenter{
        void loadData();
    }

    interface view{
        void loadDataSuccess(List<Car> cars, String ticks);
        void loadDataFail(Throwable t);
    }
}

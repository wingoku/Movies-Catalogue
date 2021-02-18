package com.wingoku.moviescatalogue.data.network.utils;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


import com.wingoku.moviescatalogue.data.network.models.responses.ApiResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

// CacheObject: Type for the Resource data (DB cache)
// RequestObject: Type for the API response (network request)
public abstract class NetworkBoundResource<CacheObject, RequestObject> {
    private static final String TAG = "NetworkBoundResource";
    //for adding different sources to liveData object
    private final MediatorLiveData<Resource<CacheObject>> results = new MediatorLiveData<>();

    public NetworkBoundResource() {
        init();
    }

    private void init() {
        //update live data from loading
        //doing this to assign loading status for the apiCall coming from Repo
        results.setValue(Resource.loading(null));
        //observe live data from local DB
        final LiveData<CacheObject> dbSource = loadFromDb();

        results.addSource(dbSource, new Observer<CacheObject>() {
            @Override
            public void onChanged(CacheObject cacheObject) {
                //no longer needed. dbSource was added to check if there's data in the cache(DB)
                results.removeSource(dbSource);

                //should refresh db data/add new data in db from network
                if(shouldFetch(cacheObject)) {
                    //get data from network
                    fetchFromNetwork(dbSource);
                }
                else {
                    //loading from db
                    results.addSource(dbSource, new Observer<CacheObject>() {
                        @Override
                        public void onChanged(CacheObject cacheObject) {
                            //sending data from db to ui
                            setValue(Resource.success(cacheObject));
                        }
                    });
                }
            }
        });
    }

    /***
     * 1. observe local db
     * 2. if need to refresh data, query the server
     * 3. stop observing local db
     * 4. insert new data in local db
     * 5. begin observing local db again to see refreshed data store in db from network
     * @param dbSource
     */
    private void fetchFromNetwork(final LiveData<CacheObject> dbSource) {
        //update live data for loading status
        results.addSource(dbSource, new Observer<CacheObject>() {
            @Override
            public void onChanged(CacheObject cacheObject) {
                setValue(Resource.loading(cacheObject));
            }
        });

        final LiveData<ApiResponse<RequestObject>> apiResponse = createCall();

        results.addSource(apiResponse, new Observer<ApiResponse<RequestObject>>() {
            @Override
            public void onChanged(ApiResponse<RequestObject> response) {
                results.removeSource(dbSource);
                results.removeSource(apiResponse);

                if(response instanceof ApiResponse.ApiSuccessResponse) {
                    Log.d(TAG, "onChanged: API success response");

                    //extracting body from ApiResponse object and storing it in db
                    writeInDBAndUpdateUI((RequestObject) ((ApiResponse.ApiSuccessResponse)response).getBody());
                }
                else if(response instanceof ApiResponse.ApiEmptyResponse) {
                    Log.d(TAG, "onChanged: ApiEmptyResponse");
                    //load whatever is in db and set its value in liveData
                    setLiveDataValueForSuccess();
                }
                else
                if(response instanceof ApiResponse.ApiErrorResponse) {
                    Log.d(TAG, "onChanged: ApiErrorResponse");
                    setLiveDataValueForErrorResponse((ApiResponse.ApiErrorResponse)response);
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void setValue(Resource<CacheObject> newValue) {
        Single.just(newValue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Resource<CacheObject>>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Resource<CacheObject> cacheObjectResource) {
                        if(results.getValue() != newValue) {
                            results.setValue(newValue);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void writeInDBAndUpdateUI(RequestObject response) {
        Single.just(response)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<RequestObject>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull RequestObject requestObject) {
                        CacheObject mappedObject = mapToDomainObject(requestObject);
                        saveCallResult(mappedObject);
                        setLiveDataValueForSuccess();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    protected abstract CacheObject mapToDomainObject(RequestObject dto);

    @SuppressLint("CheckResult")
    private void setLiveDataValueForSuccess() {

        LiveData<CacheObject> dbSource =  loadFromDb();
        Single.just(dbSource)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LiveData<CacheObject>>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull LiveData<CacheObject> cacheObjectLiveData) {
                        results.addSource(cacheObjectLiveData, new Observer<CacheObject>() {
                            @Override
                            public void onChanged(CacheObject cacheObject) {
                                results.removeSource(cacheObjectLiveData);
                                setValue(Resource.success(cacheObject));
                            }
                        });
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void setLiveDataValueForErrorResponse(ApiResponse.ApiErrorResponse response) {
        LiveData<CacheObject> dbSource = loadFromDb();
        Single.just(dbSource)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LiveData<CacheObject>>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull LiveData<CacheObject> cacheObjectLiveData) {
                        results.addSource(cacheObjectLiveData, new Observer<CacheObject>() {
                            @Override
                            public void onChanged(CacheObject cacheObject) {
                                results.removeSource(cacheObjectLiveData);
                                setValue(Resource.error(response.getErrorMessage(), cacheObject));
                            }
                        });
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull CacheObject item);

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable CacheObject data);

    @NonNull
    @MainThread
    protected abstract LiveData<CacheObject> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestObject>> createCall();

    @MainThread
    protected void onFetchFailed() {
    }

    public final LiveData<Resource<CacheObject>> getAsLiveData() {
        return results;
    }
}
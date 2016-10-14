package com.example.irwancannady.rxjavaandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.irwancannady.rxjavaandroid.adapter.CardAdapter;
import com.example.irwancannady.rxjavaandroid.model.Github;
import com.example.irwancannady.rxjavaandroid.service.ApiService;
import com.example.irwancannady.rxjavaandroid.service.ServiceFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String BASEURL = "https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardAdapter mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);

        Button bClear = (Button) findViewById(R.id.button_clear);
        Button bFetch = (Button) findViewById(R.id.button_fetch);
        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCardAdapter.clear();
            }
        });


        bFetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final ProgressDialog progress =
                        ProgressDialog.show(MainActivity.this,"Mohon tunggu","Mengambil Data",false,false);

                ApiService service = ServiceFactory.createRetrofitService(ApiService.class, ApiService.SERVICE_END_POINT);
                for(String login : Data.githubList) {
                    service.getUser(login)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Github>() {
                                @Override
                                public final void onCompleted() {
                                    // do nothing
                                    Toast.makeText(MainActivity.this, "Completed..!!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public final void onError(Throwable e) {
                                    Log.e("GithubDemo", e.getMessage());
                                }

                                @Override
                                public final void onNext(Github response) {
                                    progress.dismiss();
                                    mCardAdapter.addData(response);
                                }
                            });
                }
            }
        });
    }
}
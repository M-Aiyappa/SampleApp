package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static String JSON_URL="https://run.mocky.io/v3/f69f78c9-3beb-4b37-8a91-eb76275da221";
    List<ModelClass> mlist;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Loading... \nPlease wait it will take some time", Toast.LENGTH_SHORT).show();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        //mListView = (ListView) findViewById(R.id.listView);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.teal_700);


        mlist=new ArrayList<>();
        rv=findViewById(R.id.rv);

        GetData getData=new GetData();
        getData.execute();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    public class GetData extends AsyncTask<String,String,String> {



        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("appinfo");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    ModelClass model=new ModelClass();
                    model.setId(jsonObject1.getString("id"));
                    model.setName(jsonObject1.getString("name"));
                    model.setImg(jsonObject1.getString("image"));

                    mlist.add(model);

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(mlist);
        }

        @Override
        protected String doInBackground(String... strings) {

            String current="";
            URL url;
            HttpURLConnection urlConnection=null;
            try

            {

                try {

                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();

                    }

                    return current;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();

                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }


            return current;

        }
    }
    private void PutDataIntoRecyclerView(List<ModelClass> mlist){
        ListAdapter ListAdapter=new ListAdapter(this,mlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(ListAdapter);
    }


}



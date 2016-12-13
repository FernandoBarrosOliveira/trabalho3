package com.example.fernando.trabalho3.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fernando.trabalho3.model.Filme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by fernando on 11/12/16.
 */

public class FilmeAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        String urlString = strings[0];
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();


            StringBuffer buffer = new StringBuffer();

            if(inputStream == null){
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();


        }catch (IOException e){
            Log.e("Conection","Error",e);
            return null;

        }finally {
            if (urlConnection !=null){
                urlConnection.disconnect();
            }
        }


    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            //JSONArray jsonArray = jsonObject.getJSONArray("results");
            jsonToListaFilme(jsonObject);
            Log.d("onPost",jsonObject.toString());
        }catch (JSONException e){
            Log.e("JSOn","Error",e); e.printStackTrace();
        }
        super.onPostExecute(s);
    }

    private List<Filme> jsonToListaFilme (JSONObject jsonObject){
        List<Filme> listaFilme = new ArrayList<>();

        for(int i=0;i < jsonObject.length();i++){
            Filme filme = new Filme();
            try{
                filme.setImagem(jsonObject.getString("poster_path"));
                filme.setNome(jsonObject.getString("original_title"));
                filme.setSinopse(jsonObject.getString("overview"));
                listaFilme.add(filme);
            }catch (JSONException e){
                Log.e("JSOn","Error",e); e.printStackTrace();
            }
        }

        return listaFilme;

    }
}

package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    private static final String JSON_KEY_TITLES = "webTitle";
    private static final String JSON_KEY_SECTION = "sectionName";
    private static final String JSON_KEY_DATE = "webPublicationDate";
    private static final String JSON_KEY_URL = "webUrl";
    private static final String JSON_KEY_TYPE = "type";
    private static final String JSON_OBJECT = "response";
    private static final String JSON_ARRAY = "results";



    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "We have a problem Houston, HTTP request", e);
        }
        List<News> newslisteners = extractFeatureFromJson(jsonResponse);
        return newslisteners;
    }

    private static URL createUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with JSON result", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String newslistenerJSON) {
        if (TextUtils.isEmpty(newslistenerJSON)) {
            return null;
        }
        List<News> newsListeners = new ArrayList<>();

        try {
            JSONObject firstJsonResponse = new JSONObject(newslistenerJSON);
            JSONObject response = firstJsonResponse.getJSONObject(JSON_OBJECT);
            JSONArray results = response.getJSONArray(JSON_ARRAY);

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentResult = results.getJSONObject(i);

                String sectionName = currentResult.getString(JSON_KEY_SECTION);
                String title = currentResult.getString(JSON_KEY_TITLES);
                String date = currentResult.getString(JSON_KEY_DATE);
                String url = currentResult.getString(JSON_KEY_URL);
                String type = currentResult.getString(JSON_KEY_TYPE);

                News news = new News(title, sectionName, date, url, type, false);
                newsListeners.add(news);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem with parsing JSON response", e);
        }
        return newsListeners;
    }
}

package com.example.android.restapi;

import android.os.AsyncTask;

import com.example.android.startup.CreateFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by khancode on 6/20/15.
 */
public class CreateUser extends AsyncTask<String, Void, Void>
{
    private boolean success;
    private String error;

    private CreateFragment createFragment;

    public CreateUser(CreateFragment instance)
    {
        createFragment = instance;
    }

    @Override
    protected Void doInBackground(String... params) {

        String firstName = null;
        String lastName = null;
        String email = null;
        String username = null;
        String password = null;

        try {
            firstName = URLEncoder.encode(params[0], "UTF-8");
            lastName = URLEncoder.encode(params[1], "UTF-8");
            email = URLEncoder.encode(params[2], "UTF-8");
            username = URLEncoder.encode(params[3], "UTF-8");
            password = URLEncoder.encode(params[4], "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String url = "http://45.55.249.228/user/create";
        String urlParameters = "firstName="+firstName+"&lastName="+lastName+"&email="+email+"&username="+username+"&password="+password;

        StringBuffer response = new StringBuffer();

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
//			con.setRequestProperty("User-Agent", USER_AGENT);
//			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try
        {
            JSONObject jsonObj = new JSONObject(response.toString());

            success = jsonObj.getBoolean("success");
            error = jsonObj.getString("error");
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v)
    {
        // Return the results to LoginFragment
        createFragment.receiveCreateUserResult(success, error);
    }

}

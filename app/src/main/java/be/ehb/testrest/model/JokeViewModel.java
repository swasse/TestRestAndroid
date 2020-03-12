package be.ehb.testrest.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class JokeViewModel extends ViewModel {
    private MutableLiveData<RandomJoke> jokeOfTheDay;
    public ExecutorService threadExecutor = Executors.newFixedThreadPool(4);

    public JokeViewModel() {
        this.jokeOfTheDay = new MutableLiveData<>();
    }

    public MutableLiveData<RandomJoke> getJokeOfTheDay() {
        fetchJoke();
        return jokeOfTheDay;
    }

    private void fetchJoke() {
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.chucknorris.io/jokes/random?category=animal")
                        .get()
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String json = response.body().string();
                    JSONObject object  = new JSONObject(json);

                    String url = object.getString("icon_url");
                    String joke = object.getString("value");

                    RandomJoke rj = new RandomJoke(url, joke);
                    jokeOfTheDay.postValue(rj);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

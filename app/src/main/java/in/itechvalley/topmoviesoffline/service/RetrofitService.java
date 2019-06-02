package in.itechvalley.topmoviesoffline.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.itechvalley.topmoviesoffline.utils.constants.Urls;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService
{
    /*
    * Global Instance of Retrofit
    * */
    private Retrofit retrofit;

    /*
    * Constructor
    * */
    public RetrofitService()
    {
        /*
        * Create GsonConverterFactory separate
        * */
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        /*
        * Builder for Retrofit
        * */
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(Urls.BASE_URL);
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));

        retrofit = retrofitBuilder.build();
    }

    /*
    * Getter for Retrofit
    * */
    public Retrofit getRetrofit()
    {
        return retrofit;
    }
}

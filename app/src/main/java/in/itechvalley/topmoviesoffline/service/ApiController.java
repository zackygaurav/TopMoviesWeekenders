package in.itechvalley.topmoviesoffline.service;


import in.itechvalley.topmoviesoffline.model.TopMoviesModel;
import in.itechvalley.topmoviesoffline.utils.constants.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiController
{
    @GET(Urls.LOGIN_ENDPOINT)
    Call<TopMoviesModel> getAllMovies();

    /*@FormUrlEncoded
    @POST(Urls.LOGIN_ENDPOINT)
    Call<LoginResponseModel> login(
            @Field("username") String username,
            @Field("password") String password
    );*/
}

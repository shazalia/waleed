package commute.com.shazawdidi.commute;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("/commute/commute.php")
    Call<Commute> insertData(@Field("Fpoint") String FirstPoint, @Field("Lpoint") String LastPoint );
}

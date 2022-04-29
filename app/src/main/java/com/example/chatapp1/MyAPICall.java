
package com.example.chatapp1;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.PUT;
        import retrofit2.http.Path;


public interface MyAPICall {
    @POST("/upload/{new}.json")
    Call<List<DataModel>> getData(@Path("new") String s1, @Body DataModel dataModel);

    @GET("/upload/sushil.json")
    Call<DataModel> getData();

    @PUT("/upload/{new}.json")
    Call<DataModel> setDataWithoutRandomness(@Path("new") String s1, @Body DataModel dataModel);

}

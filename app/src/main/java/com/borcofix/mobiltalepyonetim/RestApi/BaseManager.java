package com.borcofix.mobiltalepyonetim.RestApi;

public class BaseManager { // Bu sınıfımızda RestApiClient sınıfımızın nesnesini oluşturacağız, Url'mizi vereceğiz. Bu da bize RestApi'yi döndürecek.

    protected RestApi getRestApi() // Burada bize getRestApi adlı bir RestApi dönecek. Ayrıca bunun extend, yani kalıtım alacağı bir class olduğu için erişim belirleyicisini "protected" yaptık
    {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.Url);
        return restApiClient.getRestApi();
    }

}

package com.tuanhv.mvvmarch.base.api

import android.content.Context
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tuanhv.mvvmarch.base.BuildConfig
import com.tuanhv.mvvmarch.base.R
import com.tuanhv.mvvmarch.base.api.ApiModule.provideAuthApi
import com.tuanhv.mvvmarch.base.api.ApiModule.provideHeadersInterceptor
import com.tuanhv.mvvmarch.base.api.ApiModule.provideLoggingInterceptor
import com.tuanhv.mvvmarch.base.api.ApiModule.provideMockOkHttpClient
import com.tuanhv.mvvmarch.base.api.ApiModule.provideMockRestAdapter
import com.tuanhv.mvvmarch.base.api.ApiModule.provideMoshi
import com.tuanhv.mvvmarch.base.api.ApiModule.provideOkHttpCache
import com.tuanhv.mvvmarch.base.api.ApiModule.provideOkHttpClient
import com.tuanhv.mvvmarch.base.api.ApiModule.providePostApi
import com.tuanhv.mvvmarch.base.api.ApiModule.provideRestAdapter
import com.tuanhv.mvvmarch.base.api.ApiModule.provideUserApi
import com.tuanhv.mvvmarch.base.api.auth.AuthApi
import com.tuanhv.mvvmarch.base.api.common.coroutines.NetworkResponseAdapterFactory
import com.tuanhv.mvvmarch.base.api.common.mock.MockInterceptor
import com.tuanhv.mvvmarch.base.api.post.PostApi
import com.tuanhv.mvvmarch.base.api.user.UserApi
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefs
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by hoang.van.tuan on 2/1/18.
 */
val networkModule = module {
    single { provideOkHttpCache(androidContext()) }
    single { provideLoggingInterceptor() }
    single { provideHeadersInterceptor(get()) }

    single(named("mock")) { provideMockOkHttpClient(androidContext(), get(), get(), get()) }
    single(named("main")) { provideOkHttpClient(androidContext(), get(), get(), get()) }

    single { provideMoshi() }
    single(named("mock")) { provideMockRestAdapter(androidContext(), get(named("mock")), get()) }
    single(named("main")) { provideRestAdapter(androidContext(), get(named("main")), get()) }

    single { provideAuthApi(get(named("mock"))) }
    single { providePostApi(get(named("mock"))) }
    single { provideUserApi(get(named("main"))) }
}

object ApiModule : KoinComponent {

    private const val CACHE_SIZE: Long = 10 * 1024 * 1024
    private const val CACHE_TIME_SEC = 30
    private const val TIME_OUT: Long = 60

    private val cacheInterceptor: Interceptor
        get() = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())
                val cacheControl = CacheControl.Builder()
                        .maxAge(CACHE_TIME_SEC, TimeUnit.SECONDS)
                        .build()

                response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build()

                return response
            }
        }

    fun provideOkHttpCache(context: Context): Cache
            = Cache(File(context.cacheDir, "http-cache"), CACHE_SIZE)

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i("OkHttp", message)
            }
        })
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    fun provideHeadersInterceptor(
            authSharedPrefs: AuthSharedPrefs
    ): Interceptor {
        return Interceptor { chain ->
            val initialRequest = chain.request()
            val accessToken = authSharedPrefs.getAccessToken()
            val request = if (accessToken.isNotBlank()) {
                initialRequest.newBuilder()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .addHeader("Accept", "application/json")
                        .build()
            } else {
                initialRequest.newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
            }

            return@Interceptor chain.proceed(request)
        }
    }

    fun provideMockOkHttpClient(
            context: Context,
            cache: Cache,
            headersInterceptor: Interceptor,
            loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(headersInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor)
                .addInterceptor(ChuckInterceptor(context))
                .addInterceptor(MockInterceptor(context))
                .addNetworkInterceptor(StethoInterceptor())
                .cache(cache)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build()
    }

    fun provideOkHttpClient(
            context: Context,
            cache: Cache,
            headersInterceptor: Interceptor,
            loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(headersInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor)
                .addInterceptor(ChuckInterceptor(context))
                .addNetworkInterceptor(StethoInterceptor())
                .cache(cache)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build()
    }

    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    fun provideMockRestAdapter(
            context: Context,
            okHttpClient: OkHttpClient,
            moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.getString(R.string.api_end_point))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .build()
    }

    fun provideRestAdapter(
            context: Context,
            okHttpClient: OkHttpClient,
            moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.getString(R.string.api_end_point))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .build()
    }

    fun provideAuthApi(restAdapter: Retrofit): AuthApi {
        return restAdapter.create(AuthApi::class.java)
    }

    fun providePostApi(restAdapter: Retrofit): PostApi {
        return restAdapter.create(PostApi::class.java)
    }

    fun provideUserApi(restAdapter: Retrofit): UserApi {
        return restAdapter.create(UserApi::class.java)
    }

}

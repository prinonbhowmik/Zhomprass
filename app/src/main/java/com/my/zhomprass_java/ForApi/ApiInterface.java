package com.my.zhomprass_java.ForApi;

import com.my.zhomprass_java.Models.Bazar;
import com.my.zhomprass_java.Models.BrandList;
import com.my.zhomprass_java.Models.CatagoryList;
import com.my.zhomprass_java.Models.District;
import com.my.zhomprass_java.Models.Division;
import com.my.zhomprass_java.Models.Members;
import com.my.zhomprass_java.Models.MultiProducts;
import com.my.zhomprass_java.Models.Offers;
import com.my.zhomprass_java.Models.RecentProducts;
import com.my.zhomprass_java.Models.RelatedProduct;
import com.my.zhomprass_java.Models.SearchProduct;
import com.my.zhomprass_java.Models.ShopList;
import com.my.zhomprass_java.Models.SingleProduct;
import com.my.zhomprass_java.Models.SliderImage;
import com.my.zhomprass_java.Models.SubBrandList;
import com.my.zhomprass_java.Models.SubCategoryList;
import com.my.zhomprass_java.Models.Thana;
import com.my.zhomprass_java.Models.ThiredBrandList;
import com.my.zhomprass_java.Models.ThiredCategoryList;
import com.my.zhomprass_java.Models.UserInfo;
import com.my.zhomprass_java.Models.UserShortInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("login_api.php")
    Call<List<UserInfo>> login(
            @Query("user_name") String username,
            @Query("password") String password);

    @GET("total_member.php")
    Call<List<Members>> getTotalZpl(@Query("type") String zpl);

    @GET("user_short_info.php")
    Call<List<UserShortInfo>> getUserInfo (@Query("customer_id") int id);

    @GET("slider.php?limit_from=0&limit=5")
    Call<List<SliderImage>> getSliderImage();

    @GET("product.php")
    Call<List<Offers>> getOffers(@Query("offer") int id);

    @GET("total_member.php")
    Call<List<Members>> getTotalFree(@Query("type") String free);

    @GET("category_list.php")
    Call<List<CatagoryList>> getCatagory();

    @GET("category_list_sub.php")
    Call<List<SubCategoryList>> getSubCategory(@Query("cat_id") int id);

    @GET("category_list_third.php")
    Call<List<ThiredCategoryList>> getThiredCategory(@Query("sub_cat_id") int id);

    @GET("brand_list.php?")
    Call<List<BrandList>> getBrands();

    @GET("brand_list.php")
    Call<List<SubBrandList>> getBrandsWithSub(@Query("cat_id") int id);

    @GET("brand_list.php")
    Call<List<ThiredBrandList>> getBrandsWithThired(@Query("sub_cat_id") int id);

    @GET("product.php")
    Call<List<MultiProducts>> getMultiProductByBrand(@Query("brand_id") int id);

    @GET("division.php")
    Call<List<Division>> getDivision();

    @GET("district.php")
    Call<List<District>> getDistrict(@Query("division_id") int id);

    @GET("thana.php")
    Call<List<Thana>> getThana(@Query("district_id") int id);

    @GET("bazar.php")
    Call<List<Bazar>> getBazar(@Query("thana_id") int id);

    @GET("shop.php")
    Call<List<ShopList>> getShop(@Query("bazar_id") int id);

    @GET("product.php")
    Call<List<RecentProducts>> getRecentProduct(@Query("recent") int id);

    @GET("product.php")
    Call<List<RecentProducts>> getScrolledProduct(@Query("Limit_from") int start, @Query("limit") int end);

    @GET("product.php")
    Call<List<MultiProducts>> getMultiProduct(@Query("third_cat_id") int id);

    @GET("shop_product.php")
    Call<List<MultiProducts>>getShopProduct(@Query("shop_id") int id,
                                            @Query("limit_from")int limit,
                                            @Query("limit")int end);

    @GET("product.php")
    Call<List<SingleProduct>> getSingleProduct(@Query("id") int id);

    @GET("product.php")
    Call<List<RelatedProduct>> getRelatedProduct(@Query("third_cat_id")int id);

    @GET("product.php")
    Call<List<SearchProduct>> getProduct(@Query("product_name") String name);

    @GET("brand_list.php?")
    Call<List<BrandList>> searchBrand(@Query("brand_name") String name);

    @GET("shop.php")
    Call<List<ShopList>> searchShop(@Query("shop_name") String name);


}

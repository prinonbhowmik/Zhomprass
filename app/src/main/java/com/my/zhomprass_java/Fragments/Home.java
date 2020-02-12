package com.my.zhomprass_java.Fragments;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.tabs.TabLayout;
import com.my.zhomprass_java.Adapters.OfferAdapter;
import com.my.zhomprass_java.Adapters.RecentProductAdapter;
import com.my.zhomprass_java.Adapters.SliderAdapter;
import com.my.zhomprass_java.Adapters.TabPaggerAdapter;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.Offers;
import com.my.zhomprass_java.Models.RecentProducts;
import com.my.zhomprass_java.Models.SliderImage;
import com.my.zhomprass_java.Models.UserShortInfo;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SliderView sliderView;
    private RecyclerView recyclerView;
    private List<Offers> list;
    private List<SliderImage> sliderImages;
    private List<RecentProducts> recentProductList;
    private List<RecentProducts> tempProductList;
    private List<UserShortInfo> userShortInfos;
    private RecyclerView recentProductRecyler;
    private GridLayoutManager layoutManager;
    private ProgressBar progressBar;
    private boolean isScrolled = false;
    private int currentItem;
    private int totalItem;
    private int scrolledItem;
    private int counter;
    private RecentProductAdapter recentApapter;
    private ImageView levelImage;
    private ImageView positionImage;
    private ImageView rankImage;
    private ImageView availbleImage;
    private SharedPreferences sharedPreferences;
    private ApiInterface api;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.productdetails_viewPager);
        sliderView=view.findViewById(R.id.imageSlided);
        recyclerView=view.findViewById(R.id.offerRecyclerView);
        levelImage=view.findViewById(R.id.levelImageView);
        positionImage=view.findViewById(R.id.positionImage);
        rankImage=view.findViewById(R.id.rankImage);
        availbleImage=view.findViewById(R.id.availableImage);
        list = new ArrayList<>();
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        userShortInfos = new ArrayList<>();
        sliderImages = new ArrayList<>();
        recentProductRecyler=view.findViewById(R.id.recentProductsRecycler);
        layoutManager= new GridLayoutManager(getActivity(),2);
        recentProductRecyler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recentProductRecyler.setLayoutManager(layoutManager);
        api = ApiUtils.getUserService();
        recentProductList = new ArrayList<>();
        tempProductList = new ArrayList<>();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getSliderImage();
        setUpViewPagger();
        getOffers();
        getRecentProducts();
        scrolling();

        sharedPreferences = getContext().getSharedPreferences("Customer_Id",MODE_PRIVATE);
        int id = sharedPreferences.getInt("cust_id",0);
        if (id==0){
            infowithoutLogIn();
        }

        if (id!=0){

            Call<List<UserShortInfo>> call = api.getUserInfo(id);
            call.enqueue(new Callback<List<UserShortInfo>>() {
                @Override
                public void onResponse(Call<List<UserShortInfo>> call, Response<List<UserShortInfo>> response) {
                    if (response.isSuccessful()){
                        if (response.body()==null){
                            infowithoutLogIn();
                        }
                        else{

                            userShortInfos = response.body();
                            infowithLogIn(userShortInfos.get(0).getZpl(),userShortInfos.get(0).getPosition()
                                    ,userShortInfos.get(0).getRank(),userShortInfos.get(0).getAvailable_balance());

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<UserShortInfo>> call, Throwable t) {

                    Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void infowithLogIn(String level,int position,int rank,double available) {
        TextDrawable levelDrawable = TextDrawable.builder().beginConfig().fontSize(20).endConfig()
                .buildRoundRect(level,getResources().getColor(R.color.blue_ribbon),50);

        TextDrawable positionDrawable = TextDrawable.builder().beginConfig().fontSize(20).endConfig()
                .buildRoundRect(String.valueOf(position),getResources().getColor(R.color.torch_red),50);

        TextDrawable rankDrawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).fontSize(20).endConfig()
                .buildRoundRect(String.valueOf(rank),getResources().getColor(R.color.supernova),50);
        TextDrawable avaibleDrawable = TextDrawable.builder().beginConfig().fontSize(20).endConfig()
                .buildRoundRect(String.valueOf(available),getResources().getColor(R.color.fern),50);



        levelImage.setImageDrawable(levelDrawable);
        positionImage.setImageDrawable(positionDrawable);
        rankImage.setImageDrawable(rankDrawable);
        availbleImage.setImageDrawable(avaibleDrawable);
    }

    private void infowithoutLogIn() {

       TextDrawable levelDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig()
               .buildRoundRect("0",getResources().getColor(R.color.blue_ribbon),50);
        TextDrawable positionDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig()
                .buildRoundRect("0",getResources().getColor(R.color.torch_red),50);
        TextDrawable rankDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig()
                .buildRoundRect("0",getResources().getColor(R.color.supernova),50);
        TextDrawable avaibleDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig()
                .buildRoundRect("0",getResources().getColor(R.color.fern),50);


        levelImage.setImageDrawable(levelDrawable);
        positionImage.setImageDrawable(positionDrawable);
        rankImage.setImageDrawable(rankDrawable);
        availbleImage.setImageDrawable(avaibleDrawable);
    }

    private void getSliderImage() {

        Call<List<SliderImage>> call = api.getSliderImage();
        call.enqueue(new Callback<List<SliderImage>>() {
            @Override
            public void onResponse(Call<List<SliderImage>> call, Response<List<SliderImage>> response) {

                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        sliderImages = response.body();
                        setupSlider(sliderImages);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SliderImage>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSlider(List<SliderImage> sliderImages) {
        SliderAdapter adapter = new SliderAdapter(getActivity(),sliderImages);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
    }


    private void setUpViewPagger() {

        TabPaggerAdapter tabPaggerAdapter = new TabPaggerAdapter(getFragmentManager());
        tabPaggerAdapter.addFragment(new CategoryFragment());
        tabPaggerAdapter.addFragment(new BrandsFragment());
        tabPaggerAdapter.addFragment(new ShopsFragment());
        viewPager.setAdapter(tabPaggerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void getRecentProducts() {
        Call<List<RecentProducts>> call = api.getRecentProduct(1);
        call.enqueue(new Callback<List<RecentProducts>>() {
            @Override
            public void onResponse(Call<List<RecentProducts>> call, Response<List<RecentProducts>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        recentProductList = response.body();
                        recentApapter = new RecentProductAdapter(recentProductList,getContext());
                        recentProductRecyler.setAdapter(recentApapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecentProducts>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
     private void getOffers() {
        Call<List<Offers>> call = api.getOffers(1);
        call.enqueue(new Callback<List<Offers>>() {
            @Override
            public void onResponse(Call<List<Offers>> call, Response<List<Offers>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{

                        list = response.body();
                        OfferAdapter adapter = new OfferAdapter(getContext(),list);
                        recyclerView.setAdapter(adapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<Offers>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
     private void scrolling() {
        recentProductRecyler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    currentItem = layoutManager.getChildCount();
                    totalItem = layoutManager.getItemCount();
                    scrolledItem = layoutManager.findFirstVisibleItemPosition();
                    isScrolled=true;

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = layoutManager.getChildCount();
                totalItem = layoutManager.getItemCount();
                scrolledItem = layoutManager.findFirstVisibleItemPosition();
                if (isScrolled && (currentItem + scrolledItem == totalItem)){
                    isScrolled=false;
                    dataFetch();
                }
            }
        });
    }

    private void dataFetch() {
        int end = counter+10;
        Call<List<RecentProducts>> call = api.getScrolledProduct(counter,end);
        call.enqueue(new Callback<List<RecentProducts>>() {
            @Override
            public void onResponse(Call<List<RecentProducts>> call, Response<List<RecentProducts>> response) {
                if (response.isSuccessful()){
                    if (response.body()==null){
                        return;
                    }
                    else{
                        tempProductList=response.body();
                        recentProductList.addAll(tempProductList);
                        tempProductList.clear();
                        recentApapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecentProducts>> call, Throwable t) {

            }
        });
        counter=end;
    }

}

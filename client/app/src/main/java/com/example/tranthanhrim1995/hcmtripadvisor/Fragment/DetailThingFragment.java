package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GroupedThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListCommentAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Comment;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.DetailImage;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.DetailThing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Location;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.response.EndPointResponse;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.WebServiceInterface;
import com.kobakei.ratethisapp.RateThisApp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailThingFragment extends Fragment {

    ArrayList<Thing> listHotel, listFood;
    RecyclerView rvHotelDetailThing, rvFoodDetailThing, rvListComment;
    ImageView btnCheckinDetail, btnLikeDetail;
    ImageView btnClickRate;

    GroupedThingsToDoAdapter mAdapterHotel, mAdapterFood;
    FragmentManager fragmentManager;

    Location location;
    String detail;
    String image;
    String idThing;
    String nameThing;
    String thumbnail;
    String category;
    Float rate;

    ImageLoader imageLoader;
    ArrayList<String> images = new ArrayList<>();

    String oldIdThing = "";

    ArrayList<Bitmap> imagesLoaded = new ArrayList<>();
    DetailThing detailThing;

    ListCommentAdapter mAdapterComment = null;
    ArrayList<Comment> listComment = new ArrayList<>();

    public DetailThingFragment() {
        listHotel = new ArrayList<>();
        listFood = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            String name = "Destination" + i;
            listHotel.add(new Thing("Museums", name, "This is Detail"));
            listFood.add(new Thing("Museums", name, "This is Detail"));
        }
    }

    @BindView(R.id.rbSummaryRateDetail)
    RatingBar rbSummaryRateDetail;

    @BindView(R.id.etContentComment)
    EditText etContentComment;

    @BindView(R.id.ivDetailImage1)
    ImageView ivDetailImage1;

    @BindView(R.id.ivDetailImage2)
    ImageView ivDetailImage2;

    @BindView(R.id.ivDetailImage3)
    ImageView ivDetailImage3;

    @BindView(R.id.tvNameThingDetail)
    TextView tvNameThingDetail;

    @BindView(R.id.tvHourDetail)
    TextView tvHourDetail;

    @BindView(R.id.tvDescriptionDetail)
    TextView tvDescriptionDetail;

    @BindView(R.id.tvRateCounting)
    TextView tvRateCounting;

    @BindView(R.id.rvListCommentDetail)
    RecyclerView rvListCommentDetail;

    @BindView(R.id.layoutInformationTitle)
    LinearLayout layoutInformationTitle;

    @BindView(R.id.layoutInformationDetail)
    LinearLayout layoutInformationDetail;

    @BindView(R.id.tvlocationDetail)
    TextView tvlocationDetail;

    @BindView(R.id.tvPhoneDetail)
    TextView tvPhoneDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        imageLoader = ImageLoader.getInstance();

        ScrollView detailThingFragment = (ScrollView) inflater.inflate(R.layout.fragment_detail_thing, null);
        ButterKnife.bind(this, detailThingFragment);

        LayerDrawable stars = (LayerDrawable) rbSummaryRateDetail.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);

        if (mAdapterComment == null) {
            mAdapterComment = new ListCommentAdapter(listComment, getActivity());
        }
        RecyclerView.LayoutManager mLayoutManagerListComment = new LinearLayoutManager(getActivity().getApplicationContext());
        rvListCommentDetail.setLayoutManager(mLayoutManagerListComment);
        rvListCommentDetail.setItemAnimator(new DefaultItemAnimator());
        rvListCommentDetail.setAdapter(mAdapterComment);

        return detailThingFragment;
    }

    @OnClick(R.id.btnMapDetail) void showMap() {
        Bundle bundle = new Bundle();
        bundle.putFloat("lon", location.getLongtitude());
        bundle.putFloat("lat", location.getLatitude());
        bundle.putString("nameThing", nameThing);
        Fragment fragment = FragmentFactory.getInstance().getMapThingFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.layoutImageDetail) void showGridImage() {
        Bundle bundle = new Bundle();
        bundle.putString("nameThing", nameThing);
        bundle.putString("image", image);
        bundle.putStringArrayList("images", images);
        Fragment fragment = FragmentFactory.getInstance().getGridImageFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();

    }

    @OnClick(R.id.btnSeeAllComments) void showAllComments() {
        Bundle bundle = new Bundle();
        bundle.putString("idThing", idThing);
        DialogFragment dialogFragment = FragmentFactory.getInstance().getListCommentDialogFragment();
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getActivity().getFragmentManager(), "all-comments");
    }

    @OnClick(R.id.btnSendComment) void sendComment() {
        if (etContentComment.getText().toString().equals("")) {
            return;
        }
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String idUser = sharedPref.getString(getActivity().getString(R.string.google_id), "");
        HashMap map = new HashMap();
        map.put("_idUser", idUser);
        map.put("_idThing", idThing);
        map.put("_content", etContentComment.getText().toString());
        Call<EndPointResponse> callSendComment = DataGlobal.getInstance().getService()
                .postComment(map);
        callSendComment.enqueue(new Callback<EndPointResponse>() {
            @Override
            public void onResponse(Call<EndPointResponse> call, Response<EndPointResponse> response) {
                Date date = new Date();
                listComment.add(new Comment("", "http://aminoapps.com/static/img/user-icon-placeholder.png",
                        etContentComment.getText().toString(), date.toString()));
                if (listComment.size() > 2) {
                    listComment.remove(0);
                }
                mAdapterComment.notifyDataSetChanged();
                etContentComment.setText("");
            }

            @Override
            public void onFailure(Call<EndPointResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btnClickRate) void clickRate() {
        Bundle bundle = new Bundle();
        bundle.putString("idThing", idThing);
        DialogFragment dialogFragment = FragmentFactory.getInstance().getRateDialogFragment();
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getActivity().getFragmentManager(), "rate-dialog");
    }

    @Override
    public void onResume() {
        super.onResume();
        final Bundle bundle = getArguments();
        nameThing = bundle.getString("nameThing");
        location = new Location(bundle.getFloat("lon"), bundle.getFloat("lat"));
        idThing = bundle.getString("id");
        detail = bundle.getString("detail");
        image = bundle.getString("image");
        thumbnail = bundle.getString("thumbnail");
        rate = bundle.getFloat("rate");
        category = bundle.getString("category");

        images.clear();
        images.add(thumbnail);

        if (category.equals("FoodnDrink") || category.equals("Hotels")) {
            layoutInformationDetail.setVisibility(View.VISIBLE);
            layoutInformationTitle.setVisibility(View.VISIBLE);
        } else {
            layoutInformationDetail.setVisibility(View.GONE);
            layoutInformationTitle.setVisibility(View.GONE);
        }

        ManageActionBar.getInstance().setTitle(nameThing);
        ManageActionBar.getInstance().showButtonBack();

        rbSummaryRateDetail.setRating(rate);

        WebServiceInterface service = DataGlobal.getInstance().getService();
        Call<List<DetailThing>> callDetailThing = service.detailThing(detail);
        callDetailThing.enqueue(new Callback<List<DetailThing>>() {
            @Override
            public void onResponse(Call<List<DetailThing>> call, Response<List<DetailThing>> response) {
                detailThing = response.body().get(0);
                tvNameThingDetail.setText(nameThing);
                String openHour = "";
                for(int i =0; i < detailThing.get_openHour().size(); i++) {
                    openHour += detailThing.get_openHour().get(i) + " ";
                }
                tvHourDetail.setText(openHour);
                tvDescriptionDetail.setText(detailThing.get_description());
                tvlocationDetail.setText(detailThing.get_location());
                tvPhoneDetail.setText(detailThing.get_phoneNumber());

            }

            @Override
            public void onFailure(Call<List<DetailThing>> call, Throwable t) {

            }
        });

        Call<List<DetailImage>> callDetailImage = service.detailImage(image);
        callDetailImage.enqueue(new Callback<List<DetailImage>>() {
            @Override
            public void onResponse(Call<List<DetailImage>> call, Response<List<DetailImage>> response) {
                DetailImage temp = response.body().get(0);
                if (oldIdThing.equals(idThing) || idThing.equals("")) {
                    ivDetailImage1.setImageBitmap(imagesLoaded.get(0));
                    ivDetailImage2.setImageBitmap(imagesLoaded.get(1));
                    ivDetailImage3.setImageBitmap(imagesLoaded.get(2));
                } else {
                    imagesLoaded.clear();
                    imageLoader.displayImage(thumbnail, ivDetailImage1, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            imagesLoaded.add(loadedImage);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
                    imageLoader.displayImage(temp.get_link().get(0), ivDetailImage2, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            imagesLoaded.add(loadedImage);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
                    imageLoader.displayImage(temp.get_link().get(1), ivDetailImage3, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            imagesLoaded.add(loadedImage);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
                    oldIdThing = idThing;
                }

                images.addAll(temp.get_link());
            }

            @Override
            public void onFailure(Call<List<DetailImage>> call, Throwable t) {

            }
        });

        Call<Map> callRateCounting = service.getRateCounting(idThing);
        callRateCounting.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                long longRate = Math.round((Double)response.body().get("count"));
                if (longRate <= 1) {
                    tvRateCounting.setText(String.valueOf(longRate) + " Review");
                } else {
                    tvRateCounting.setText(String.valueOf(longRate) + " Reviews");
                }

            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

            }
        });

        Call<List<Comment>> callListComment = service.getComments(idThing);
        callListComment.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                listComment.clear();
                if (response.body().size() >= 2) {
                    listComment.add(response.body().get(0));
                    listComment.add(response.body().get(1));
                } else if (response.body().size() >= 1) {
                    listComment.add(response.body().get(0));
                }
                mAdapterComment.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }


}

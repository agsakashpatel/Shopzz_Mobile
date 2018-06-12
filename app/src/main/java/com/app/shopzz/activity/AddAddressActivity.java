package com.app.shopzz.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.app.shopzz.R;
import com.app.shopzz.api.ApiList;
import com.app.shopzz.api.RequestCode;
import com.app.shopzz.api.RequestListener;
import com.app.shopzz.api.RestClient;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.model.Area;
import com.app.shopzz.model.Country;
import com.app.shopzz.utility.Utils;
import com.app.shopzz.utility.ValidationClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddAddressActivity extends AppCompatActivity implements OnValidationClick, RequestListener {

    private LinearLayout llParent;
    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private LinearLayout llSelectCountry;
    private LinearLayout llSelectArea;
    private TextView tvCountry;
    private TextView tvArea;
    private EditText etAddressTitle;
    private EditText etBlock;
    private EditText etStreet;
    private EditText etAuanue;
    private EditText etBuilding;
    private EditText etFloor;
    private EditText etApartment;
    private EditText etMobileNumber;
    private EditText etLandlineNumber;
    private CheckBox cbSaveAddress;

    List<Country> countryList = new ArrayList<>();
    List<Area> areaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_add_address);

        llParent = GenericView.findViewById(this, R.id.llParent);
        Utils.getInstance().setupOutSideTouchHideKeyboard(llParent);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.close_icon);
        tvTitle.setText(getResources().getString(R.string.str_add_address));
        tvEdit.setText(getResources().getString(R.string.str_save));

        llSelectCountry = GenericView.findViewById(this, R.id.llSelectCountry);
        llSelectArea = GenericView.findViewById(this, R.id.llSelectArea);
        tvCountry = GenericView.findViewById(this, R.id.tvCountry);
        tvArea = GenericView.findViewById(this, R.id.tvArea);
        etAddressTitle = GenericView.findViewById(this, R.id.etAddressTitle);
        etBlock = GenericView.findViewById(this, R.id.etBlock);
        etStreet = GenericView.findViewById(this, R.id.etStreet);
        etAuanue = GenericView.findViewById(this, R.id.etAuanue);
        etBuilding = GenericView.findViewById(this, R.id.etBuilding);
        etFloor = GenericView.findViewById(this, R.id.etFloor);
        etApartment = GenericView.findViewById(this, R.id.etApartment);
        etMobileNumber = GenericView.findViewById(this, R.id.etMobileNumber);
        etLandlineNumber = GenericView.findViewById(this, R.id.etLandlineNumber);
        cbSaveAddress = GenericView.findViewById(this, R.id.cbSaveAddress);
    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.llSelectCountry:
                break;
            case R.id.llSelectArea:
                break;
            case R.id.tvEdit:
                if (isValidate()) {

                }
                break;
        }
    }

    // check velidations
    private boolean isValidate() {
        if (ValidationClass.isEmpty(etAddressTitle.getText().toString())) {
            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_address_title_validation), etAddressTitle, this);
            return false;
        } else {
            if (ValidationClass.isEmpty(etBlock.getText().toString())) {
                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_block_validation), etBlock, this);
                return false;
            } else {
                if (tvCountry.getText().toString().equalsIgnoreCase(getResources().getString(R.string.str_select_country))) {
                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_select_country_validation));
                    return false;
                } else {
                    if (tvArea.getText().toString().equalsIgnoreCase(getResources().getString(R.string.str_select_area))) {
                        ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_select_area_validation));
                        return false;
                    } else {
                        if (ValidationClass.isEmpty(etStreet.getText().toString())) {
                            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_street_validation), etStreet, this);
                            return false;
                        } else {
                            if (ValidationClass.isEmpty(etAuanue.getText().toString())) {
                                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_avanue_validation), etAuanue, this);
                                return false;
                            } else {
                                if (ValidationClass.isEmpty(etBuilding.getText().toString())) {
                                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_building_validation), etBuilding, this);
                                    return false;
                                } else {
                                    if (ValidationClass.isEmpty(etFloor.getText().toString())) {
                                        ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_floor_validation), etFloor, this);
                                        return false;
                                    } else {
                                        if (ValidationClass.isEmpty(etApartment.getText().toString())) {
                                            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_apartment_validation), etApartment, this);
                                            return false;
                                        } else {
                                            if (ValidationClass.isEmpty(etMobileNumber.getText().toString())) {
                                                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_mobile_validation), etMobileNumber, this);
                                                return false;
                                            }
                                            if (etMobileNumber.getText().toString().length() != 10) {
                                                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_mobile_length_validation), etMobileNumber, this);
                                                return false;
                                            } else {
                                                if (ValidationClass.isEmpty(etLandlineNumber.getText().toString())) {
                                                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_landline_validation), etLandlineNumber, this);
                                                    return false;
                                                } else {
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void OnValidationClick(View mView) {
        mView.requestFocus();
        Utils.getInstance().launchKeyboard(this, (EditText) mView);
    }

    //call country list api
    private void getCountryList() {
        JSONObject param = new JSONObject();
        RestClient.getInstance().post(this, Request.Method.GET, ApiList.APIs.getCountryList.getUrl()
                , param, this, RequestCode.COUNTRY, true, true);
    }

    //call area list  api
    private void getAreaList() {
        JSONObject param = new JSONObject();
        try {
            param.put(ApiList.KEY_COUNTRY_ID, 0);
            RestClient.getInstance().post(this, Request.Method.POST, ApiList.APIs.getAreaList.getUrl()
                    , param, this, RequestCode.AREA, true, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //get api callback response
    @Override
    public void onComplete(RequestCode requestCode, Object object, String message) {
        switch (requestCode) {
            case COUNTRY:
                countryList = (List<Country>) object;
                break;
            case AREA:
                areaList = (List<Area>) object;
                break;
        }

    }

    @Override
    public void onException(int statusCode, String error, RequestCode requestCode) {

    }

    @Override
    public void onRetryRequest(RequestCode requestCode) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}

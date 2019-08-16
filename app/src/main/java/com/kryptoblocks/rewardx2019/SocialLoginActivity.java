package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.gson.Gson;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GeneralError;
import com.kryptoblocks.rewardx2019.pojo.GenericErrorPojo;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerData;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.SignUpActivity.emailIdRegister;
import static com.kryptoblocks.rewardx2019.SignUpActivity.passwordRegister;

public class SocialLoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    ApiInterface apiInterface;
    TextView text_sign_up_now;
    TextInputLayout emailId_TextInputlayout, password_TextInputlayout;
    RadioButton radio_email, radio_phoneNumber;
     EditText  emailId_editT,password_editT;
    Button gmail_button, login;
    private static final String TAG = SocialLoginActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
        String id, pswd ;
   public  static String  user_uuid;
    public static String user_fullName;
    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesLogin;
    String user_id, password;
    public static String user_unigue_id;
    String login_type;
    RadioGroup radioGroup;
    int flag = 0;
    //  CallbackManager callbackManager;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        emailId_TextInputlayout = findViewById(R.id.text_input_layout_emailId);
        password_TextInputlayout = findViewById(R.id.password_input_layout);
        text_sign_up_now = findViewById(R.id.sign_up_now);
        emailId_editT = findViewById(R.id.editText_emailId_login);
        password_editT= findViewById(R.id.editText_password_login);
        login = findViewById(R.id.login_button);

        radio_email = findViewById(R.id.radio_button_chooseEmail);
        radio_phoneNumber = findViewById(R.id.radio_button_choosePhoneNumber);
        radioGroup = findViewById(R.id.radioGroup_signIn);

        radio_email.setChecked(true);
        emailId_TextInputlayout.setHint("Email Id");
        flag = 1;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.radio_button_choosePhoneNumber) {
                    emailId_TextInputlayout.setHint("Phone Number");
                    flag = 0;
                    System.out.println("ph num:"+flag);
                }
                else if(checkedId == R.id.radio_button_chooseEmail)
                {
                    emailId_TextInputlayout.setHint("Email Id");
                    flag = 1;
                    System.out.println("email:"+flag);
                }
            }

        });


        text_sign_up_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferencesLogin.edit();
                editor.remove("user_id");
                editor.remove("user_pswd");
                editor.remove("user_uuid");
                editor.clear();
                editor.commit();
                Intent i = new Intent(getApplication(), SignUpActivity.class);
                startActivity(i);
            }
        });

        sharedPreferencesLogin = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id = sharedPreferencesLogin.getString("user_id","hi");
        password = sharedPreferencesLogin.getString("user_pswd","bye");
        //Toast.makeText(SocialLoginActivity.this, "User id:" + user_id + " User password:" + password, Toast.LENGTH_SHORT).show();


        //checking whether user is logged in or not
        if(sharedPreferencesLogin.getBoolean("logged",false)){
           Intent i = new Intent(this, MainActivity.class);
           startActivity(i);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 1) {
                    System.out.println("login by email");
                    login_type = "1";
                    loginCustomerByEmail();
                }
                else if(flag == 0)
                {
                    System.out.println("login by ph num");
                    login_type ="2";
                    loginCustomerByEmail();
                }
            }
        });



        ////////////////////////Gmail/////////////////////////

        gmail_button = findViewById(R.id.gmail_login_button);

        gmail_button.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestServerAuthCode(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        ////////////////////////Gmail End////////////////////////




        // getLocation();


    }



    /////////////////////////////////////////GMAIL Start////////////////////////////////////////////////////


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            String personName = acct.getDisplayName();
            //  String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String id = acct.getId();
            String token = acct.getIdToken();
            String auth = acct.getServerAuthCode();

            //login_id_value = id;

            System.out.println("Google tryyy " + personName);
            System.out.println("Google email " + email);
            System.out.println("Google token " + token);
            System.out.println("Google ID " + id);
            System.out.println("Google auth " + auth);


        }
        else {

        }
    }

    /////////////////////////////////////////GMAIL End////////////////////////////////////////////////////

    /////////////////////////////////////////GMAIL Start////////////////////////////////////////////////////
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Task<GoogleSignInAccount> gg = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(result);
            long statusCode = result.getStatus().getStatusCode();
            System.out.println("status code----------- " + statusCode);

           /* Intent i = new Intent(getApplication(), ProfileActivity.class );
            startActivity(i);*/
        }
       /* //fb
            else  if (resultCode == RESULT_OK) {
           *//* Intent secondActivityIntent = new Intent(this, HomeActivity.class);
            startActivity(secondActivityIntent);*//*
           callbackManager.onActivityResult(requestCode, resultCode, data);

            }*/
    }

    /////////////////////////////////////////GMAIL End////////////////////////////////////////////////////


    /////////////////////////////////////////GMAIL Start////////////////////////////////////////////////////
    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            //  showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //  hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.gmail_login_button:
                signIn();
                break;
           /* case R.id.fb_custom_button:
                loginButton.performClick();
                break;*/
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    /////////////////////////////////////////GMAIL End////////////////////////////////////////////////////

    public void loginCustomerByEmail() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
      //  apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        //Call<LoginCustomerOutput> call1 =  apiInterface.customerLogin("abc","123");

        LoginCustomerInput loginCustomerInput = new LoginCustomerInput();

        loginCustomerInput.setLoginName(emailId_editT.getText().toString());
        loginCustomerInput.setPassword(password_editT.getText().toString());
        loginCustomerInput.setLoginMethod(login_type);

      /*  id = emailId_editT.getText().toString();
        pswd = password_editT.getText().toString();*/

        //using sharedPreferences
       /* SharedPreferences.Editor editor = sharedPreferencesLogin.edit();
        editor.putString("user_id", id);
        editor.putString("user_pswd", pswd);
        editor.commit();


        System.out.println("Id======"+ id);
        System.out.println("pswd======"+pswd);*/


       // Call<LoginCustomerOutput> callLogin =  apiInterface.customerLogin(id,pswd);

        Call<LoginCustomerOutput> callLogin =  apiInterface.customerLogin(loginCustomerInput);

        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<LoginCustomerOutput>() {


            @Override
            public void onResponse(Call<LoginCustomerOutput> call, final Response<LoginCustomerOutput> response) {

              System.out.println("call====="+call);
                System.out.println("body" + response.body());
                int statusCode = response.code();

                System.out.println("Code" + statusCode);

                try {

                    //  if (response.code()==200 && (response.body().getData().getError()==null)) {
                    if (response.isSuccessful()) {
                       /* int statusCode = response.code();

                    System.out.println("Code" + statusCode);*/

                        System.out.println("body" + response.body().toString());

                        user_fullName = response.body().getData().getFullName();
                        System.out.println("name----" + user_fullName);

                        SharedPreferences.Editor user_name_editor = sharedPreferencesLogin.edit();
                        user_name_editor.putString("user_full_name", user_fullName);
                        user_name_editor.commit();

                        user_uuid = response.body().getData().getUuid();
                        user_unigue_id = user_uuid;

                        SharedPreferences.Editor editor = sharedPreferencesLogin.edit();
                        editor.putString("user_unique_id", user_uuid);
                        editor.commit();

                        System.out.println("name----" + user_uuid);

                        sharedPreferencesLogin.edit().putBoolean("logged", true).apply();

                        Intent i = new Intent(getApplication(), MainActivity.class);
                        startActivity(i);

                        //checking user logged in or not


                        Log.i(TAG, "login successful" + response);
                    }
                  /*  else if ( response.code() == 500 && response.body().getData().getError()!=null)
                    {

                        System.out.println("data----------+response.body().getData().getError()");
                        // emailId_TextInputlayout.setError(response.body().getData().getError());
                    }*/

                    else {
                        try {
                            String errorBodyMessage = response.errorBody().source().toString();
                            System.out.println("error msg----------" + errorBodyMessage);
                            if (!TextUtils.isEmpty(errorBodyMessage)) {
                                Gson gson = new Gson();
                                GeneralError genericErrorPojo = gson.fromJson(errorBodyMessage, GeneralError.class);

                                String errorMsg = genericErrorPojo.getError();
                                System.out.println("error msg----------" + errorMsg);
                                emailId_TextInputlayout.setError(errorMsg);

                            }
                        /*System.out.println("data----------+response.body().getData().getError()");
                         emailId_TextInputlayout.setError(response.body().getData().getError());
                        Log.i(TAG, "post not submitted to API." + response.errorBody());*/
                            //Toast.makeText(getApplicationContext(), "Unsuccess login+++++++++", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            System.out.println("Exception -----------" + e);
                        }
                    }
                }
                    catch (Exception e) {

                    e.printStackTrace();
                    System.out.println("Error----------"+e);
                }
                }


            @Override
            public void onFailure(Call<LoginCustomerOutput> call, Throwable t ) {
                Log.e(TAG, "Unable to submit post to login API.");

                //Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}








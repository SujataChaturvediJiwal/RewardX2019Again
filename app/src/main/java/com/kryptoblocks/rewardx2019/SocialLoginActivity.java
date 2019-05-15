package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
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
     EditText  emailId_editT,password_editT;
    Button gmail_button, login;
    private static final String TAG = SocialLoginActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
     static   String id, pswd ;
   public static  String  user_uuid;
    public static String user_fullName;
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

        text_sign_up_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), SignUpActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCustomer();
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
    }



    /////////////////////////////////////////GMAIL Start////////////////////////////////////////////////////


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

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

            Intent i = new Intent(getApplication(), ProfileActivity.class );
            startActivity(i);
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

    public void loginCustomer() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
      //  apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        //Call<LoginCustomerOutput> call1 =  apiInterface.customerLogin("abc","123");
         id = emailId_editT.getText().toString();
         pswd = password_editT.getText().toString();

        System.out.println("Id======"+ id);
        System.out.println("pswd======"+pswd);


        Call<LoginCustomerOutput> callLogin =  apiInterface.customerLogin(id,pswd);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<LoginCustomerOutput>() {


            @Override
            public void onResponse(Call<LoginCustomerOutput> call, Response<LoginCustomerOutput> response) {

              System.out.println("call====="+call);
                System.out.println("body" + response.body());
                int statusCode = response.code();

                System.out.println("Code" + statusCode);

                try {

                   if (response.code()==200) {

                       /* int statusCode = response.code();

                    System.out.println("Code" + statusCode);*/

                    System.out.println("body" + response.body().toString());

                       user_fullName = response.body().getData().getFullName();
                       System.out.println("name----" + user_fullName);

                       user_uuid = response.body().getData().getUuid();
                       System.out.println("name----" + user_uuid);

                        Intent i = new Intent(getApplication(), ProfileActivity.class);
                        startActivity(i);

                        Log.i(TAG, "login successful" + response);
                    }
                    else {
                        Log.i(TAG, "post not submitted to API." + response.errorBody());
                        Toast.makeText(getApplicationContext(), "Unsuccess login+++++++++", Toast.LENGTH_LONG).show();
                    }
               }
                    catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error----------"+e);
                }
                }


            @Override
            public void onFailure(Call<LoginCustomerOutput> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to login API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}








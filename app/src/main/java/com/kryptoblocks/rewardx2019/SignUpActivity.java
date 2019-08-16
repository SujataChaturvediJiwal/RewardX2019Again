package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_fullName;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

public class SignUpActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    TextView get_started;
   public TextInputLayout userName_register_textInputLayout, emailId_register_textInputLayout, phoneNumber_register_textInputLayout,
           password_register_textInputLayout, rePassword_register_textInputLayout;
    public EditText userName_register, emailId_register, password_register, rePassword_register, phone_number_register;
   static String passwordRegister, emailIdRegister;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesSignUp;
    String user_id_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        get_started = findViewById(R.id.get_started_button);

        userName_register_textInputLayout = findViewById(R.id.user_name_input_layout);
        emailId_register_textInputLayout = findViewById(R.id.email_id_input_layout);
        password_register_textInputLayout = findViewById(R.id.password_input_layout);
        rePassword_register_textInputLayout = findViewById(R.id.re_password_input_layout);
        phoneNumber_register_textInputLayout = findViewById(R.id.phoneNumber_input_layout);

        userName_register = findViewById(R.id.editText_userName_register);
        emailId_register = findViewById(R.id.editText_emailId_register);
        password_register = findViewById(R.id.editText_password_register);
        rePassword_register = findViewById(R.id.editText_rePassword_register);
        phone_number_register = findViewById(R.id.editText_phoneNumber_register);

        sharedPreferencesSignUp = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_uuid = sharedPreferencesSignUp.getString("user_unique_id","hi");

        //Toast.makeText(SignUpActivity.this, "User id:" + user_uuid , Toast.LENGTH_SHORT).show();

        //validatingData();
        //Toast.makeText(SignUpActivity.this, "Hi 1", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Hi 1------------" );


        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(SignUpActivity.this, "Hi 2", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Hi 2------------" );

                if(validateEmailId() && validateUserName()&& validatingPasswordEmpty() && validatePhoneNumber())
        {
            validatingPassword();

           // Toast.makeText(SignUpActivity.this, "Hi 3", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Hi 3------------" );

            CustomerRegistration();

        }
        else
        {
            //Toast.makeText(SignUpActivity.this, "Stay here", Toast.LENGTH_SHORT).show();
        }

            }
        });

///////////////trial
        //checking whether user is logged in or not
        if(sharedPreferencesSignUp.getBoolean("logged",false)){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void CustomerRegistration() {

        //tryyy/////////////

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
       // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        RegisterCustomerInput registerCustomerIn = new RegisterCustomerInput();

        registerCustomerIn.setFullName(userName_register.getText().toString());
        registerCustomerIn.setEmail(emailId_register.getText().toString());
        registerCustomerIn.setPassword(password_register.getText().toString());
        registerCustomerIn.setPhone(phone_number_register.getText().toString());


        System.out.println("UUID======"+emailId_register.getText().toString());
        System.out.println("Code======"+passwordRegister);

        System.out.println("Input code======"+ registerCustomerIn.getFullName());
        System.out.println("Input code======"+ registerCustomerIn.getEmail());
        System.out.println("Input uuid======"+registerCustomerIn.getPassword());

        //System.out.println("Input======"+redeemInput);


        Call<RegisterCustomerOutput> call1 = apiInterface.registerCustomer(registerCustomerIn);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<RegisterCustomerOutput>() {


            @Override
            public void onResponse(Call<RegisterCustomerOutput> call, Response<RegisterCustomerOutput> response) {

                if(response.code()== 200) {
                   // if (passwordRegister == rePassword_register.getText().toString()) {
                        int statusCode = response.code();

                        System.out.println("Code" + statusCode);

                        System.out.println("body" + response.body());

                    user_uuid = response.body().getData().getUuid();
                    System.out.println("registered user uuid++++++++++++++++++" + user_uuid);

                    user_fullName = response.body().getData().getFullName();
                    SharedPreferences.Editor editor = getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                    editor.putString("user_unique_id", user_uuid);
                    editor.apply();

                    SharedPreferences.Editor user_name_editor = getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                    user_name_editor.putString("user_full_name", user_fullName);
                    user_name_editor.commit();

                    sharedPreferencesSignUp.edit().putBoolean("logged",true).apply();

                    Intent i = new Intent(getApplication(), MainActivity.class);
                    startActivity(i);

                    /*Intent i = new Intent(getApplication(), ProfileActivity.class);
                    startActivity(i);*/
                 }

                    else if(response.code() == 300)
                    {
                        Log.i(TAG, "Email Id exists" + response);
                        //Toast.makeText(getApplicationContext(), "Email Id exists+++++++++", Toast.LENGTH_LONG).show();
                             }

                    else
                    {
                        Log.i(TAG, "post not submitted to API." + response);
                        //Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
            public void onFailure(Call<RegisterCustomerOutput> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
}
        private Boolean validatingPassword() {

             String user_name = userName_register.getText().toString();
             String emailId = emailId_register.getText().toString();
             String password = password_register.getText().toString();
             String re_password = rePassword_register.getText().toString();
             String phone_number = phone_number_register.getText().toString();

             if (password.equals(re_password)) {

                        /*Intent i = new Intent(getApplication(), ProfileActivity.class);
                        startActivity(i);*/
                        return true;

                    }
                        else
                    {
                        rePassword_register_textInputLayout.setError(getString(R.string.err_msg_password_match));
                       // password_register_textInputLayout.setError(getString(R.string.err_msg_password_empty));
                         password_register.getText().clear();
                          rePassword_register.setText("");
                          password_register.requestFocus();

                        //Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                   }

    private Boolean validatingPasswordEmpty() {

        String password = password_register.getText().toString();
        String re_password = rePassword_register.getText().toString();

        if(android.text.TextUtils.isEmpty(password))
        {
            Toast.makeText(SignUpActivity.this, "Empty password filed is not allowed", Toast.LENGTH_SHORT).show();
            rePassword_register_textInputLayout.setError(getString(R.string.err_msg_password_empty));
            rePassword_register_textInputLayout.requestFocus();
            return false;
        }
        else if(android.text.TextUtils.isEmpty(re_password))
        {
            rePassword_register_textInputLayout.setError(getString(R.string.err_msg_password_empty));
            rePassword_register_textInputLayout.requestFocus();
            return false;
        }

        else
        {
          //  rePassword_register_textInputLayout.setError(getString(R.string.err_msg_password_match));
           // password_register_textInputLayout.setError(getString(R.string.err_msg_password_empty));
            //password_register.getText().clear();
           // rePassword_register.setText("");
            //password_register.requestFocus();

           // Toast.makeText(SignUpActivity.this, "done", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

       private Boolean validateUserName()
       {
             String user_name = userName_register.getText().toString();
             if(android.text.TextUtils.isEmpty(user_name))
                 {
                        userName_register_textInputLayout.setError(getString(R.string.err_msg_username));
                        userName_register.requestFocus();
                        return false;
                 }
                else
                    {
                         userName_register_textInputLayout.setErrorEnabled(false);
                         }
                         return true;
                   }
    private Boolean validatePhoneNumber()
    {
        String phone_number = phone_number_register.getText().toString();
        if(android.text.TextUtils.isEmpty(phone_number))
        {
            phoneNumber_register_textInputLayout.setError(getString(R.string.err_msg_username));
            phoneNumber_register_textInputLayout.requestFocus();
            return false;
        }
        else
        {
            phoneNumber_register_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

        private Boolean validateEmailId()
       {
              String emailId = emailId_register.getText().toString();
             if(android.text.TextUtils.isEmpty(emailId))
                 {
                        emailId_register_textInputLayout.setError(getString(R.string.err_msg_emailId));
                     emailId_register_textInputLayout.requestFocus();
                        return false;
                 }
                else {
                 emailId_register_textInputLayout.setErrorEnabled(false);
                 return true;
             }
                   }

}
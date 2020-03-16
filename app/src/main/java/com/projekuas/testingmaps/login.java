package com.projekuas.testingmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn,SignUp;
    FirebaseAuth mFirebaseAuth;
    TextView home,mRecoverPassTv;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
   ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        //menghubungkan Firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignIn = findViewById(R.id.button2);
        mRecoverPassTv = findViewById ( R.id.recoveryPassTv );



        //skip langsung ke mneu home

        home = findViewById ( R.id.menu);
        home.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            //Membuka CardViewActivity
            public void onClick(View view) {
                openhome();
            }
        } );

        SignUp = findViewById ( R.id.signUp);
        SignUp.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            //Membuka CardViewActivity
            public void onClick(View view) {
                openRegisterActivity();
            }
        } );


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(login.this,"Selamat Cari Makan",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, home.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        //Foger Password
        mRecoverPassTv.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        } );


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(login.this,"Sabar Dong Makannya,Yuk Input Datanya",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(login.this,"Sepetinya Ada yang Salah Deh",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToHome = new Intent(login.this,home.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(login.this,"Aduh Sabar Dong,Masih Ada Yang Salah",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void showRecoverPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Atur Ulang Password" );
        // buat linear Layout
        LinearLayout linearLayout = new LinearLayout ( this );
        final EditText emailEt = new EditText ( this );
        emailEt.setHint ( "Email" );
        emailEt.setInputType ( InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS );
        emailEt.setMinEms ( 16 );

        linearLayout.addView ( emailEt );
        linearLayout.setPadding ( 10,10,10,10 );
        builder.setView ( linearLayout );

        //Button Recover
        builder.setPositiveButton ( "Reset Password" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialog , int which) {
                //masukan email sesuai variabel dalam ShowRecoveryPassword
                String email = emailEt.getText ().toString ().trim ();
                beginRecover(email);
            }
        } );

        //Button Cancel
        builder.setNegativeButton ( "Cancel" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialog , int which) {
                //masukan email sesuai variabel dalam ShowRecoveryPassword
                dialog.dismiss ();
            }
        } );

        //Menampilkan Dialog
        builder.create ().show ();
    }

    private void beginRecover(String email) {
        pd.setMessage ( "Mengirim Email" );
        pd.show ();
        mFirebaseAuth.sendPasswordResetEmail ( email ).addOnCompleteListener ( new OnCompleteListener<Void> ( ) {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss ();
                if ( task.isSuccessful ( ) ) {
                    Toast.makeText ( login.this,"Email Dikirim",Toast.LENGTH_SHORT ).show ();
                }
                else {
                    Toast.makeText ( login.this,"Gagal Broo...",Toast.LENGTH_SHORT).show ();
                }

            }
        } ).addOnFailureListener ( new OnFailureListener ( ) {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss ();
                Toast.makeText ( login.this,""+e.getMessage (),Toast.LENGTH_SHORT ).show ();

            }
        } );
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
        //Skipp langsung ke layout Menu CardView


    private void openRegisterActivity() {
        Intent intent = new Intent ( this,RegisterActivity.class );
        startActivity ( intent );
    }

    //menjalankan aksi menu skip langsu ke menu home

    private void openhome() {
        Intent intent = new Intent ( this,home.class );
        startActivity ( intent );

    }
}

package wdsc.ggateway.net.ggatewayfirebase04;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();

        final EditText userEd = (EditText) findViewById(R.id.usernameEd);
        final EditText passEd = (EditText) findViewById(R.id.passwordEd);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userEd.getText().toString();
                String password = passEd.getText().toString();
                Task<AuthResult> task = auth.signInWithEmailAndPassword(username,password);
                task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful()){
                             Toast.makeText(getApplicationContext(),"Unable  to login: "+task.getException(), Toast.LENGTH_LONG).show();
                         }else{
                             Toast.makeText(getApplicationContext(),auth.getCurrentUser().getEmail()+" login in successfully",Toast.LENGTH_LONG).show();

                         }
                    }
                });
            }
        });
    }
}

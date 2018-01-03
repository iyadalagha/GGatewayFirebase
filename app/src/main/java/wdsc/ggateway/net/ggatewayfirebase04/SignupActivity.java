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

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        final EditText userEd = (EditText) findViewById(R.id.usernameEd);
        final EditText passEd = (EditText) findViewById(R.id.passwordEd);

        Button signUpBtn = (Button) findViewById(R.id.signupBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEd.getText().toString().trim();
                String password = passEd.getText().toString().trim();
                Task<AuthResult> task = auth.createUserWithEmailAndPassword(username, password);
                task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Failed: "+task.getException(), Toast.LENGTH_LONG).show();
                        }else{
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),user.getEmail()+" Signed up successfully: ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}

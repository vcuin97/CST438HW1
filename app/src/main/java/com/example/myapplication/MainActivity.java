package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.model.User;
import com.example.myapplication.model.UserDao;
import com.example.myapplication.model.UserRoom;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView usrn;
    TextView pwrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = MainActivity2.getIntent(getApplicationContext(), 42);

        UserRoom.getUserRoom(this).loadData(this);
        final UserDao dao = UserRoom.getUserRoom(this).dao();

        Button login = findViewById(R.id.logIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<User> users = dao.getAllUsers();
                usrn = findViewById(R.id.UserN);
                pwrd = findViewById(R.id.Pwrd);
                boolean pass = false;
                boolean userRight = false;
                boolean passRight = false;

                final String username = usrn.getText().toString();
                final String password = pwrd.getText().toString();

                for(int i = 0; i < users.size(); i++){
                    if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())){
                        pass = true;
                        break;
                    }
                    if(username.equals(users.get(i).getUsername())){
                        //wrong pass
                        if(!password.equals(users.get(i).getPassword())){
                            passRight = true;
                            userRight = false;
                            break;

                        }
                    } else if(password.equals(users.get(i).getPassword())){
                        //wrong user
                        if(!username.equals(users.get(i).getUsername())){
                            userRight = true;
                            passRight = false;
                            break;
                        }
                    } else if(!password.equals(users.get(i).getPassword()) && !username.equals(users.get(i).getUsername())){
                        //both wrong
                        passRight = true;
                        userRight = true;
                    }
                }

                String u = username;
                String p = password;

                if(pass == false){
                    if(userRight && !passRight){
                        Toast.makeText(MainActivity.this, "Incorrect username", Toast.LENGTH_LONG).show();
                        usrn.setError("Invalid user name");
                    }else if(passRight && !userRight){
                        Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_LONG).show();
                        pwrd.setError("Invalid password");
                    }else if(userRight && passRight){
                        Toast.makeText(MainActivity.this, "Incorrect username and password", Toast.LENGTH_LONG).show();
                        usrn.setError("Invalid user name");
                        pwrd.setError("Invalid password");
                    }
                } else{
                    Intent intent = new Intent(MainActivity.this, LandingActivity.class);
                    Toast.makeText(MainActivity.this, "Welcome, " + username + "!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });


    }



}

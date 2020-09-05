package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.model.User;
import com.example.myapplication.model.UserDao;
import com.example.myapplication.model.UserRoom;

import java.util.List;
import java.util.Scanner;

public class Testing extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UserRoom.getUserRoom(this).loadData(this);
        final UserDao dao = UserRoom.getUserRoom(this).dao();
        List<User> users = dao.getAllUsers();

        AllUserstest1(users);
        UsernameTest2(users);
        PasswordTest3(users);
    }

    private void PasswordTest3(List<User> users) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = in.nextLine();

        for(int i = 0; i < users.size(); i++){
            if(password.equals(users.get(i).getPassword())){
                System.out.println("Password exists");
                break;
            }
        }
    }
    private void UsernameTest2(List<User> users) {
        for(int i = 0; i < users.size(); i++){
            if(users.equals(users.get(i).getUsername())){
                System.out.println("Username exists");
                break;
            }
        }
    }
    private void AllUserstest1(List<User> users) {
        for(int i = 0; i < users.size(); i++){
            System.out.println("username: " + users.get(i).getUsername() + " password: " + users.get(i).getPassword());
        }
    }
}

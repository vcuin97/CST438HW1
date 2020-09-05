package com.example.myapplication.model;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {User.class}, version = 1)
public abstract class UserRoom extends RoomDatabase {

    public abstract UserDao dao();
    //singleton
    private static UserRoom instance;

    public static UserRoom getUserRoom(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserRoom.class,
                    "UserDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public void loadData(Context context){
        List<User> user_list = UserRoom.getUserRoom(context).dao().getAllUsers();
        if(user_list.size() == 0) {
            Log.d("user", "Loading...");
            loadUsers(context);
        }
    }

    public void loadUsers(Context context){
        UserDao dao = getUserRoom(context).dao();

        User user1 = new User("joe", "mama");
        dao.addUser(user1);
        User user2 = new User("din_djarin", "baby_yoda_ftw");
        dao.addUser(user2);
        User user3 = new User("Scott", "Pilgrim");
        dao.addUser(user3);
        User user4 = new User("Victor", "C");
        dao.addUser(user4);
        Log.d("UserRoom", "4 users added to the database");

    }

}

package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.User;

import java.util.Calendar;

public class UpdateUserActivity extends AppCompatActivity {
    private User currentUser;
    private EditText name, email, dob, phone;
    private Button update;
    private ImageView timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        getSupportActionBar().hide();

        init();
        activity();
    }

    private void init() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        dob = findViewById(R.id.dob);
        update = findViewById(R.id.btnUpdate);
        timePicker = findViewById(R.id.timePicker);

        //get user from other form
        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("currentUser");
    }

    private void activity() {
        setCurrentUser();
        updateUserProfile();
        choseDob();
    }

    private void choseDob() {
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int d = c.get(Calendar.DAY_OF_MONTH);
                int m = c.get(Calendar.MONTH);
                int y = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateUserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int mm, int d) {
                        dob.setText(d + "/" + (mm+1) + "/" + y);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }

    private void setCurrentUser() {
        name.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhoneNumber());
        dob.setText(currentUser.getBirthday());
    }

    private void updateUserProfile() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateName = name.getText().toString().trim();
                String updateEmail = email.getText().toString().trim();
                String updatePhone = phone.getText().toString().trim();
                String updateDob = dob.getText().toString().trim();

                User updateUser = new User();
                updateUser.setId(currentUser.getId());
                updateUser.setUserName(currentUser.getUserName());
                updateUser.setPassWord(currentUser.getPassWord());
                updateUser.setName(updateName);
                updateUser.setEmail(updateEmail);
                updateUser.setBirthday(updateDob);
                updateUser.setPhoneNumber(updatePhone);

                Intent intent = getIntent();
                intent.putExtra("updateUser", updateUser);
                setResult(RESULT_OK, getIntent());

                UpdateUserTask task = new UpdateUserTask();
                task.execute(updateUser);
            }
        });
    }

    private class UpdateUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            db.userDao().updateUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(UpdateUserActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
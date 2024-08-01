package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.constants.Constants;
import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.executors.AppExecutors;
import com.example.myapplication.model.Person;

public class EditPersonActivity extends AppCompatActivity {
    private EditText edt_FirstName, edt_LastName;
    private Button btn_Save;
        private int mPersonId;

    private Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        mDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").build();

        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            btn_Save.setText("Update");
            mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = mDb.personDAO().loadPersonById(mPersonId);
                    populateUI(person);
                }
            });
        }
    }

    private void populateUI(Person person) {
        if (person == null) {
            return;
        }

        edt_FirstName.setText(person.getFirstName());
        edt_LastName.setText(person.getLastName());
    }

    private void initViews() {
        edt_LastName = (EditText) findViewById(R.id.editTextLastName);
        edt_FirstName = (EditText) findViewById(R.id.editTextFirstName);

        btn_Save = findViewById(R.id.buttonSave);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final Person person = new Person(
                edt_FirstName.getText().toString(),
                edt_LastName.getText().toString()
        );

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Person_Id)) {
                    mDb.personDAO().insert(person);
                } else {
                    person.setUid(mPersonId);
                    mDb.personDAO().update(person);
                }
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

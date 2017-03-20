package com.example.mdkamrul.trackwork;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WorkEntryActivity extends AppCompatActivity {
    EditText editTextWorkName,editTextDueDate,editTextDueDateTime;
    int year;
    int month;
    int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_entry);
        Initialize();
    }

    public void Initialize(){
        editTextWorkName= (EditText)findViewById(R.id.editTextWorkName);
        editTextDueDate = (EditText)findViewById(R.id.editTextDueDate);
        editTextDueDateTime = (EditText)findViewById(R.id.editTextDueDateTime);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        datePicker();
    }

    private void datePicker() {
        java.util.Calendar calender = java.util.Calendar.getInstance();
        year = calender.get(java.util.Calendar.YEAR);
        month = calender.get(java.util.Calendar.MONTH)+1;
        day = calender.get(java.util.Calendar.DAY_OF_MONTH);

        editTextDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDueDateTime.setVisibility(v.VISIBLE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(WorkEntryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int newMonth=month+1;
                        editTextDueDate.setText(dayOfMonth+"-"+newMonth+"-"+year);
                    }
                },day,month,year);
                datePickerDialog.setTitle("Select a Date");
                datePickerDialog.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donesettings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String num = ""+year;
        switch (item.getItemId()){
            case R.id.done:
                String workName = editTextWorkName.getText().toString();
                String dueDate = editTextDueDate.getText().toString();
                if (workName.trim().isEmpty()){
                    editTextWorkName.setError("Please insert Task");

                }
                else if (dueDate.trim().isEmpty()){
                    editTextDueDate.setError("No Date Selected");
                }

                else {

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("workName",workName);
                    intent.putExtra("dueDate",dueDate);
                    startActivity(intent);

                }
                return  true;
            case android.R.id.home:
                Intent intenthome = new Intent(this,MainActivity.class);
                startActivity(intenthome);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}

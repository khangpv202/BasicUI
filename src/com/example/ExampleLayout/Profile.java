package com.example.ExampleLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.ExampleLayout.customerActivity.CustomerListAdaper;
import com.example.ExampleLayout.customerActivity.Items;


import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/9/13
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Profile extends Activity
{
    private static String TAG = "Profile";
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 2;

    TextView tvHello;

    TextView tvAddress;
    TextView tvBirthDay;
    TextView tvOnline;
    TextView gender;
    Spinner addressPopup;
    ListView lvFriendList;
    CheckBox cbShowListFriends;
    RadioGroup rgGender;
    DatePicker dpDateBirthday;
    TimePicker tpTimeOnline;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvBirthDay = (TextView) findViewById(R.id.tvBirthDay);
        tvOnline = (TextView) findViewById(R.id.tvOnline);
        gender = (TextView) findViewById(R.id.tvGender);
        addressPopup = (Spinner) findViewById(R.id.spAddressList);
        lvFriendList = (ListView) findViewById(R.id.lvFriendLists);
        cbShowListFriends = (CheckBox) findViewById(R.id.cbShowListFriends);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        dpDateBirthday = (DatePicker) findViewById(R.id.dpDateBirthday);
        tpTimeOnline = (TimePicker) findViewById(R.id.tpTimeOnline);

        String userName = getIntent().getExtras().getString("userName");
        tvHello.setText("Hi " + userName);
        tvAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addressPopup.performClick();

            }
        });

        addressPopup.setOnItemSelectedListener(itemSelect);
        final ArrayList<Items> list = buildData();
        lvFriendList.setAdapter(new CustomerListAdaper(this, list));

        setCurrentDateOnView();
        setCurrentTimeOnView();

        cbShowListFriends.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cbShowListFriends.isChecked())
                {
                    lvFriendList.setVisibility(View.VISIBLE);
                    Log.d(TAG, "isChecked");

                }
                else
                {
                    lvFriendList.setVisibility(View.INVISIBLE);
                    //lvFriendList.setAdapter(null);
                    Log.d(TAG, "not checked");
                }
            }
        });
        gender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "gender is checked");
                rgGender.setVisibility(View.VISIBLE);
            }
        });

        rgGender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "radiobutton clicked " + rgGender.getCheckedRadioButtonId() + "");
            }
        });

        tvBirthDay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //dpDateBirthday.setVisibility(View.VISIBLE);
                showDialog(DATE_DIALOG_ID);
            }
        });
        tvOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDialog(TIME_DIALOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener()
    {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay)
        {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            tvBirthDay.setText(new StringBuilder().append("Birthday: ").append(day)
                    .append(":").append(month+1).append(":").append(year)
                    .append(" "));
            dpDateBirthday.init(year, month+1, day, null);

        }
    };
    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener()
            {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute)
                {
                    final Calendar c = Calendar.getInstance();
                    c.set(0,selectedHour,selectedMinute);
                    String am_pm = null;
                    if (c.get(Calendar.AM_PM) == Calendar.AM)
                        am_pm = "AM";
                    else if (c.get(Calendar.AM_PM) == Calendar.PM)
                        am_pm = "PM";
                    String strHrsToShow = (c.get(Calendar.HOUR) == 0) ?"12":c.get(Calendar.HOUR)+"";
                    tvOnline.setText(new StringBuilder().append("Online: ").append(selectedHour)
                            .append(":").append(selectedMinute).append(" ")
                            .append(am_pm));
                    // set current time into timepicker
                    //tpTimeOnline.setCurrentHour(hour);
                    //tpTimeOnline.setCurrentMinute(minute);

                }
            };

    private void setCurrentDateOnView()
    {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        tvBirthDay.setText(new StringBuilder().append("Birthday: ").append(day)
                .append(":").append(month+1).append(":").append(year)
                .append(" "));
    }

    private void setCurrentTimeOnView(){
        final Calendar c = Calendar.getInstance();
        String am_pm = null;
        if (c.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (c.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";
        String strHrsToShow = (c.get(Calendar.HOUR) == 0) ?"12":c.get(Calendar.HOUR_OF_DAY)+"";
        tvOnline.setText(new StringBuilder().append("Online: ").append(strHrsToShow)
                .append(":").append(c.get(Calendar.MINUTE)).append(" ")
                .append(am_pm));
        // set current date into datepicker

    }


    private ArrayList<Items> buildData()
    {
        ArrayList<Items> list = new ArrayList<Items>();
        list.add(new Items("Android", "Mobile"));
        list.add(new Items("Windows7", "Windows7"));
        list.add(new Items("iPhone", "iPhone"));
        return list;
    }

    private final AdapterView.OnItemSelectedListener itemSelect = new AdapterView.OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            tvAddress.setText("Address: " + addressPopup.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };
    public void logOut(View view){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.rbMale:
                if (checked)
                {
                    gender.setText("Gender: Male");
                    rgGender.setVisibility(View.GONE);
                    break;
                }
            case R.id.rbFemale:
                if (checked)
                {
                    gender.setText("Gender: Female");
                }
                rgGender.setVisibility(View.GONE);
                break;
        }
    }

}
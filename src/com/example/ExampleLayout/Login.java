package com.example.ExampleLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity
{
    /**
     * Called when the activity is first created.
     */
    private TextView userName = null;
    private Button login = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName= (TextView) findViewById(R.id.etUserName);
        login = (Button) findViewById(R.id.btLogin);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                i.putExtra("userName",userName.getText().toString());
                startActivity(i);

            }
        });
    }
}

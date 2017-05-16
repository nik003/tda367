package gruppnan.timeline.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gruppnan.timeline.R;

public class TestActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        configureToolbar();
        configureNavigationDrawer();
    }
}

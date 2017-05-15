package gruppnan.timeline.controller;

import android.os.Bundle;

import gruppnan.timeline.R;

public class TestActivity2 extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        configureToolbar();
        configureNavigationDrawer();
    }
}

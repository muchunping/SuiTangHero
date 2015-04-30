package cn.hero.suitanghero.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.hero.suitanghero.R;

public class ShellActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell);
    }

    public void signIn(View view) {
        startActivity(new Intent(view.getContext(), MainActivity.class));
    }
}

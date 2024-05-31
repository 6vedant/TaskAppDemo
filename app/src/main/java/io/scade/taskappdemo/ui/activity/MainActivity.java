package io.scade.taskappdemo.ui.activity;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.widget.Toast;

import io.scade.taskappdemo.R;
import io.scade.taskappdemo.adapter.TaskRecyclerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.fragment);

    }
}
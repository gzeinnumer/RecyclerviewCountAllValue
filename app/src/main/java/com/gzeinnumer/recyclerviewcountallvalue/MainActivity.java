package com.gzeinnumer.recyclerviewcountallvalue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gzeinnumer.recyclerviewcountallvalue.databinding.ActivityMainBinding;
import com.gzeinnumer.recyclerviewcountallvalue.databinding.ItemRvBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private CountDataAdapter adapter;

    private void initView() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter = new CountDataAdapter(getApplicationContext(), list);

        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rv.hasFixedSize();

        adapter.setOnFocusListener(isFocus -> {
            if (isFocus) countNow();
        });
    }

    private void countNow() {
        int[] countAll = new int[adapter.getHolders().size()];
        for (int i = 0; i < adapter.getHolders().size(); i++) {
            ItemRvBinding bind = adapter.getHolders().get(i);
            String s = bind.edData.getText().toString();
            if (s.length() > 0) {
                countAll[i] = Integer.parseInt(s);
            } else {
                countAll[i] = 0;
            }
        }

        int sum = 0;
        for (int value : countAll) sum += value;

        binding.edSum.setText(String.valueOf(sum));
    }
}
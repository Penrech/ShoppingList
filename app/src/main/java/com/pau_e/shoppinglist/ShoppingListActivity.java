package com.pau_e.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<String> items; // Model de dades
    private ShoppingListAdapter adapter;
    private EditText new_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        // Omplim el model de dades
        items = new ArrayList<>();
        items.add("Patatas");
        items.add("Paper WC");
        items.add("Ketchup");

        list = (ListView) findViewById(R.id.list);
        new_item = (EditText) findViewById(R.id.new_item);

        adapter = new ShoppingListAdapter(this, R.layout.shopping_item, items);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                onRemoveItem(pos);
                return true;
            }
        });


    }

    private void onRemoveItem(int pos) {
        items.remove(pos);
        adapter.notifyDataSetChanged();
    }

    public void OnAddItem(View view) {
        String item = new_item.getText().toString();
        items.add(item);
        adapter.notifyDataSetChanged();
        new_item.setText("");
        list.smoothScrollToPosition(items.size()-1);
    }
}

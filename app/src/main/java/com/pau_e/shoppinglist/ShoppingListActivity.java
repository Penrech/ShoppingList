package com.pau_e.shoppinglist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingListActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<ShoppingItem> items; // Model de dades
    private ShoppingListAdapter adapter;
    private EditText new_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        // Omplim el model de dades
        items = new ArrayList<>();
        items.add(new ShoppingItem("Patatas"));
        items.add(new ShoppingItem("Paper WC"));
        items.add(new ShoppingItem("Ketchup"));

        list = (ListView) findViewById(R.id.list);
        new_item = (EditText) findViewById(R.id.new_item);

        adapter = new ShoppingListAdapter(this, R.layout.shopping_item, items);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                items.get(pos).toggleChecked();
                adapter.notifyDataSetChanged();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                onRemoveItem(pos);
                return true;
            }
        });


    }

    private void onRemoveItem(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(
                String.format(Locale.getDefault(), "Estas segur que vols esborrar '%s'",
                        items.get(pos).getText())
        );
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                items.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();

    }

    public void OnAddItem(View view) {
        String item = new_item.getText().toString();
        if (!item.isEmpty()) {
            items.add(new ShoppingItem(item));
            adapter.notifyDataSetChanged();
            new_item.setText("");
            list.smoothScrollToPosition(items.size() - 1);
        }
    }
}

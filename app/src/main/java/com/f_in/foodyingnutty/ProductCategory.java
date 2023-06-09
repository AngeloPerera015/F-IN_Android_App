package com.f_in.foodyingnutty;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.f_in.foodyingnutty.Adapters.CustomExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//this is to create the user from the main activity image button to select food products from the expandable list and view selected product ingredients and nutrition from the dialog box
public class ProductCategory extends AppCompatActivity {
    //declare variables
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        //show the expandable list headers and child list
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " list expanded.", Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " list collapsed.", Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final String selected = (String) expandableListAdapter.getChild(groupPosition, childPosition);
                //show selected product ingredients and nutrition from the relevant xml file
                Dialog dialog;
                switch (selected) {
                    case "Munchee Super Cream Cracker - 190g":
                        dialog = new Dialog(ProductCategory.this, R.style.DialogStyle);
                        dialog.setContentView(R.layout.munchee_super_cream_cracker_190g);
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
                        dialog.show();
                        break;
                    case "Revello Cashew - 170g":
                        dialog = new Dialog(ProductCategory.this, R.style.DialogStyle);
                        dialog.setContentView(R.layout.revello_cashew_170g);
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
                        dialog.show();
                        break;
                }
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
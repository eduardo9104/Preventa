package com.systematic.preventa;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.systematic.preventa.DataBase.ProductDB;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.Fragment.PedidosFragment;
import com.systematic.preventa.Fragment.ProductFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //llamar fragmento
                    callFragmentProduct();
                    return true;
                case R.id.navigation_carshop:
                    callFragmentCarShop();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillDataBase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void callFragmentProduct(){

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProductFragment productFragment = new ProductFragment();

        fragmentTransaction.replace(R.id.loadingPanel, productFragment.newProductionLine("1", false,pedidos,products));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void callFragmentCarShop(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.loadingPanel, PedidosFragment.newInstance(1,pedidos));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void fillDataBase(){
        for (int i = 0; i < 5; i++) {
            this.products.add(ProductDB.getRandomProduct());
        }
    }

}

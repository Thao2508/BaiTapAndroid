package com.example.baitapcuoi_ntt;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kết nối các view với mã
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Setup ViewPager và BottomNavigationView
        setupViewPager();

        // Cài đặt sự kiện chuyển đổi giữa các trang và mục menu trong BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (itemId == R.id.menu_notice) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (itemId == R.id.menu_acount) {
                    viewPager.setCurrentItem(2);
                    return true;
                }

                return false;
            }
        });

        // Đảm bảo rằng khi trang thay đổi, BottomNavigationView được cập nhật
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Không cần xử lý
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_notice);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_acount);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Không cần xử lý
            }
        });
    }

    private void setupViewPager() {
        // Tạo adapter cho ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        // Cài đặt vị trí trang mặc định
        int tabPosition = 0;
        if (getIntent() != null) {
            tabPosition = getIntent().getIntExtra("tabPosition", 0);
        }
        viewPager.setCurrentItem(tabPosition);
    }
}

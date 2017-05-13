package com.untaek.oneroom.act;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.untaek.oneroom.R;
import com.untaek.oneroom.format.ListTitleFormat;
import com.untaek.oneroom.rest.UserAuthService;
import com.untaek.oneroom.utility.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public UserAuthService.UserInfo logined = null;
    static private boolean recreated = false;
    static public final int LOGIN_REQUEST = 100;

    TabLayout tabLayout = null;
    ViewPager viewPager = null;
    MainPagerAdapter pagerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(logined != null){
            if(!recreated){
                this.recreate();
                recreated = !recreated;
            }
        }
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {
        private MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MainFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0) return "방보기";
            else if(position==1) return "게시판";
            else if (position==2) return "로그인";
            return super.getPageTitle(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);

        }
    }

    static public class MainFragment extends Fragment {
        int mNum;
        Button button_login = null;
        TextView textView_login = null;

        static public MainFragment getInstance(int num){
            MainFragment fragment = new MainFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View displayView = null;
            switch (mNum){
                case 0:
                    displayView = inflater.inflate(R.layout.fragment_room, container, false);
                    displayView.findViewById(R.id.button_go_room_find_map).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), RoomFindMapActivity.class);
                            startActivity(intent);
                        }
                    });

                    displayView.findViewById(R.id.button_go_room_board).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), RoomBoardActivity.class);
                            startActivity(intent);
                        }
                    });

                    displayView.findViewById(R.id.button_go_room_sell).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), RoomRegisterListActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
                case 1:
                    int layout;
                    if(logined == null){
                        layout = R.layout.fragment_require_login;
                        displayView = inflater.inflate(layout, container, false);
                    }
                    else{
                        layout = R.layout.fragment_board;
                        displayView = inflater.inflate(layout, container, false);
                        displayView.findViewById(R.id.button_go_board).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), Board1Activity.class);
                                startActivity(intent);
                            }
                        });
                        ArrayList<ListTitleFormat> list = new ArrayList<>();
                        ListTitleFormat format = new ListTitleFormat();
                        format.setTitle("제목제목제목제목");
                        format.setAuthor("작성자작성자");
                        format.setDate("30분 전");
                        list.add(format);list.add(format);list.add(format);list.add(format);list.add(format);

                        ListView listView_popular = (ListView) displayView.findViewById(R.id.listView_popularBoard);
                        final ListAdapter adapter = new ListAdapter(list, getContext());
                        listView_popular.setAdapter(adapter);

                        listView_popular.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ListTitleFormat data = (ListTitleFormat) adapter.getItem(position);
                                Intent intent = new Intent(getContext(), BoardPostActivity.class);
                                intent.putExtra(ListTitleFormat.BUNDLE, data);
                                startActivity(intent);
                            }
                        });
                    }
                    break;
                case 2:
                    displayView = inflater.inflate(R.layout.fragment_login, container, false);
                    button_login = (Button) displayView.findViewById(R.id.button_go_login);
                    textView_login = (TextView) displayView.findViewById(R.id.textView_please_login);

                    button_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivityForResult(new Intent(getContext(), LoginActivity.class), LOGIN_REQUEST);
                        }
                    });
                    break;
            }
            return displayView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == LOGIN_REQUEST){
                if(resultCode == RESULT_OK){
                    getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                    Toast.makeText(getContext(), "onResult : " + this.mNum, Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            if(mNum==2){
                if(logined != null){
                    textView_login.setText(logined.getNick_name() + "님 안녕하세요");

                }else{
                    textView_login.setText("로그인 해주세요");
                }
            }
        }
    }
}

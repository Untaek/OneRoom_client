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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.untaek.oneroom.R;
import com.untaek.oneroom.format.ListTitleFormat;
import com.untaek.oneroom.rest.RetrofitManager;
import com.untaek.oneroom.rest.UserService;
import com.untaek.oneroom.utility.BoardListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public UserService.UserInfo logined = null;
    static public UserService.Prefer prefer = null;
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
            else if (position==2){
                if(logined != null) return "회원 정보";
                else return "로그인";
            }
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
                            Intent intent = new Intent(getContext(), RoomPostDetailActivity.class);
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

                    displayView.findViewById(R.id.button_go_posted_rooms).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(), RoomMyPostListActivity.class);
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
                        final BoardListAdapter adapter = new BoardListAdapter(list, getContext());
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
                    if(logined==null){
                        displayView = inflater.inflate(R.layout.fragment_login, container, false);
                        Button button_login = (Button) displayView.findViewById(R.id.button_go_login);
                        TextView textView_login = (TextView) displayView.findViewById(R.id.textView_please_login);
                        textView_login.setText("로그인 해주세요");
                        button_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivityForResult(new Intent(getContext(), LoginActivity.class), LOGIN_REQUEST);
                            }
                        });
                    }
                    else{
                        displayView = inflater.inflate(R.layout.fragment_user, container, false);
                        TextView textView_user_hello = (TextView) displayView.findViewById(R.id.textView_user_hello);
                        TextView textView_user_univ = (TextView) displayView.findViewById(R.id.textView_user_univ);
                        Button button_logout = (Button) displayView.findViewById(R.id.button_logout);
                        textView_user_hello.setText(logined.getNick_name() + "님 안녕하세요");
                        textView_user_univ.setText(logined.getUniversity());
                        button_logout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                logined = null;
                                getActivity().recreate();
                            }
                        });

                        final CheckBox checkBox_monthly = (CheckBox) displayView.findViewById(R.id.checkBox_prefer_monthly);
                        final CheckBox checkBox_yearly = (CheckBox) displayView.findViewById(R.id.checkBox_prefer_yearly);
                        final EditText editText_min_cost = (EditText) displayView.findViewById(R.id.editText_prefer_min_cost);
                        final EditText editText_max_cost = (EditText) displayView.findViewById(R.id.editText_prefer_max_cost);
                        final EditText editText_size = (EditText) displayView.findViewById(R.id.editText_prefer_size);
                        final CheckBox checkBox_parking = (CheckBox) displayView.findViewById(R.id.checkBox_prefer_parking);
                        final CheckBox checkBox_available = (CheckBox) displayView.findViewById(R.id.checkBox_prefer_available);
                        Button button_prefer_confirm = (Button) displayView.findViewById(R.id.button_prefer_confirm);

                        checkBox_monthly.setChecked(prefer.getContract_type().equals("BO") || prefer.getContract_type().equals("MO"));
                        checkBox_yearly.setChecked(prefer.getContract_type().equals("BO") || prefer.getContract_type().equals("YR"));
                        editText_min_cost.setText(prefer.getMin_cost()==0 ? "" : String.valueOf(prefer.getMin_cost()));
                        editText_max_cost.setText(prefer.getMax_cost()==0 ? "" : String.valueOf(prefer.getMax_cost()));
                        checkBox_parking.setChecked(prefer.isParking());
                        checkBox_available.setChecked(prefer.isAvailable());

                        button_prefer_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UserService.Prefer prefer = new UserService.Prefer(logined.getId());
                                if(checkBox_monthly.isChecked()&&checkBox_monthly.isChecked()){

                                }
                                prefer.setContract_type(
                                         checkBox_monthly.isChecked() ?
                                        (checkBox_yearly.isChecked() ? "BO" : "MO") :
                                        (checkBox_yearly.isChecked() ? "YR" : "NO"));

                                prefer.setMin_cost(editText_min_cost.getText().toString().equals("") ? 0 : Integer.parseInt(editText_min_cost.getText().toString()));
                                prefer.setMax_cost(editText_max_cost.getText().toString().equals("") ? 0 : Integer.parseInt(editText_max_cost.getText().toString()));
                                prefer.setSize(editText_size.getText().toString().equals("") ? 0 : Integer.parseInt(editText_size.getText().toString()));
                                prefer.setParking(checkBox_parking.isChecked());
                                prefer.setAvailable(checkBox_available.isChecked());
                                RetrofitManager.getInstance().updatePrefer(prefer, getActivity());
                            }
                        });
                    }
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
                    getActivity().recreate();
                }
            }
        }
    }
}

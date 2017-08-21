package com.test.coordinatordemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
    public class CurrencyFragment extends android.support.v4.app.Fragment {

        public static final String ARG_PAGE = "ARG_PAGE";
        private String ttile;
        private List<String> lists;

        public static CurrencyFragment newInstance(String title) {
            Bundle args = new Bundle();
            args.putString(ARG_PAGE, title);
            CurrencyFragment pageFragment = new CurrencyFragment();
            pageFragment.setArguments(args);
            return pageFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ttile = getArguments().getString(ARG_PAGE);
        }


        public List<String> getListData() {
            List<String> list=new ArrayList<>();
            for (int i=0;i<100;i++)
            {
                list.add("item"+i);
            }
            return list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_page, container, false);

            lists = getListData();
            RecyclerView recycler  = (RecyclerView) view.findViewById(R.id.recycler);


            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(new RecyclerView.Adapter() {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    TextView textView=new TextView(parent.getContext());
                    MyViewHolder viewHolder=new MyViewHolder(textView);
                    return viewHolder;
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                    MyViewHolder myViewHolder=   (MyViewHolder)holder  ;
                    ((TextView)myViewHolder.itemView).setText(lists.get(position));
                }

                @Override
                public int getItemCount() {
                    return lists.size();
                }
            });
            return view;
        }
        public  class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }
}

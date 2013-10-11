package com.example.ExampleLayout.customerActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.example.ExampleLayout.Profile;
import com.example.ExampleLayout.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/10/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerListAdaper extends BaseAdapter
{
    private ArrayList<Items> listData;

    private LayoutInflater layoutInflater;

    public CustomerListAdaper(Context context, ArrayList<Items> listData)
    {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return listData.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int position)
    {
        return listData.get(position);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position)
    {
        return position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
         if(convertView ==null){
             convertView= layoutInflater.inflate(R.layout.row_layout,null);
             holder = new ViewHolder();
             holder.nameView = (TextView) convertView.findViewById(R.id.tvName);
             holder.phoneView = (TextView) convertView.findViewById(R.id.tvPhoneNumber);
             convertView.setTag(holder);
         }                            else{
             holder= (ViewHolder) convertView.getTag();
         }
        holder.nameView.setText(listData.get(position).getName());
        holder.phoneView.setText(listData.get(position).getPhoneNumber());

        return convertView;  //To change body of implemented methods use File | Settings | File Templates.
    }

    static class ViewHolder{
        TextView nameView;
        TextView phoneView;
    }
}

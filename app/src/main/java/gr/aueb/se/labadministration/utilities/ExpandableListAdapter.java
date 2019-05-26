package gr.aueb.se.labadministration.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.fragments.TerminalFragment;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader; // contains the main categories e.x. lab1, lab2, lab3 etc.
    private HashMap<String, List<String>> listHashMap; // connects each main category with her items e.x. String lab1, List<String> computer_101, computer_102 etc.

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item, i1 = Children item
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group, null);
        }
        TextView listHeader = view.findViewById(R.id.listHeader);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String)getChild(i, i1);
        System.out.println(childText);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        TextView listItem = view.findViewById(R.id.listItem);
        listItem.setText(childText);
        listItem.setOnClickListener(v ->  {
            // TODO code to show each computer elements, mabe in Fragment
            //listItem.setText("TODO: go to Fragment(ExpandableListAdapter)");
            FragmentTransaction manager = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            TerminalFragment terminalFragment = new TerminalFragment();
            Bundle bundle = new Bundle();
            bundle.putString("terminal_name", childText);
            terminalFragment.setArguments(bundle);
            manager.add(terminalFragment, "Terminal");
            manager.commit();

        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}

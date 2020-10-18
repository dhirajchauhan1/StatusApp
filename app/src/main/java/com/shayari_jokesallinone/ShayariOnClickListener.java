package com.shayari_jokesallinone;

import com.shayari_jokesallinone.RecyclerPackage.DbModelClass;

import java.util.ArrayList;
import java.util.List;

public interface ShayariOnClickListener {
    void onClicked(int position, List<DbModelClass> data);
}

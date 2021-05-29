package com.example.qlsv.DAO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.qlsv.Model.PolyClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PolyClassDAO implements DAO<PolyClass> {
    List<PolyClass> classList = new ArrayList<>();
    @Override
    public List<PolyClass> getAll() {
        return classList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<PolyClass> get(String id) {
        return classList.stream().filter(polyClass -> polyClass.getId().equals(id)).findFirst();
    }

    @Override
    public void save(PolyClass polyClass) {
        classList.add(polyClass);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update(PolyClass polyClass) {
        get(polyClass.getId()).ifPresent(existClass ->{
            existClass.setId(polyClass.getId());
            existClass.setName(polyClass.getId());
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void delete(PolyClass polyClass) {
        get(polyClass.getId()).ifPresent(existClass ->{
            classList.remove(existClass);
        });
    }
}

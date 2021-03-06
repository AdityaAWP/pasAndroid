package com.example.ptsfootballdb;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final modelrealm footballModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(modelrealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    footballModel.setId(nextId);
                    modelrealm model = realm.copyToRealm(footballModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<modelrealm> getAllMovie(){
        RealmResults<modelrealm> results = realm.where(modelrealm.class).findAll();
        return results;
    }
    public void delete(Integer id){
        final RealmResults<modelrealm> model = realm.where(modelrealm.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}

package adPortal.storage.impl;

import adPortal.model.Advertisment;
import adPortal.model.Category;
import adPortal.model.User;
import adPortal.storage.AdStorage;
import adPortal.util.FileUtil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AdStorageFileImpl implements AdStorage {

    private List<Advertisment> adList;
    public AdStorageFileImpl(){
        adList = FileUtil.deserializeAdList();
    }

    @Override
    public void add(Advertisment ad) {
        adList.add(ad);
        FileUtil.serializeAdList(adList);

    }

    @Override
    public void printMyAds(User user) {
        for (Advertisment ad : adList) {
            if (ad.getUser().equals(user)){
                System.out.println(ad);
            }
        }

    }

    @Override
    public void printAllAds() {

        System.out.println(adList);
    }

    @Override
    public void printByCategory(Category category) {
        for (Advertisment ad : adList) {
            if (ad.getCategory() == category){
                System.out.println(ad);
            }
        }
    }

    @Override
    public void deleteMyAds(User user) {
        for (Advertisment ad : adList) {
            if (ad.getUser().equals(user)){
                adList.remove(ad);
            }
        }
        FileUtil.serializeAdList(adList);
    }

    @Override
    public void deleteAdByTitle(String title, User currentUser) {
        Iterator<Advertisment> iterator = adList.iterator();
        while (iterator.hasNext()){
            Advertisment ad = iterator.next();
            if (ad.getTitle().equals(title) && ad.getUser().equals(currentUser)){
                iterator.remove();
            }
        }
        FileUtil.serializeAdList(adList);
    }
}

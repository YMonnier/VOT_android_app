package pm12016g3.tln.univ.fr.vot.models.network;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.network.
 * File ResList.java.
 * Created by Ysee on 01/06/2017 - 11:51.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class ResList<T>
        implements List<T>, Requestable {
    private List<T> items = new ArrayList<T>();
    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(@NonNull T1[] t1s) {
        return items.toArray(t1s);
    }

    @Override
    public boolean add(T t) {
        return items.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return items.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> collection) {
        return items.addAll(collection);
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends T> collection) {
        return items.addAll(i, collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return items.removeAll(collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return items.retainAll(collection);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public T get(int i) {
        return items.get(i);
    }

    @Override
    public T set(int i, T t) {
        return items.set(i, t);
    }

    @Override
    public void add(int i, T t) {
        items.add(i, t);
    }

    @Override
    public T remove(int i) {
        return items.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return items.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return items.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return items.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator(int i) {
        return items.listIterator(i);
    }

    @NonNull
    @Override
    public List<T> subList(int i, int i1) {
        return items.subList(i, i1);
    }
}

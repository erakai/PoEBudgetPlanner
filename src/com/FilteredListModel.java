package com;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

//stack overflow
//i understand how this works but being able to write it is another thing

public class FilteredListModel extends AbstractListModel {
    public static interface Filter {
        boolean accept(Object element);
    }

    private final DefaultListModel _source;
    private Filter _filter;
    private final ArrayList<Integer> _indices = new ArrayList<Integer>();

    public FilteredListModel(DefaultListModel source) {
        if (source == null)
            throw new IllegalArgumentException("Source is null");
        _source = source;
        _source.addListDataListener(new ListDataListener() {
            public void intervalRemoved(ListDataEvent e) {
                doFilter();
            }

            public void intervalAdded(ListDataEvent e) {
                doFilter();
            }

            public void contentsChanged(ListDataEvent e) {
                doFilter();
            }
        });
    }

    public void setFilter(Filter f) {
        _filter = f;
        doFilter();
    }

    private void doFilter() {
        _indices.clear();

        Filter f = _filter;
        if (f != null) {
            int count = _source.getSize();
            for (int i = 0; i < count; i++) {
                Object element = _source.getElementAt(i);
                if (f.accept(element)) {
                    _indices.add(i);
                }
            }
            fireContentsChanged(this, 0, getSize() - 1);
        }
    }

    public void addElement(Object element) {
        int index = _indices.size();
        _source.addElement(element);
        fireIntervalAdded(this, index, index);
    }

    public void clear() {
        _source.clear();
    }

    public int getSize() {
        return (_filter != null) ? _indices.size() : _source.getSize();
    }

    public Object getElementAt(int index) {
        return (_filter != null) ? _source.getElementAt(_indices.get(index)) : _source.getElementAt(index);
    }

}

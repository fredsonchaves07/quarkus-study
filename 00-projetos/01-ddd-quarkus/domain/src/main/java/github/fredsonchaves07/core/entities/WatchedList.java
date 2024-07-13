package github.fredsonchaves07.core.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class WatchedList<T> {

    private List<T> currentItems;

    private List<T> initial;

    private List<T> removedItems;

    private List<T> newItems;

    protected WatchedList(List<T> initialItems) {
        this.currentItems = initialItems;
        this.initial = initialItems;
        this.newItems = new ArrayList<>();
        this.removedItems = new ArrayList<>();
    }

    public abstract boolean compareItems(T a, T b);

    public List<T> currentItems() {
        return new ArrayList<>(currentItems);
    }

    public List<T> newItems() {
        return new ArrayList<>(newItems) ;
    }

    public List<T> removedItems() {
        return new ArrayList<>(removedItems) ;
    }

    private boolean isCurrentItem(T item) {
        return !this.currentItems.stream().filter(i -> compareItems(item, i)).toList().isEmpty();
    }

    private boolean isNewItem(T item) {
        return !this.newItems.stream().filter(i -> compareItems(item, i)).toList().isEmpty();
    }

    private boolean isRemovedItem(T item) {
        return !this.removedItems.stream().filter(i -> compareItems(item, i)).toList().isEmpty();
    }

    private void removeFromNew(T item) {
        this.newItems = this.removedItems.stream().filter(i -> !compareItems(item, i)).toList();
    }

    private void removeFromCurrent(T item) {
        this.currentItems = this.currentItems.stream().filter(i -> !compareItems(item, i)).toList();
    }

    private void removeFromRemoved(T item) {
        this.removedItems = this.removedItems.stream().filter(i -> !compareItems(item, i)).toList();
    }

    private boolean wasAddedInitially(T item) {
        return !this.initial.stream().filter(i -> compareItems(item, i)).toList().isEmpty();
    }

    public boolean exists(T item) {
        return this.currentItems.contains(item);
    }

    public void add(T item) {
        if (isRemovedItem(item)) removeFromRemoved(item);
        if (!isNewItem(item) && !wasAddedInitially(item)) newItems.add(item);
        if (!isCurrentItem(item)) currentItems.add(item);
    }

    public void remove(T item) {
        removeFromCurrent(item);
        if (isNewItem(item)) {
            removeFromNew(item);
            return;
        }
        if (!isRemovedItem(item)) {
            removedItems.add(item);
        }
    }

    public void update(List<T> items) {
        newItems = items.stream()
                .filter(a -> currentItems.stream().noneMatch(b -> compareItems(a, b)))
                .toList();
        removedItems = currentItems.stream()
                .filter(a -> items.stream().noneMatch(b -> compareItems(a, b)))
                .toList();
        currentItems = items;
    }
}

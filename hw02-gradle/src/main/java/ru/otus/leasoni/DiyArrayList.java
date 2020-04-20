package ru.otus.leasoni;
import java.util.*;
import java.util.function.UnaryOperator;

public class DiyArrayList<T> implements List<T> {

    private Object[] tArray;
    private int size;
    private final Object[] DEFAULT_T_ARRAY = {};
    private static final int DEFAULT_CAPACITY = 10;
    public static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    public DiyArrayList() {
        this.tArray = DEFAULT_T_ARRAY;
    }

    public DiyArrayList(int initialCapacity) {
        this.tArray = new Object[initialCapacity];
    }

    public DiyArrayList(Object[] tArray) {
        this.tArray = tArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (size == tArray.length) {
            tArray = grow();
        }
        tArray[size] = t;
        size = size + 1;
        return true;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = tArray.length;
        if (oldCapacity > 0 || tArray != DEFAULT_T_ARRAY) {
            int newCapacity = 0;
            int minLength = oldCapacity + (minCapacity - oldCapacity);
            int newLength = Math.max(oldCapacity, oldCapacity >> 1) + oldCapacity;
            if (newLength - MAX_ARRAY_LENGTH <= 0) {
                newCapacity =  newLength;
            }
            else {
                if(minLength < 0) {
                    throw new OutOfMemoryError("Required array length too large");
                }
                if (minLength <= MAX_ARRAY_LENGTH) {
                    newCapacity =  MAX_ARRAY_LENGTH;
                }
                newCapacity = Integer.MAX_VALUE;
            }
            return tArray = Arrays.copyOf(tArray, newCapacity);
        } else {
            return tArray = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return tArray(index);
    }

    @SuppressWarnings("unchecked")
    T tArray(int index) {
        return (T) tArray[index];
    }

    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T value = tArray(index);
        tArray[index] = element;
        return value;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DiyListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        rangeCheckForAdd(index);
        return new DiyListItr(index);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "+ size);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) tArray, 0, size, c);
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    private class DiyListItr extends DiyItr implements ListIterator<T> {

        DiyListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] tArray = DiyArrayList.this.tArray;
            if (i >= tArray.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) tArray[lastRet - 1];
        }

        @Override
        public void set(T t) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                DiyArrayList.this.set(lastRet, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(T t) {
            try {
                int i = cursor;
                DiyArrayList.this.add(i, t);
                cursor = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }
    }

    private class DiyItr {
        int cursor;
        int lastRet = -1;


        DiyItr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] tArray = DiyArrayList.this.tArray;
            if (i >= tArray.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) tArray[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                DiyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }

}

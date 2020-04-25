HOMEWORK02

Target: get to know how ArrayList is omplemented in Java, try to create your own Collection

1) Create your own class DIYarrayList<T> implements List<T>{...}
2) Check that several Collections methods works on your collection
    * Collections.addAll(Collection<? super T> c, T... elements)
    * Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
    * Collections.static <T> void sort(List<T> list, Comparator<? super T> c)
3) Check on collection with more than 20 elements
4) DIYarrayList MUST implement only List<T> interface
5) If method is not implemented it should throw UnsupportedOperationException 
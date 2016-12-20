package group2.grade15.njuse.cache;

public class Cache {
    private String key;
    private Object element;

    public Cache(String key, Object element){
        this.key = key;
        this.element = element;
    }

    public String getKey(){
        return key;
    }

    public Object getElement(){
        return element;
    }
}

import java.util.List;

public class MyObject {
    private String name;
    private MyObject related;
    private List<MyObject> children;
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public MyObject getRelated() {
        return this.related;
    }
    public void setRelated(MyObject related) {
        this.related = related;
    }
    
    public List<MyObject> getChildren() {
        return this.children;
    }
    public void setChildren(List<MyObject> children) {
        this.children = children;
    }
}
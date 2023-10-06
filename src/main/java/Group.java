import java.util.List;

public class Group {
    private String groupName;
    private List<Contact> members;

    public Group(String name, List<Contact> members) {
        this.groupName = name;
        this.members = members;
    }
}

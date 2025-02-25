package Person;

import lombok.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class DataForTable<T> {
    private String tableName;
    private Method[] methods;
    private List<String> columns;
    private Field[] fields;
    private T object;

    public DataForTable(T object) {
        this.object = object;
    }

    public String receiveTableName() {
        return object.getClass().getDeclaredAnnotation(MyTable.class).name();
    }

    public Field[] receiveFields() {
        return object.getClass().getDeclaredFields();
    }

    public Method[] receiveMethods() {
        return object.getClass().getMethods();
    }

    public List<Method> receiveGetters() {
        List<Method> getters = new LinkedList<>();
        Method[] methods = getMethods();
        for (Method method :
                methods) {
            if (method.getName().compareTo("getId") == 0 || method.getName().compareTo("getClass") == 0) {
                continue;
            }
            if (method.getName().matches("get\\w*")) {
                getters.add(method);
            }
        }
        return getters;
    }

    public List<String> receiveColumnsName() {
        Field[] fields = receiveFields();
        List<String> list = new ArrayList<>();
        for (Field field :
                fields) {

            MyColumn myColumn = field.getAnnotation(MyColumn.class);
            list.add(myColumn.name());

        }
        return list;
    }

    public void updateInfoInData() {
        this.setTableName(this.receiveTableName());
        this.setMethods(this.receiveMethods());
        this.setColumns(this.receiveColumnsName());
        this.setFields(this.receiveFields());
    }
}

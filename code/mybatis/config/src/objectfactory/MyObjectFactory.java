package objectfactory;

        import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

        import java.util.List;
        import java.util.Properties;

/**
 * Created by ixfosa on 2021/3/24 17:31
 */
public class MyObjectFactory extends DefaultObjectFactory {

    private Object temp = null;

    @Override
    public <T> T create(Class<T> type) {
        T result = super.create(type);
        System.out.println("创建对象：" + result.toString());
        System.out.println("是否和上次创建的是同一个对象：【" + (temp == result) + "】");
        return result;
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        T result = super.create(type, constructorArgTypes, constructorArgs);
        System.out.println("创建对象：" + result.toString());
        temp = result;
        return result;
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        System.out.println("初始化参数：【" + properties.toString() + "】");
    }
}

package injector;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class injecto
 *
 * @author Alex Lozovoy
 */

@Configuration(packages = {"repository", "validators"})
public class Injector  {
    public static <T> T inject(T object) throws InjectionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                Type suitableType;
                if (!Collection.class.isAssignableFrom(fieldType)) {
                    suitableType = field.getType();
                } else {
                    suitableType = field.getGenericType();
                    ParameterizedType aType = (ParameterizedType) suitableType;
                    Type[] fieldArgTypes = aType.getActualTypeArguments();
                    suitableType = fieldArgTypes[0];
                }
                String[] packages = Injector.class.getAnnotation(Configuration.class).packages();
                List<Class<?>> suitableClassesForInj = new ArrayList<>();
                for (String pack : packages) {
                    List<Class<?>> classesInPackage = ClassSearch.processDirectory(
                            new File("src/main/java/" + pack), pack);

                    for (Class<?> classInP : classesInPackage) {
                        if (classInP.getInterfaces().length == 1 &&
                                classInP.getInterfaces()[0].equals(suitableType)) {
                            suitableClassesForInj.add(classInP);
                        }
                    }
                }
                if (!Collection.class.isAssignableFrom(fieldType)) {
                    if (suitableClassesForInj.size() == 1) {
                        field.setAccessible(true);
                        field.set(object, suitableClassesForInj.get(0).getDeclaredConstructor().newInstance());

                    } else
                        throw new RuntimeException("Class not found or found more than 1. Can not inject");

                } else {
                    List<Object> injection = new ArrayList<>();
                    for (Class<?> sc : suitableClassesForInj) {
                        injection.add(sc.getDeclaredConstructor().newInstance());
                    }
                    field.setAccessible(true);
                    try {
                        field.set(object, injection);
                    }
                    catch (IllegalAccessException e){

                    }


                }

            }

        }
        return object;
    }


}


package ua.lviv.iot.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.lviv.iot.model.anotation.Column;
import ua.lviv.iot.model.anotation.CompositePrimaryKey;
import ua.lviv.iot.model.anotation.Table;


public class Transformer<T> {
  private final Class<T> className;

  public Transformer(Class<T> className) {
    this.className = className;
  }

  public Object fromResultSetToEntity(ResultSet resultSet) throws SQLException {
    Object entity = null;
    try {
      entity = className.getConstructor().newInstance();
      if (className.isAnnotationPresent(Table.class)) {
        Field[] fields = className.getDeclaredFields();
        for (Field field : fields) {
          if (field.isAnnotationPresent(Column.class)) {
            Column column = (Column) field.getAnnotation(Column.class);
            String name = column.name();
            int length = column.length();
            field.setAccessible(true);
            Class fieldType = field.getType();
            if (fieldType == String.class) {
              field.set(entity, resultSet.getString(name));
            } else if (fieldType == Integer.class) {
              field.set(entity, resultSet.getInt(name));
            } else if (fieldType == Date.class) {
              field.set(entity, resultSet.getDate(name));
            } else if (fieldType == Double.class) {
              field.set(entity, resultSet.getDouble(name));
            } else if (fieldType == Boolean.class) {
              field.set(entity, resultSet.getBoolean(name));
            }
          } else if (field.isAnnotationPresent(CompositePrimaryKey.class)) {
            field.setAccessible(true);
            Class fieldType = field.getType();
            Object foreignKey = fieldType.getConstructor().newInstance();
            field.set(entity, foreignKey);
            Field[] innerFields = fieldType.getDeclaredFields();
            for (Field innerField : innerFields) {
              if (innerField.isAnnotationPresent(Column.class)) {
                Column column = (Column) field.getAnnotation(Column.class);
                String name = column.name();
                int length = column.length();
                innerField.setAccessible(true);
                Class innerFieldType = innerField.getType();
                if (innerFieldType == String.class) {
                  innerField.set(entity, resultSet.getString(name));
                } else if (innerFieldType == Integer.class) {
                  innerField.set(entity, resultSet.getInt(name));
                } else if (innerFieldType == Date.class) {
                  innerField.set(entity, resultSet.getDate(name));
                } else if (innerFieldType == Double.class) {
                  innerField.set(entity, resultSet.getDouble(name));
                } else if (innerFieldType == Boolean.class) {
                  innerField.set(entity, resultSet.getBoolean(name));
                }
              }
            }
          }
        }
      }
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return entity;
  }
}

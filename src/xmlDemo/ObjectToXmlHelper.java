package xmlDemo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



public class ObjectToXmlHelper {
	public static <T> String convertToXml(T objT) throws NoSuchFieldException, SecurityException {
		StringBuilder sb = new StringBuilder();
			sb.append("<?xml version = \"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append(convertToXml(objT, 0));
		return sb.toString();
	}
	
	public static <T> String convertToXml(T obj, int numOfTab) throws NoSuchFieldException, SecurityException {
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = obj.getClass(); 
		boolean isXmlDoc = clazz.isAnnotationPresent(XmlRootElement.class);
		List<Field> fields;
		String name;
		String value;
		if(isXmlDoc) {
			XmlRootElement rootNodElement = clazz.getAnnotation(XmlRootElement.class);			
			sb.append(getTab(numOfTab));
			sb.append("<" + rootNodElement.name() );
			
			if (isNotEmpty(rootNodElement.nameSpace()))
			{ 
				sb.append(" xmlns=\"" + rootNodElement.nameSpace() + "\">"); 
			} else {
				Field field = clazz.getDeclaredField("isbn");
				field.setAccessible(true);
				XmlAttribute ann = field.getAnnotation(XmlAttribute.class); 
				name = ann.name(); 
				value = getValueOfField(field, obj); 
				sb.append(" " + name + "=\"" + value + "\">"); // Add attribute								
			}
			sb.append("\n");			
			fields = getFields(clazz, null);
			if(!fields.isEmpty()) {
				for(Field field: fields) {
					field.setAccessible(true);
					if(field.isAnnotationPresent(XmlElementWrapper.class)) {
						sb.append(createXmlWrapper(field, obj, numOfTab + 1));
					} else if(field.isAnnotationPresent(XmlElement.class)){
						sb.append(createXmlElement(field, obj, numOfTab + 1));
					} 
				}
			}										
			sb.append(getTab(numOfTab));
			sb.append("</" + rootNodElement.name() + ">");
		}
		return sb.toString();
	}
	
	public static String createXmlWrapper(Field field, Object obj, int numOfTab) throws NoSuchFieldException, SecurityException {
		StringBuilder sb = new StringBuilder();
		XmlElementWrapper annElementWrapper = field.getAnnotation(XmlElementWrapper.class);
		String nameString = annElementWrapper.name();
		sb.append(getTab(numOfTab));
		sb.append("<" + nameString + ">\n");
		Collection<?> collection = getListValueOfField(field, obj);
		if(collection != null && !collection.isEmpty()) {
			for(Object object: collection) {
				sb.append(convertToXml(object, numOfTab + 1));
				sb.append("\n");
			}
		}
		sb.append(getTab(numOfTab));
		sb.append("</" + nameString + ">\n");
		return sb.toString();
	}
	
	public static String createXmlElement(Field field, Object obj, int numOfTab) {
		StringBuilder sb = new StringBuilder();
		XmlElement ann = field.getAnnotation(XmlElement.class);
		if(ann == null) {
			System.out.println("ann == null");
		}
		String nameString = ann.name();
		String valueString = getValueOfField(field, obj);
		sb.append(getTab(numOfTab));
		sb.append("<" + nameString + ">");
		sb.append(valueString);
		sb.append("</" + nameString + ">");
		sb.append("\n");
		return sb.toString();
	}
	
	public static boolean isNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}
	
	//Lấy danh sách fields có sử dụng Annotation ann
	public static List<Field> getFields(Class<?> clazz, Class<? extends Annotation> ann){
		List<Field> fieldsAttributesFields = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		if(ann == null) {
			fieldsAttributesFields.addAll(Arrays.asList(fields));
		} else {
			for(Field field: fields) {
				if(field.isAnnotationPresent(ann)) {
					fieldsAttributesFields.add(field);
				}
			}
		}
		return fieldsAttributesFields;
	}	
	
	public static String getValueOfField(Field field, Object obj) {
		String valueString = "";
		try {
			valueString = String.valueOf(field.get(obj));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return valueString;
	}
	
	public static Collection<?> getListValueOfField(Field field, Object obj){
		Collection<?> collection = null;
		try {
			Object objValueObject = field.get(obj);
			if(objValueObject instanceof Collection<?>) {
				collection = (Collection<?>)objValueObject;
			}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return collection;
	}
	
	public static String getTab(int numOfTab) {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < numOfTab; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}
}

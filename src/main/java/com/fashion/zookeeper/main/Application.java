package com.fashion.zookeeper.main;

import java.lang.reflect.Field;

import com.fashion.zookeeper.analyzer.AnnotationAnalyzer;
import com.fashion.zookeeper.annotation.Path;

public class Application {

	public static void main(String[] args) {

		AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer();
		
		Class<? extends AnnotationAnalyzer> class1 = annotationAnalyzer.getClass();
		
		Field[] declaredFields = class1.getDeclaredFields();
		
		for (Field field : declaredFields) {
			
			if(field.isAnnotationPresent(Path.class)) {
				Path annotation = field.getAnnotation(Path.class);
				
				System.out.println(annotation.value());
			}

			System.out.println(field.getName());
		}
	}
}

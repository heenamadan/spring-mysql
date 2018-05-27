package com.concretepage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:items.properties")
@ConfigurationProperties("app") 
public class ItemsProperties {
	
	private List<Items> itemList  = new ArrayList<>();
	
	public static class Items {
        private String name;
        

        //getters and setters

        public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		@Override
        public String toString() {
            return "Menu{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}

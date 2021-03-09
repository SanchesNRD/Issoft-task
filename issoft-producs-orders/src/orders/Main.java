/*
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
В ЗАДАЧЕ ПРО СНЕГ ОТВЕТ 20 )) Я СПЕШИЛ, ПОЭТОМУ ПОДУМАЛ ЧТО ПОДВОХ В ВИСОКОСНОМ ГОДЕ И ОТВЕТИЛ НЕВЕРНО
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
*/
package orders;

import java.io.*;
import java.util.ArrayList;

public class Main {
	public static void main(String str[]){
		
		//хранит ID заказы в определнный день (введенный с клавиатуры)
		ArrayList<String> arrayList = findOrders(readDate());
		
		//хранит массив объектов. Состояние объекта: Id продукта; Название; Количества; Цена за штуку.
		ArrayList<ProductInfo> products = setProductInfo(arrayList);
		
		//вызываем метод для определения имени и цены за штуку
		setProductAdditionalInfo(products);
		
		
		//посмотреть все продукты
		//showFullProductsInfo(products);
		
		//посмотреть информацию о продукте с максимальным доходом
		 showMaxIncomeProduct(products);

    }
	
	
	//метод для записи даты (год-месяц-день) пример: 2021-03-05
	public static String readDate() {
		String date = null;
		BufferedReader reader = null;
		
		System.out.println("Введите дату (год-месяц-день) пример: 2021-01-12: ");
		try {
			reader = new  BufferedReader(new InputStreamReader(System.in));
			date = reader.readLine();
			reader.close();
		}
		catch (Exception e){
            e.printStackTrace();
		}
		
		return date;
	}
	
	//метод для поиска Id заказов по записанной дате
	public static ArrayList findOrders(String date) {
		String filePath = "files\\orders.csv";
        BufferedReader reader = null;
        String line = "";
        ArrayList<String> ordersId = new ArrayList<String>();	
        

        try{
            reader = new BufferedReader(new FileReader(filePath));
            while((line = reader.readLine()) != null){

                String[] row = line.split(",");

                if(row[1].contains(date) == true) {
                	//System.out.printf("%-20s", row[1]);	
                	//System.out.println();
                	ordersId.add(row[0]);
                }
            }
            reader.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return ordersId;
	}
	
	//метод для чтения 2 файла(order_items) и запись информации о продукте: ID, количество
	public static ArrayList setProductInfo(ArrayList<String> arrayList) {
		BufferedReader reader = null;
		String filePath = "files\\order_items.csv";
        String line = "";
        ArrayList<ProductInfo> products = new ArrayList<ProductInfo>();
        
        try {
        	reader = new BufferedReader(new FileReader(filePath));
        	while((line = reader.readLine()) != null) {
        		ProductInfo productThis = new ProductInfo();
        		String[] row = line.split(",");
        		
        		for(String index : arrayList) {
        			if(row[0].equals(index)) {
        				boolean repeatProduct = false;
        				
        				//смотрит, есть ли уже такие продукты в массиве, если есть, то прибавляет новое количество к старому
        				for(ProductInfo element : products) {
        					if(row[1].equals(element.getId())) {
        						repeatProduct = true;
        						element.setQuantity(element.getQuantity() + Integer.parseInt(row[2]));
        						break;
        					}
        				}
        				
        				//если не встретился похожий ид продукта, то он создается 
        				if(repeatProduct == false) {
        					productThis.setId(row[1]);
        					productThis.setQuantity(Integer.parseInt(row[2]));
        					products.add(productThis);
        				}
        				//
        				break;        					
        			}	
        		}
        	}
        	reader.close();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        return products;
	}

	//метод для записи имен и цен
	public static void setProductAdditionalInfo(ArrayList<ProductInfo> array) {
		BufferedReader reader = null;
		String filePath = "files\\products.csv";
        String line = "";
        
        try {
        	reader = new BufferedReader(new FileReader(filePath));
        	while((line = reader.readLine()) != null) {
        		String[] row = line.split(",");
        		
        		for(ProductInfo element : array) {
        			if(row[0].equals(element.getId())) {
        				element.setName(row[1]);
        				element.setPrice(Integer.parseInt(row[2]));
        			}
        		}
        	}
        	reader.close();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}

	//вывод продукт с максимальным доходом за введеный день
	public static void showMaxIncomeProduct(ArrayList<ProductInfo> products) {
		int maxIncome = -1;
		String id = " ";
		String name = " ";
		for(ProductInfo element : products) {
			if(element.getFullPrice() > maxIncome) {
				maxIncome = element.getFullPrice();
				id = element.getId();
				name = element.getName();
			}
		}
		
		System.out.println("За этот день самый большой доход был: " + maxIncome + "\nИнформация о продукте: " + name + " | " + id);
	}
	
	//выводит всю информацию о всех продуктах в данный введеный день
	public static void showFullProductsInfo(ArrayList<ProductInfo> products) {
		System.out.println(products.size());
		
		for(ProductInfo index : products) {
			//System.out.printf("%-20s", index.getId());
			System.out.print(index.getId() +  " "  + index.getQuantity() + " " + index.getName() + " " + index.getPrice() + " |" + index.getFullPrice());
            System.out.println();
		}
	}
}


package com.projectx.data.service.request;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.util.PincodeData;
import com.projectx.data.repository.util.PincodeDataRepository;

@Component
public class PincodeReadCSV {
	
	
	@Autowired
	PincodeDataRepository pincodeDataRepository;
	
	 public void run() {
		 
			String csvFile = "/home/chhand/Downloads/all_india_pin_code.csv";
			//BufferedReader br = null;
			//String line = "";
			String cvsSplitBy = ",";
		 
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			executorService.execute(()->{
				BufferedReader br = null;
				String line = "";
			try {
		 
				
					
					br = new BufferedReader(new FileReader(csvFile));
					line = br.readLine();
					
					while ((line = br.readLine()) != null) {
						 
				        // use comma as separator
					String[] country = line.split(cvsSplitBy);
		 
					System.out.println("Country [office= " + country[0] 
		                                 + " , pincode=" + country[1] + ",Taluka="+country[7]+",Dist="+country[8]+",state="+country[9]+"]");
					
					
					PincodeData pincodeData=new PincodeData(Integer.parseInt(country[1]), country[0], country[7], country[8], country[9]);
					
					pincodeDataRepository.save(pincodeData);
		 
				}
			
					
					
				
				 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		 
			System.out.println("Done");
		  });

			try {
				executorService.awaitTermination(1, TimeUnit.DAYS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
}
/**
 * 
 */
package org.vsg.rmodel.schejob.search.image;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author ruanweibiao
 *
 */
public class FullIndexScheduler {
	
	// --- set the schedule --
	public static void main(String[] args) {
		
		JobRunModule runModule = new JobRunModule();
		
		Injector injector =  Guice.createInjector(runModule);
		
		SearchIndex si =  injector.getInstance(SearchIndex.class);
		
		si.indexData();
	}
	

}
